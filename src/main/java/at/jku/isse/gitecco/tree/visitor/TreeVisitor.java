package at.jku.isse.gitecco.tree.visitor;

import at.jku.isse.gitecco.tree.nodes.*;

public interface TreeVisitor {
    void visit(RootNode n);
    void visit(BinaryFileNode n);
    void visit(SourceFileNode n);
    void visit(ConditionBlockNode n);
    void visit(IFCondition c);
    void visit(IFDEFCondition c);
    void visit(IFNDEFCondition c);
    void visit(ELSECondition c);
}
