package puzzles.strings;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

/**
 * runs the string puzzle solver program
 *
 * @author Harbor Wolff
 */
import java.util.Collection;

public class Strings {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(("Usage: java Strings start finish"));
        } else {
            StringConfig game = new StringConfig(args[0], args[1]);

            Solver stringSolver = new Solver();

            System.out.println("Start: "+ args[0] + " end: " + args[1]);

            Collection< Configuration > solution = stringSolver.doStuff(game);

            System.out.println("Total configs: " + stringSolver.getTotalConfigs());
            System.out.println("Unique configs: " + stringSolver.getUniqueConfigs());

            if(solution.size() != 0){
                int counter = 0;
                for(Configuration c: solution){
                    System.out.println("Step " + counter + ": " + c);
                    counter++;
                }
            }else{
                System.out.println("No such path");
            }
        }
    }
}
