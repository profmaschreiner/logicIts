package logicits.javaccList;

/* LogicoLexicoTokenManager.java */
 /* Generated By:JJTree&JavaCC: Do not edit this line. LogicoLexicoTokenManager.java */
import logicits.javaccList.TokenMgrError;
import logicits.javaccList.Token;
import java.io.*;
import java.util.*;

/**
 * Token Manager.
 */
@SuppressWarnings("unused")
public class LogicoLexicoTokenManager implements LogicoLexicoConstants {

    /**
     * Debug output.
     */
    public static java.io.PrintStream debugStream = System.out;

    /**
     * Set debug output.
     */
    public static void setDebugStream(java.io.PrintStream ds) {
        debugStream = ds;
    }

    private static final int jjStopStringLiteralDfa_0(int pos, long active0) {
        switch (pos) {
            default:
                return -1;
        }
    }

    private static final int jjStartNfa_0(int pos, long active0) {
        return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
    }

    static private int jjStopAtPos(int pos, int kind) {
        jjmatchedKind = kind;
        jjmatchedPos = pos;
        return pos + 1;
    }

    static private int jjMoveStringLiteralDfa0_0() {
        switch (curChar) {
            case 13:
                return jjMoveStringLiteralDfa1_0(0x400L);
            case 38:
                return jjStopAtPos(0, 3);
            case 40:
                return jjStopAtPos(0, 1);
            case 41:
                return jjStopAtPos(0, 2);
            case 45:
                return jjMoveStringLiteralDfa1_0(0x100L);
            case 60:
                return jjMoveStringLiteralDfa1_0(0x80L);
            case 124:
                return jjStopAtPos(0, 4);
            case 126:
                return jjStopAtPos(0, 5);
            default:
                return jjMoveNfa_0(0, 0);
        }
    }

