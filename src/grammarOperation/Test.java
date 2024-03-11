package grammarOperation;

import java.io.*;
import grammar.*;

public class Test {

    public static void main(String[] args) {
        Grammar grammar;

        try {
            GrammarReader inp = new GrammarReader(new FileReader(args[0]));
            grammar = inp.read();
        } catch (GrammarException e) {
            System.err.println("Error(" + e.getLineNumber() + ") " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        grammar.dump(System.out);

        GrammarOpsKt go = new GrammarOpsKt(grammar);

        /* first step, computing nonterminals that can generate empty word */
        for (Nonterminal nt : go.getEmptyNonterminals()) {
            System.out.print(nt.getName() + " ");
        }
        System.out.println();

    }
}
