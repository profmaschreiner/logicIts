package logicits.javaccList;

/* Generated By:JJTree: Do not edit this line. ASTprogram.java Version 6.0 */
 /* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTprogram extends SimpleNode {

    public ASTprogram(int id) {
        super(id);
    }

    public ASTprogram(LogicoLexico p, int id) {
        super(p, id);
    }

    /**
     * Accept the visitor. *
     */
    public Object jjtAccept(LogicoLexicoVisitor visitor, Object data) {

        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=685c3a09945f8b21a8a1d4bcc4cbed18 (do not edit this line) */
