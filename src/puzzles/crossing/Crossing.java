package puzzles.crossing;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.util.Collection;

/**
 * runs the crossing puzzle solver program
 *
 * @author Harbor Wolff
 */
public class Crossing {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(("Usage: java Crossing pups wolves"));
        } else {
            CrossingConfig game = new CrossingConfig(Integer.parseInt(args[0]), Integer.parseInt(args[1]), 0, 0, false);

            Solver crossSolver = new Solver();

            System.out.println("Pups: " + args[0] + " Wolves: " + args[1]);

            Collection<Configuration > solution = crossSolver.doStuff(game);

            System.out.println("Total configs: " + crossSolver.getTotalConfigs());
            System.out.println("Unique configs: " + crossSolver.getUniqueConfigs());


            if(solution.size() != 0){
                int counter = 0;
                for(Configuration c: solution){
                    System.out.println("Step " + counter + ": " + c.toString());
                    counter++;
                }
            }else{
                System.out.println("No such path");
            }

        }
    }
}
