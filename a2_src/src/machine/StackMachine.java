package machine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.EnumSet;

import source.Errors;
import source.Severity;
import syms.SymEntry;
import tree.CodePlusProcedures;
import tree.Procedures;

/** class StackMachine - Implementation of an emulation engine and code writer
 *    for the Stack Machine.
 * @version $Id: StackMachine.java 10 2013-04-15 23:26:08Z uqihayes $
 */

public class StackMachine {

    /** Size of an integer variable */
    public static int SIZE_OF_INT = 1;
    /** Size of a boolean variable */
    public static int SIZE_OF_BOOLEAN = 1;
    /** Size of an address (reference) */
    public static int SIZE_OF_ADDRESS = 1;
    /** Value used for false */
    public static int FALSE_VALUE = 0;
    /** Value used for true */
    public static int TRUE_VALUE = 1;
    
    /** Offset of start of local variables from frame pointer */
    public final static int LOCALS_BASE = 3; 
    /** Offset of start of parameters from frame pointer */
    public final static int PARAMS_BASE = 0; 
    /** Start of code within memory */
    public final static int CODE_START = 1000;
    /** Size of memory */
    private final static int MEM_LIMIT = 10000;
    /** Address outside memory */
    public final static int NULL_ADDR = MEM_LIMIT;
    /** Memory array - stack and heap and code */
    private int memory[] = new int [MEM_LIMIT];
    /** Location to store the next instruction during code generation */
    private int currLocn = CODE_START;
    /** Print an assembler listing? */
    private boolean listing = false;
    /** Stack machine running? */
    private boolean running = false;
    
    /** Tracing constants (unioned together) */
    public static enum Trace {
        MEM, 
        CALLS,
        JUMPS,
        STACK,
        STATE;
    }
    /** Trace everything */
    public static EnumSet<Trace> TRACE_ALL = EnumSet.allOf( Trace.class );
    /** No tracing at all. */
    public static EnumSet<Trace> TRACE_NONE = 
        EnumSet.complementOf( TRACE_ALL );
    /** Current tracing during execution of stack machine */
    private EnumSet<Trace> tracing = TRACE_NONE;
    
    /** Object to handle error reports */
    private Errors error;
    /** Stores addresses of procedure starts */
    private Procedures procStarts;

    /** Bottom of stack */
    private final int STACK_START = 0;
    /** Program counter */
    private int pc = CODE_START;
    /** Frame pointer */
    private int fp = STACK_START;
    /** Top of stack pointer - always one past top */
    private int sp = STACK_START;
    /** Top of stack limit = bottom of heap limit */
    private int limit = CODE_START;
    /** Standard input line reader */
    private BufferedReader in =
        new BufferedReader( new InputStreamReader (System.in) );

/****************************** Constructors **************************/

    public StackMachine( Errors error, boolean verbose, CodePlusProcedures code ) {
        this.error = error;
        this.listing = verbose;
        procStarts = code.getProcStarts();
        for( int i=0; i<MEM_LIMIT; i++ )
            memory[i] = NULL_ADDR;  // out of memory address
        for( Instruction inst : code.getInstructionList().getCode() ) {
            inst.loadInstruction(this);
        }
    }

/***************************** Public Methods *************************/