    static private int jjMoveStringLiteralDfa1_0(long active0) {
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_0(0, active0);
            return 1;
        }
        switch (curChar) {
            case 10:
                if ((active0 & 0x400L) != 0L) {
                    return jjStopAtPos(1, 10);
                }
                break;
            case 45:
                return jjMoveStringLiteralDfa2_0(active0, 0x80L);
            case 62:
                if ((active0 & 0x100L) != 0L) {
                    return jjStopAtPos(1, 8);
                }
                break;
            default:
                break;
        }
        return jjStartNfa_0(0, active0);
    }

    static private int jjMoveStringLiteralDfa2_0(long old0, long active0) {
        if (((active0 &= old0)) == 0L) {
            return jjStartNfa_0(0, old0);
        }
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_0(1, active0);
            return 2;
        }
        switch (curChar) {
            case 62:
                if ((active0 & 0x80L) != 0L) {
                    return jjStopAtPos(2, 7);
                }
                break;
            default:
                break;
        }
        return jjStartNfa_0(1, active0);
    }

    static private int jjMoveNfa_0(int startState, int curPos) {
        int startsAt = 0;
        jjnewStateCnt = 2;
        int i = 1;
        jjstateSet[0] = startState;
        int kind = 0x7fffffff;
        for (;;) {
            if (++jjround == 0x7fffffff) {
                ReInitRounds();
            }
            if (curChar < 64) {
                long l = 1L << curChar;
                do {
                    switch (jjstateSet[--i]) {
                        case 1:
                            if ((0x3ff000000000000L & l) == 0L) {
                                break;
                            }
                            kind = 6;
                            jjstateSet[jjnewStateCnt++] = 1;
                            break;
                        default:
                            break;
                    }
                } while (i != startsAt);
            } else if (curChar < 128) {
                long l = 1L << (curChar & 077);
                do {
                    switch (jjstateSet[--i]) {
                        case 0:
                            if ((0x7fffffe07fffffeL & l) == 0L) {
                                break;
                            }
                            if (kind > 6) {
                                kind = 6;
                            }
                             {
                                jjCheckNAdd(1);
                            }
                            break;
                        case 1:
                            if ((0x7fffffe87fffffeL & l) == 0L) {
                                break;
                            }
                            if (kind > 6) {
                                kind = 6;
                            }
                             {
                                jjCheckNAdd(1);
                            }
                            break;
                        default:
                            break;
                    }
                } while (i != startsAt);
            } else {
                int i2 = (curChar & 0xff) >> 6;
                long l2 = 1L << (curChar & 077);
                do {
                    switch (jjstateSet[--i]) {
                        default:
                            break;
                    }
                } while (i != startsAt);
            }
            if (kind != 0x7fffffff) {
                jjmatchedKind = kind;
                jjmatchedPos = curPos;
                kind = 0x7fffffff;
            }
            ++curPos;
            if ((i = jjnewStateCnt) == (startsAt = 2 - (jjnewStateCnt = startsAt))) {
                return curPos;
            }
            try {
                curChar = input_stream.readChar();
            } catch (java.io.IOException e) {
                return curPos;
            }
        }
    }
    static final int[] jjnextStates = {};

    /**
     * Token literal values.
     */
    public static final String[] jjstrLiteralImages = {
        "", "\50", "\51", "\46", "\174", "\176", null, "\74\55\76", "\55\76", null,
        null, null, null,};

    static protected Token jjFillToken() {
        final Token t;
        final String curTokenImage;
        final int beginLine;
        final int endLine;
        final int beginColumn;
        final int endColumn;
        String im = jjstrLiteralImages[jjmatchedKind];
        curTokenImage = (im == null) ? input_stream.GetImage() : im;
        beginLine = input_stream.getBeginLine();
        beginColumn = input_stream.getBeginColumn();
        endLine = input_stream.getEndLine();
        endColumn = input_stream.getEndColumn();
        t = Token.newToken(jjmatchedKind, curTokenImage);

        t.beginLine = beginLine;
        t.endLine = endLine;
        t.beginColumn = beginColumn;
        t.endColumn = endColumn;

        return t;
    }

    static int curLexState = 0;
    static int defaultLexState = 0;
    static int jjnewStateCnt;
    static int jjround;
    static int jjmatchedPos;
    static int jjmatchedKind;

    /**
     * Get the next Token.
     */
    public static Token getNextToken() {
        Token matchedToken;
        int curPos = 0;

        EOFLoop:
        for (;;) {
            try {
                curChar = input_stream.BeginToken();
            } catch (java.io.IOException e) {
                jjmatchedKind = 0;
                jjmatchedPos = -1;
                matchedToken = jjFillToken();
                return matchedToken;
            }
            image = jjimage;
            image.setLength(0);
            jjimageLen = 0;

            try {
                input_stream.backup(0);
                while (curChar <= 32 && (0x100000600L & (1L << curChar)) != 0L) {
                    curChar = input_stream.BeginToken();
                }
            } catch (java.io.IOException e1) {
                continue EOFLoop;
            }
            jjmatchedKind = 0x7fffffff;
            jjmatchedPos = 0;
            curPos = jjMoveStringLiteralDfa0_0();
            if (jjmatchedKind != 0x7fffffff) {
                if (jjmatchedPos + 1 < curPos) {
                    input_stream.backup(curPos - jjmatchedPos - 1);
                }
                if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
                    matchedToken = jjFillToken();
                    TokenLexicalActions(matchedToken);
                    return matchedToken;
                } else {
                    continue EOFLoop;
                }
            }
            int error_line = input_stream.getEndLine();
            int error_column = input_stream.getEndColumn();
            String error_after = null;
            boolean EOFSeen = false;
            try {
                input_stream.readChar();
                input_stream.backup(1);
            } catch (java.io.IOException e1) {
                EOFSeen = true;
                error_after = curPos <= 1 ? "" : input_stream.GetImage();
                if (curChar == '\n' || curChar == '\r') {
                    error_line++;
                    error_column = 0;
                } else {
                    error_column++;
                }
            }
            if (!EOFSeen) {
                input_stream.backup(1);
                error_after = curPos <= 1 ? "" : input_stream.GetImage();
            }
            throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
        }
    }

    static void TokenLexicalActions(Token matchedToken) {
        switch (jjmatchedKind) {
            case 1:
                image.append(jjstrLiteralImages[1]);
                lengthOfMatch = jjstrLiteralImages[1].length();
                System.out.println("ABRE PARENTESES -> " + image);
                break;
            case 2:
                image.append(jjstrLiteralImages[2]);
                lengthOfMatch = jjstrLiteralImages[2].length();
                System.out.println("FECHA PARENTESES -> " + image);
                break;
            case 3:
                image.append(jjstrLiteralImages[3]);
                lengthOfMatch = jjstrLiteralImages[3].length();
                System.out.println("E -> " + image);
                break;
            case 4:
                image.append(jjstrLiteralImages[4]);
                lengthOfMatch = jjstrLiteralImages[4].length();
                System.out.println("OU -> " + image);
                break;
            case 5:
                image.append(jjstrLiteralImages[5]);
                lengthOfMatch = jjstrLiteralImages[5].length();
                System.out.println("NEGACAO -> " + image);
                break;
            case 6:
                image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
                System.out.println("VARIAVEL -> " + image);
                break;
            case 7:
                image.append(jjstrLiteralImages[7]);
                lengthOfMatch = jjstrLiteralImages[7].length();
                System.out.println("BI IMPLICA -> " + image);
                break;
            case 8:
                image.append(jjstrLiteralImages[8]);
                lengthOfMatch = jjstrLiteralImages[8].length();
                System.out.println("IMPLICA -> " + image);
                break;
            default:
                break;
        }
    }

    static private void jjCheckNAdd(int state) {
        if (jjrounds[state] != jjround) {
            jjstateSet[jjnewStateCnt++] = state;
            jjrounds[state] = jjround;
        }
    }

    static private void jjAddStates(int start, int end) {
        do {
            jjstateSet[jjnewStateCnt++] = jjnextStates[start];
        } while (start++ != end);
    }

    static private void jjCheckNAddTwoStates(int state1, int state2) {
        jjCheckNAdd(state1);
        jjCheckNAdd(state2);
    }

    /**
     * Constructor.
     */
    public LogicoLexicoTokenManager(SimpleCharStream stream) {

        if (input_stream != null) {
            throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
        }

        input_stream = stream;
    }

    /**
     * Constructor.
     */
    public LogicoLexicoTokenManager(SimpleCharStream stream, int lexState) {
        ReInit(stream);
        SwitchTo(lexState);
    }

    /**
     * Reinitialise parser.
     */
    static public void ReInit(SimpleCharStream stream) {
        jjmatchedPos = jjnewStateCnt = 0;
        curLexState = defaultLexState;
        input_stream = stream;
        ReInitRounds();
    }

    static private void ReInitRounds() {
        int i;
        jjround = 0x80000001;
        for (i = 2; i-- > 0;) {
            jjrounds[i] = 0x80000000;
        }
    }

    /**
     * Reinitialise parser.
     */
    static public void ReInit(SimpleCharStream stream, int lexState) {
        ReInit(stream);
        SwitchTo(lexState);
    }

    /**
     * Switch to specified lex state.
     */
    static public void SwitchTo(int lexState) {
        if (lexState >= 1 || lexState < 0) {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
        } else {
            curLexState = lexState;
        }
    }

    /**
     * Lexer state names.
     */
    public static final String[] lexStateNames = {
        "DEFAULT",};
    static final long[] jjtoToken = {
        0x1ffL,};
    static final long[] jjtoSkip = {
        0x1e00L,};
    static protected SimpleCharStream input_stream;

    static private final int[] jjrounds = new int[2];
    static private final int[] jjstateSet = new int[2 * 2];

    private static final StringBuilder jjimage = new StringBuilder();
    private static StringBuilder image = jjimage;
    private static int jjimageLen;
    private static int lengthOfMatch;

    static protected char curChar;
}
