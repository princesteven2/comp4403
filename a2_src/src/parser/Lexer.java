/* The following code was generated by JFlex 1.4.3 on 4/15/13 3:32 PM */

/** JFlex lexical analyzer for PL0.
 * @version $Id: PL0.flex 8 2013-02-22 06:25:04Z ianh $
 * The input to JFlex consists of three sections:
 * - user code to be included directly in the generated class,
 * - options and definitions for JFlex, and
 * - lexical rules defining the tokens.
 * These sections are separated by lines containing just "%%"
 */

/* --------------------------Usercode Section------------------------ */

package parser;

import java_cup.runtime.*;
import source.ErrorHandler;
import source.Severity;
import source.Position;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 4/15/13 3:32 PM from the specification file
 * <tt>/Users/ianh/courses/comp4403/eclipse/A2/src/parser/PL0.flex</tt>
 */
class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\1\17\6\0"+
    "\1\6\1\7\1\15\1\13\1\23\1\14\1\22\1\16\12\4\1\11"+
    "\1\10\1\20\1\12\1\21\2\0\32\5\3\0\1\24\2\0\1\33"+
    "\1\25\1\32\1\35\1\26\1\40\1\27\1\46\1\30\2\5\1\34"+
    "\1\5\1\31\1\36\1\42\1\5\1\43\1\37\1\45\1\44\1\50"+
    "\1\41\1\5\1\47\1\5\uff85\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\1\1\16"+
    "\1\17\1\20\1\21\1\22\13\4\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\3\4\1\31\2\4\1\32\10\4"+
    "\1\33\1\4\1\34\10\4\1\35\1\4\1\36\1\37"+
    "\4\4\1\40\1\41\1\42\1\43\1\44\1\45\3\4"+
    "\1\46\2\4\1\47";

  private static int [] zzUnpackAction() {
    int [] result = new int[85];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\51\0\122\0\51\0\173\0\244\0\51\0\51"+
    "\0\51\0\315\0\51\0\51\0\51\0\51\0\366\0\u011f"+
    "\0\u0148\0\u0171\0\u019a\0\51\0\51\0\u01c3\0\u01ec\0\u0215"+
    "\0\u023e\0\u0267\0\u0290\0\u02b9\0\u02e2\0\u030b\0\u0334\0\u035d"+
    "\0\51\0\u0386\0\51\0\51\0\51\0\51\0\u03af\0\u03d8"+
    "\0\u0401\0\244\0\u042a\0\u0453\0\244\0\u047c\0\u04a5\0\u04ce"+
    "\0\u04f7\0\u0520\0\u0549\0\u0572\0\u059b\0\244\0\u05c4\0\244"+
    "\0\u05ed\0\u0616\0\u063f\0\u0668\0\u0691\0\u06ba\0\u06e3\0\u070c"+
    "\0\244\0\u0735\0\244\0\244\0\u075e\0\u0787\0\u07b0\0\u07d9"+
    "\0\244\0\244\0\244\0\244\0\244\0\244\0\u0802\0\u082b"+
    "\0\u0854\0\244\0\u087d\0\u08a6\0\244";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[85];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\2\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\6\1\30"+
    "\1\31\1\32\2\6\1\33\3\6\1\34\1\35\1\36"+
    "\1\6\1\37\2\6\1\40\53\0\1\4\52\0\1\5"+
    "\50\0\2\6\17\0\24\6\12\0\1\41\54\0\1\42"+
    "\44\0\1\43\50\0\1\44\50\0\1\45\60\0\1\46"+
    "\32\0\2\6\17\0\1\6\1\47\22\6\4\0\2\6"+
    "\17\0\4\6\1\50\2\6\1\51\14\6\4\0\2\6"+
    "\17\0\13\6\1\52\10\6\4\0\2\6\17\0\1\6"+
    "\1\53\22\6\4\0\2\6\17\0\6\6\1\54\15\6"+
    "\4\0\2\6\17\0\11\6\1\55\12\6\4\0\2\6"+
    "\17\0\16\6\1\56\2\6\1\57\2\6\4\0\2\6"+
    "\17\0\16\6\1\60\5\6\4\0\2\6\17\0\1\6"+
    "\1\61\22\6\4\0\2\6\17\0\21\6\1\62\1\63"+
    "\1\6\4\0\2\6\17\0\6\6\1\64\15\6\1\42"+
    "\2\0\46\42\4\0\2\6\17\0\2\6\1\65\21\6"+
    "\4\0\2\6\17\0\10\6\1\66\13\6\4\0\2\6"+
    "\17\0\12\6\1\67\11\6\4\0\2\6\17\0\14\6"+
    "\1\70\7\6\4\0\2\6\17\0\7\6\1\71\14\6"+
    "\4\0\2\6\17\0\3\6\1\72\20\6\4\0\2\6"+
    "\17\0\3\6\1\73\20\6\4\0\2\6\17\0\11\6"+
    "\1\74\12\6\4\0\2\6\17\0\5\6\1\75\1\76"+
    "\15\6\4\0\2\6\17\0\1\6\1\77\22\6\4\0"+
    "\2\6\17\0\15\6\1\100\6\6\4\0\2\6\17\0"+
    "\16\6\1\101\5\6\4\0\2\6\17\0\3\6\1\102"+
    "\20\6\4\0\2\6\17\0\1\6\1\103\22\6\4\0"+
    "\2\6\17\0\7\6\1\104\14\6\4\0\2\6\17\0"+
    "\20\6\1\105\3\6\4\0\2\6\17\0\7\6\1\106"+
    "\14\6\4\0\2\6\17\0\5\6\1\107\16\6\4\0"+
    "\2\6\17\0\11\6\1\110\12\6\4\0\2\6\17\0"+
    "\10\6\1\111\13\6\4\0\2\6\17\0\4\6\1\112"+
    "\17\6\4\0\2\6\17\0\1\6\1\113\22\6\4\0"+
    "\2\6\17\0\4\6\1\114\17\6\4\0\2\6\17\0"+
    "\1\6\1\115\22\6\4\0\2\6\17\0\1\6\1\116"+
    "\22\6\4\0\2\6\17\0\1\6\1\117\22\6\4\0"+
    "\2\6\17\0\16\6\1\120\5\6\4\0\2\6\17\0"+
    "\10\6\1\121\13\6\4\0\2\6\17\0\10\6\1\122"+
    "\13\6\4\0\2\6\17\0\17\6\1\123\4\6\4\0"+
    "\2\6\17\0\16\6\1\124\5\6\4\0\2\6\17\0"+
    "\1\6\1\125\22\6";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2255];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\1\11\2\1\3\11\1\1\4\11"+
    "\5\1\2\11\13\1\1\11\1\1\4\11\57\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[85];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    /** To create a new java_cup.runtime.Symbol.
     * @param kind is an integer code representing the token.
     * Note that CUP and JFlex use integers to represent token kinds.
     */
    private Symbol makeToken( int kind ) {
        /* Symbol takes the token kind, and the positions of the
         * leftmost and rightmost characters of the substring of the
         * input file that matched the token. 
         */
        // System.err.println( "Token " + yytext() + " " + kind );
        return new Symbol( kind, yychar, yychar+yytext().length() );
    }
    /** Also creates a new java_cup.runtime.Symbol with information
     * about the current token, but this object has a value. 
     * @param kind is an integer code representing the token.
     * @param value is an arbitrary Java Object.
     * Below when tokens such as a NUMBER or IDENTIFIER are 
     * recognised they pass values which are respectively
     * of type Integer and String. The types of these values *must*
     * match their type as declared in the Terminals sections
     * of the CUP specification.
     */
    private Symbol makeToken(int kind, Object value) {
        // System.err.println( "Token " + yytext() + " " +kind );
        return new Symbol( kind, yychar, yychar+yytext().length(), value );
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 110) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 32: 
          { return makeToken( CUPToken.KW_READ );
          }
        case 40: break;
        case 3: 
          { int value = 0x80808080; // Nonsense value
      try {
          value = Integer.parseInt( yytext() );
      } catch( NumberFormatException e ) { 
          /* Can only happen if the number is too big */
          ErrorHandler.getErrorHandler().errorMessage(
            "integer too large", Severity.ERROR,
            new Position( yychar ) );
      }
      return makeToken( CUPToken.NUMBER, new Integer( value ) );
          }
        case 41: break;
        case 30: 
          { return makeToken( CUPToken.KW_ELSE );
          }
        case 42: break;
        case 27: 
          { return makeToken( CUPToken.KW_END );
          }
        case 43: break;
        case 31: 
          { return makeToken( CUPToken.KW_CALL );
          }
        case 44: break;
        case 5: 
          { return makeToken( CUPToken.LPAREN );
          }
        case 45: break;
        case 29: 
          { return makeToken( CUPToken.KW_VAR );
          }
        case 46: break;
        case 13: 
          { return makeToken( CUPToken.DIVIDE );
          }
        case 47: break;
        case 14: 
          { return makeToken( CUPToken.LESS );
          }
        case 48: break;
        case 12: 
          { return makeToken( CUPToken.TIMES );
          }
        case 49: break;
        case 10: 
          { return makeToken( CUPToken.PLUS );
          }
        case 50: break;
        case 18: 
          { return makeToken( CUPToken.POINTER );
          }
        case 51: break;
        case 24: 
          { return makeToken( CUPToken.RANGE );
          }
        case 52: break;
        case 15: 
          { return makeToken( CUPToken.GREATER );
          }
        case 53: break;
        case 39: 
          { return makeToken( CUPToken.KW_PROCEDURE );
          }
        case 54: break;
        case 17: 
          { return makeToken( CUPToken.COMMA );
          }
        case 55: break;
        case 25: 
          { return makeToken( CUPToken.KW_IF );
          }
        case 56: break;
        case 23: 
          { return makeToken( CUPToken.GEQUALS );
          }
        case 57: break;
        case 22: 
          { return makeToken( CUPToken.LEQUALS );
          }
        case 58: break;
        case 33: 
          { return makeToken( CUPToken.KW_THEN );
          }
        case 59: break;
        case 26: 
          { return makeToken( CUPToken.KW_DO );
          }
        case 60: break;
        case 16: 
          { return makeToken( CUPToken.PERIOD );
          }
        case 61: break;
        case 19: 
          { return makeToken( CUPToken.ASSIGN );
          }
        case 62: break;
        case 38: 
          { return makeToken( CUPToken.KW_RECORD );
          }
        case 63: break;
        case 20: 
          { /* ignore comment - an empty action causes the lexical analyser
       * to skip the matched characters in the input and then start
       * scanning for a token from the next character. */
          }
        case 64: break;
        case 34: 
          { return makeToken( CUPToken.KW_TYPE );
          }
        case 65: break;
        case 9: 
          { return makeToken( CUPToken.EQUALS );
          }
        case 66: break;
        case 8: 
          { return makeToken( CUPToken.COLON );
          }
        case 67: break;
        case 1: 
          { return makeToken( CUPToken.ILLEGAL );
          }
        case 68: break;
        case 36: 
          { return makeToken( CUPToken.KW_WRITE );
          }
        case 69: break;
        case 35: 
          { return makeToken( CUPToken.KW_BEGIN );
          }
        case 70: break;
        case 4: 
          { return makeToken( CUPToken.IDENTIFIER, yytext() );
          }
        case 71: break;
        case 7: 
          { return makeToken( CUPToken.SEMICOLON );
          }
        case 72: break;
        case 6: 
          { return makeToken( CUPToken.RPAREN );
          }
        case 73: break;
        case 37: 
          { return makeToken( CUPToken.KW_WHILE );
          }
        case 74: break;
        case 11: 
          { return makeToken( CUPToken.MINUS );
          }
        case 75: break;
        case 21: 
          { return makeToken( CUPToken.NEQUALS );
          }
        case 76: break;
        case 2: 
          { /* ignore white space */
          }
        case 77: break;
        case 28: 
          { return makeToken( CUPToken.KW_NEW );
          }
        case 78: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {     return makeToken( CUPToken.EOF );
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