    /** Specify whether assembly listings are to be printed out when storing
     * instructions / constants
     */
    public void setListing( boolean list ) {
        listing = list;
    }
    /** Specify whether code tracing is to be output when executing */
    public void setTracing( EnumSet<Trace> flags ) {
        tracing = flags;
    }
    /** Begin executing the code stored in the stack machine. 
     * Runs until a STOP opcode, a return to 0, or an illegal condition 
     * (e.g., popping an empty stack.
     */
    public void run( ) {
        running = true;
        while( running ) {
            execInstruction();
        }
        System.out.println();
        System.out.println("Terminated");
    }

/*********************** Public Code Generators ************************/
    /** Store the given word (with associated name) into the code buffer
     * @param word to be stored
     * @param name of operation code to be used in listing. */
    public void generateWord( int word, String name ) {
        if( currLocn >= MEM_LIMIT ) {
            error.errorMessage( "Object code too large.",
                                Severity.RESTRICTION );
        } else {
            memory[ currLocn++ ] = word;
            if( listing )
                printListing( currLocn - 1, word, name );
        }
    }
    /** Push the value onto the stack, and increment the stack pointer */
    private void push( int val ) {
        if( sp >= limit ) {
            runtimeError( "Error: memory overflow!" );
        }
        else {
            if( tracing.contains( Trace.STACK ) ) {
                System.out.print( " Push(" + val + ") " );
            }
            memory[sp++] = val;
        }
    }
    /** Pop the top value form the stack and decrement the stack pointer */
    private int pop( ) {
        if( sp <= STACK_START ) {
            runtimeError( "Error: stack underflow!" );
            return 0;
        } else {
            if( tracing.contains( Trace.STACK) ) {
                System.out.print( " Pop() = " + memory[sp-1] + " " );
            }
            return memory[--sp];
        }
    }
    /** Return value stored at address */
    private int loadValue( int address ) {
        int val = 0;
        if( address < 0 || address >= MEM_LIMIT ) {
            runtimeError("Load outside memory pc="  +
                + (pc-1) + ": address=" + (address) );
        } else {
             val = memory[address];
        }
        if( tracing.contains( Trace.MEM ) ) {
            System.out.print( "\n    Load [" + (address) + "] => " + val );
        }
        return val;
    }
    /** Store value at StoreAdr */
    private void storeValue( int address, int value ) {
        if( address < 0 || address >= CODE_START ) {
            runtimeError("Store outside memory pc="  +
                + (pc-1) + ": address=" + (address) );
        } else {
             memory[ address ] = value;
        }
        if( tracing.contains( Trace.MEM ) ) {
            System.out.print( "\n    Store [" + (address) + "] <= " + value );
        }
    }
    /** Print a listing line to the message handler */
    private void printListing( int locn, int word, String name ) {
        /** Offset used in listing code */
        final int ASSEMBLY_POSN = 4;
        StringBuffer buf = new StringBuffer();
        pad( buf, ASSEMBLY_POSN );
        buf.append( locn );
        pad( buf, ASSEMBLY_POSN + 5 );
        buf.append( ":  " );
        buf.append( word );
        pad( buf, ASSEMBLY_POSN + 14 );
        buf.append( name );
        System.out.println( buf.toString() );
    }
    /** Dump the contents of the stack to stdout.
     * Used for debugging. */
    private void dumpStack() {
        StringBuffer out = new StringBuffer("\nStack pointer = " + sp + "\n");
        for( int i=STACK_START; i<sp; i++ ) {
            if( i == fp ) {
                out.append(" FP: ");
            } else {
                out.append( "     " );
            }
            int n = out.length();
            out.append( i );
            pad( out, n+4 );
            out.append( ": " );
            out.append( memory[i] );
            out.append( '\n' );
        }
        System.out.print( out );
    }
    /** Trace back of procedure calls */
    public void traceBack() {
        /* Start trace back from current program counter and frame pointer */
        int tracePC = pc;
        int traceFP = fp;
        while( tracePC != 0 ) {
            SymEntry.ProcedureEntry proc = 
                procStarts.getProcedure( tracePC );
            if( proc == null ) {
                // if fp is 0 then in main program setup/finalisation code
                if( fp != 0 ) {
                    System.out.println( "Trace back terminated early - " +
                        "PC out of valid range" );
                    dumpStack();
                }
                return;
            }
            System.out.print( "PC=" + tracePC + " in " + proc.getIdent() );
            System.out.print( " FP=" + traceFP );
            int staticLink = memory[traceFP];
            System.out.print( " SL=" + staticLink );
            // Dynamic link is at offset 1 from frame pointer
            int dynamicLink = memory[traceFP+1];
            System.out.print( " DL=" + dynamicLink );
            // Return address is at offset 2
            System.out.println( " RA=" + memory[traceFP+2] );
            for( SymEntry entry : proc.getLocalScope().getEntries() ) {
                if( entry instanceof SymEntry.VarEntry ) {
                    SymEntry.VarEntry varEntry = (SymEntry.VarEntry)entry;
                    System.out.println( "  " + varEntry.getIdent() + 
                            "(" + varEntry.getOffset() + ")" +
                            " = " + memory[ traceFP + varEntry.getOffset()] );
                }
            }
            // Return PC is at offset 2 from frame pointer
            tracePC = memory[traceFP+2];
            if( dynamicLink != 0 && dynamicLink > traceFP - 3 ) {
                System.out.println( "Trace back terminated early - " +
                    "invalid dynamic link " + dynamicLink + " FP= " + traceFP );
                dumpStack();
                return;
            }
            traceFP = dynamicLink;
        }
    }
    /** Right pad the given string buffer to the given length */
    private void pad( StringBuffer buf, int to ) {
        for( int i=buf.length(); i<to; i++ ) {
            buf.append( ' ' );
        }
    }
    /** Runtime error while executing program. */
    private void runtimeError( String message ) {
        running = false;
        System.out.println( message );
        dumpStack();
        traceBack();
    }
/********************************** Execution *******************************/
    /** Convert from integer to operation */
    Operation[] getOperation = Operation.values();
    /** Execute the instruction pointed to by the pc register, 
     * and adjust pc to point to the next instruction.
     */
    private void execInstruction() {
        if( pc < CODE_START || pc >= currLocn ) {
            runtimeError( "\nRuntime error: PC = " + pc + " out of range of code" );
            return;
        }
        int instWord = memory[pc++];
        if( instWord < 0 || getOperation.length <= instWord ) {
            runtimeError( "\nRuntime error: Invalid opcode" );
            return;
        }
        Operation inst = getOperation[instWord];
        int address;
        if( tracing.contains( Trace.STATE ) ) {
            System.out.print( "\n" + "PC: " + (pc-1) +
                              ": FP: " + fp +
                              "  SP: " + sp +
                              "  Limit: " + limit + 
                              "  Opcode: " +
                              inst + " " );
            if( inst == Operation.LOAD_CON ) {
                System.out.print( memory[pc] + " " );
            }
        }
        switch (inst) {
        case NO_OP: /* Do nothing */
            break;
        case BR_FALSE: /* If the second top value = FALSE_VALUE, 
                jump to the destination */
            int dest = pop();
            int test = pop();
            if (test == FALSE_VALUE) {
                pc += dest;
            } else if (test != TRUE_VALUE ) {
                runtimeError("\nRuntime error: Non-boolean operand in branch");
            }
            if (tracing.contains(Trace.JUMPS)) {
                System.out.print("\n      Branch => " + pc);
            }
            break;
        case COPY: /* Copy top-of-stack words from third-top-of-stack address 
                      to second-top-of-stack address */
            int copySize = pop();
            int toAddr = fp + pop();
            int fromAddr = fp + pop();
            int copyLimit = fromAddr + copySize;
            while (fromAddr < copyLimit && running) {
                storeValue(toAddr, loadValue(fromAddr));
                fromAddr += 1;
                toAddr += 1;
            }
            break;
        case CALL: /* Execute a call */
            int addr = pop();   /* pop address of procedure */
            /* Set up a new stack frame.
             * We assume a static link has already been set up */
            push(fp);           /* push fp to create the dynamic link */
            fp = sp - 2;        /* frame pointer addresses static link */
            push(pc);           /* save return address */
            pc = addr;          /* branch to procedure */
            if (tracing.contains(Trace.CALLS)) {
                System.out.print("\n      Call => " + pc);
            }
            break;
        case RETURN: /* Return to caller */
            sp = fp + 3;   /* Set stack pointer so next pop is return address
                              this will also deallocate any locals */ 
            pc = pop();    /* Set program counter to return address. */
            fp = pop();    /* Restore the frame pointer from dynamic link */
            pop();         /* Remove the static link */
            if (pc == 0) { /* Return from main terminates program */
                running = false;
            }
            if (tracing.contains(Trace.CALLS)) {
                System.out.print("\n      Returning => " + pc);
            }
            break;
        case ALLOC_STACK: /* Allocate top-of-stack words on stack */
            /* It is assumed that the top of stack contains the number of 
             * words to be allocated on the stack. */
            int locs = pop(); /* size in words */
            /* Allocate space on stack */
            for (int i = 1; i <= locs; i++) {
                /* Push a useless value to make error detection more likely. */
                push(NULL_ADDR); 
            }
            break;
        case DEALLOC_STACK: /* Remove locations from the stack */
            int nwords = pop(); /* Number of words for to deallocate */
            if( sp - nwords <= fp + 2 ) {
                runtimeError( "\nDeallocating too many words");
            } else {
                sp -= nwords;   /* Deallocate locations */
            }
            break;
        case POP: /* Discard the top of stack */
            pop();
            break;
        case DUP: /* Duplicate value on stack */
            int val = pop();
            push(val);
            push(val);
            break;
        case SWAP: /* Swap top two values on stack */
            int val1 = pop();
            int val2 = pop();
            push(val1);
            push(val2);
            break;
        case DIV: /* Divide */
            int divbottom = pop();
            int divtop = pop();
            if (divbottom == 0) {
                runtimeError("\nRuntime error: Divide by zero");
            } else {
                push( divtop / divbottom );
            }
            break;
        case MPY: /* Multiply */
            push(pop() * pop());
            break;
        case ADD: /* Add */
            push(pop() + pop());
            break;
        case XOR: /* Bitwise XOR */
            push(pop() ^ pop());
            break;
        case OR: /* Bitwise OR */
            push(pop() | pop());
            break;
        case AND: /* Bitwise AND */
            push(pop() & pop());
            break;
        case EQUAL: /* Test if top two values are equal */
            push(pop() == pop() ? TRUE_VALUE : FALSE_VALUE);
            break;
        case LESS: /* Test if second top value < top value */
            int top = pop();
            int second = pop();
            push(second < top ? TRUE_VALUE : FALSE_VALUE);
            break;
        case LESSEQ: /* Test if second top value <= top value */
            top = pop();
            second = pop();
            push(second <= top ? TRUE_VALUE : FALSE_VALUE);
            break;
        case NOT: /* Bitwise inversion */
            push(~pop());
            break;
        case NEGATE: /* 2s complement */
            push(-pop());
            break;
        case READ: /* Read a number from stdin */
            int read;
            try {
                read = Integer.parseInt(in.readLine());
                push(read);
            } catch (Exception e) {
                runtimeError( "\nInvalid value read");
            }
            break;
        case WRITE: /* Write a number to stdout */
            System.out.println(Integer.toString(pop()));
            break;
        case BOUND: /* Check if index is within bounds, 
                halt if not. This needs to be an instruction to write 
                the error */
            int upper = pop();
            int lower = pop();
            val = pop();
            if (val < lower || val > upper) {
                runtimeError("Bounds check failed at " + (pc - 1) + ": "
                        + val + " not in " + lower + ".." + upper);
            }
            push(val); /* push the value back on the stack */
            break;
        case TO_GLOBAL: /* Adjust local to global */
            push( pop() + fp );
            break;
        case TO_LOCAL: /* Adjust a global address to a frame-local one */
            push(pop() - fp);
            break;
        case LOAD_CON: /* Load a constant value from the following word */
            push(memory[pc++]);
            break;
        case LOAD_ABS: /* Load a value from address in top of stack */
            address = pop();
            push(loadValue(address));
            break;
        case STORE_FRAME: /* Store a value into memory */
            address = fp + pop();
            int value = pop();
            storeValue(address, value);
            break;
        case LOAD_FRAME: /* Load a value from memory frame relative */
            address = fp + pop();
            push(loadValue(address));
            break;
        case STORE_REL: /* Store a value into memory  */
            address = pop() + pop();
            value = pop();
            storeValue(address, value);
            break;
        case LOAD_REL: /* Load a value from memory from 
            top of stack offset relative to second top of stack */
            address = pop() + pop();
            push(loadValue(address));
            break;
        case ZERO: /* Push 0 on the stack */
            push(0);
            break;
        case ONE: /* Push 1 on the stack */
            push(1);
            break;
        case ALLOC_HEAP: /* Allocate memory from heap */
            int size = pop();
            limit -= size;
            push(limit); // will fail if limit less than sp
            for(int i = limit; i < limit+size; i++ ) {
                memory[i] = NULL_ADDR;
            }
            break;
        case LOAD_MULTI: /* Load multiple words onto stack from
                            address on second top of stack */
            int count = pop();        /* pop count of number of words */
            address = fp + pop();     /* address relative to frame pointer */
            while( count > 0 ) {
                push(loadValue(address++));
                count--;
            }
            break;
        case STORE_MULTI: /* Store multiple words from stack to
                             address on second top of stack */
            count = pop();        /* pop count of number of words */
            address = fp + pop() + count; /* address relative to frame pointer */
            while( count > 0 ) {
                /* store from last location back (to match LOAD_MULTI) */
                storeValue(--address, pop());
                count--;
            }
            break;
        case STOP: /* Halt */
            int exitcode = pop();
            switch( exitcode ) {
            case 1:
                runtimeError("Stopped: Expression in case doesn't match any label");
                break;
            case 2:
                runtimeError("Stopped: Nil pointer dereference");
                break;
            case 3:
                runtimeError("Stopped: No branch has true condition");
                break;
            default:
                runtimeError("\nMachine halted with code " + exitcode );
                break;
            }
            break;
        default:
            System.out.println("\nError: Opcode not implemented: " + inst );
        }
    }
}
