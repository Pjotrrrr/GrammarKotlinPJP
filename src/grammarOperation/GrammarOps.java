package grammarOperation;

import grammar.*;
import java.util.*;

public class GrammarOps {

    Grammar g;
    Set<Nonterminal> emptyNonterminals;
    public Set<Nonterminal> getEmptyNonterminals() {
        return emptyNonterminals;
    }

    public GrammarOps(Grammar g) {
        this.g = g;
        compute_empty();
    }

    private void compute_empty() {
        emptyNonterminals = new TreeSet<Nonterminal>();
        // TODO: start here
    }


}
