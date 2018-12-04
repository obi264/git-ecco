package at.jku.isse.gitecco.tree.visitor;

import at.jku.isse.gitecco.tree.nodes.*;

public class ValidateChangeVisitor implements TreeVisitor {
    @Override
    public void visit(RootNode n) {

    }

    @Override
    public void visit(BinaryFileNode n) {

    }

    @Override
    public void visit(SourceFileNode n) {

    }

    @Override
    public void visit(ConditionBlockNode n) {
        if((n.getElseBlock() != null && n.getElseBlock().isChanged()) || n.getIfBlock().isChanged()) {
            n.setChanged();
            return;
        }
        for (IFCondition elseIfBlock : n.getElseIfBlocks()) {
            if(elseIfBlock.isChanged()) {
                n.setChanged();
                return;
            }
        }
    }

    @Override
    public void visit(IFCondition c) {

    }

    @Override
    public void visit(IFDEFCondition c) {

    }

    @Override
    public void visit(IFNDEFCondition c) {

    }

    @Override
    public void visit(ELSECondition c) {

    }
}
