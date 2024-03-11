package grammar;

import java.util.*;

public class Rule {

    public Rule(Nonterminal lhs) {
        this.lhs = lhs;
    }

    public Nonterminal getLHS() {
        return lhs;
    }

    public List<Symbol> getRHS() {
        return rhs;
    }

    public void addSymbol(Symbol s) {
        rhs.add(s);
    }

    private final Nonterminal lhs;

    private final List<Symbol> rhs = new ArrayList<Symbol>();
}
