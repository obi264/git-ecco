package at.jku.isse.gitecco.core.types;

import at.jku.isse.gitecco.core.preprocessor.util.org.anarres.cpp.featureExpr.*;
import at.jku.isse.gitecco.core.tree.nodes.DefineNodes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class to represent a feature.
 */
public class Feature implements Comparable<Feature> {
    private final String name;

    public Feature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Compares a #define or #undef to this feature
     * @param n
     * @return
     */
    public boolean compareToDefine(DefineNodes n) {
        return this.name.equals(n.getMacroName());
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Feature || o instanceof TraceableFeature) {
            return equals(((Feature) o));
        }
        return false;
    }

    private boolean equals(Feature f) {
        return this.getName().equals(f.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int compareTo(Feature o) {
        return this.name.compareTo(o.name);
    }


    /**
     * Extracts all features from a given condition string
     * @param condition the condition string
     * @return A Set of types feature
     */
    public static Set<Feature> parseCondition(String condition) {
        Set<Feature> ret = new HashSet<>();

        FeatureExpressionParser fep = new FeatureExpressionParser(condition);
        FeatureExpression root = fep.parse();

        traverse(root, ret);

        return ret;
    }

    /**
     * Helper Method
     * Traverses the expression tree which was created before by the FeatureExpressionParser.
     *
     * @param expr the expression tree to be parsed.
     */
    private static void traverse(FeatureExpression expr, Set<Feature> features) {
        if (expr == null) return;

        if (expr instanceof Name) {
            features.add(new Feature(((Name) expr).getToken().getText()));
        } else if (expr instanceof CondExpr) {
            CondExpr e = (CondExpr) expr;
            //idea: parse that created expression and attach it instead of the CondExpr and continue to traverse again.
            String cond = "(!("+e.getExpr()+")||("+e.getThenExpr()+"))&&(("+e.getExpr()+")||("+e.getElseExpr()+"))";
            traverse(new FeatureExpressionParser(cond).parse(), features);
        } else if (expr instanceof PrefixExpr) {
            traverse(((PrefixExpr) expr).getExpr(), features);
            traverse(((PrefixExpr) expr).getOperator(), features);
        } else if (expr instanceof InfixExpr) {
            traverse(((InfixExpr) expr).getLeftHandSide(), features);
            traverse(((InfixExpr) expr).getRightHandSide(), features);
            traverse(((InfixExpr) expr).getOperator(), features);
        } else if (expr instanceof ParenthesizedExpr) {
            traverse(((ParenthesizedExpr) expr).getExpr(), features);
        }
    }

}
