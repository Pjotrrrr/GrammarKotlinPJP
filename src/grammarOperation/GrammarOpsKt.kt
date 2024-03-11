package grammarOperation

import grammar.Grammar
import grammar.Nonterminal
import grammar.Rule
import grammar.Symbol
import grammar.Terminal

class GrammarOpsKt(private var g: Grammar) {
    var emptyNonterminals: MutableSet<Nonterminal>? = null

    init {
        compute_empty()
    }

    fun compute_empty() {
        emptyNonterminals = mutableSetOf<Nonterminal>()
        // TODO: start here
        var rules = g.rules
        if (rules != null) {
            for (rule in rules) {
                if (rule.rhs.isEmpty()) {
                    emptyNonterminals!!.add(rule.lhs)
                }
            }

            val newEmptyNonterminals = mutableSetOf<Nonterminal>()
            for (rule in rules) {
                for (emptyNonterminal in emptyNonterminals!!) {
                    if (rule.rhs.contains(emptyNonterminal)) {
                        newEmptyNonterminals.add(rule.lhs)
                    }
                }
            }
            emptyNonterminals!!.addAll(newEmptyNonterminals)
        }
    }

    fun compute_first(symbol : Symbol) : MutableSet<Terminal> {
        var first = mutableSetOf<Terminal>()
        var rulesFromSymbol = g.rules.filter { it.lhs.name == symbol.name }

        for (rule in rulesFromSymbol) {
            first.addAll(computeFirstForRule(rule))
        }

        return first
    }

    private fun computeFirstForRule(rule : Rule) : MutableSet<Terminal> {
        var first = mutableSetOf<Terminal>()
        if (rule.rhs.isEmpty()) {
            return first
        }
        if (rule.rhs[0] is Terminal) {
            first.add(rule.rhs[0] as Terminal)
        }
        if (rule.rhs[0] is Nonterminal) {
            var rulesFromNonterminal = g.rules.filter { it.lhs == rule.rhs[0] as Nonterminal }
            var firstsFromRules = mutableSetOf<Terminal>()
            for (rule1 in rulesFromNonterminal) {
                firstsFromRules.addAll(computeFirstForRule(rule1))
            }
            first.addAll(firstsFromRules)
        }
        if (emptyNonterminals!!.contains(rule.rhs[0])) {
            var changedRule = rule
            changedRule.rhs.removeFirst()
            first.addAll(computeFirstForRule(changedRule))
        }
        return first
    }

}