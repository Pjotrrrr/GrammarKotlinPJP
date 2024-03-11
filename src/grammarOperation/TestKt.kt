package grammarOperation

import grammar.Grammar
import grammar.GrammarException
import grammar.GrammarReader
import grammar.Nonterminal
import java.io.FileReader
import java.io.IOException


class TestKt {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val grammar: Grammar = try {
                val inp = GrammarReader(FileReader(args[0]))
                inp.read()
            } catch (e: GrammarException) {
                System.err.println("Error(" + e.lineNumber + ") " + e.message)
                return
            } catch (e: IOException) {
                System.err.println("Error: " + e.message)
                return
            }
            grammar.dump(System.out)
            val go = GrammarOpsKt(grammar)

            /* first step, computing nonterminals that can generate empty word */
            println("\nNon-terminals that can generate empty word are: ")
            var emptyNonterminals = go.emptyNonterminals
            if (emptyNonterminals != null) {
                for (nt in emptyNonterminals) {
                    print(nt.name + " ")
                }
            }
            println()

            var firstSetSymbol = Nonterminal("A")
            println("\nFIRST set of ${firstSetSymbol.name} is:")
            val first = go.compute_first(firstSetSymbol)
            for (f in first) {
                print(f.name + " ")
            }
        }
    }
}
