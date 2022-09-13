package puzzles.common.solver;

import java.util.*;

/**
 * The generic DFS solver for the puzzles
 *
 * @author harborwolff
 */
public class Solver {
    /** The total configurations generated */
    private int totalConfigs = 1;
    /** The total unique configurations generated */
    private int uniqueConfigs = 1;

    public Solver(){};

    /**
     * Performs a DFS on the provided configuration, then finds the shortest path
     *
     * @param start the starting configuration
     * @return the shortest path, represented as a collection
     */
    public Collection< Configuration > doStuff(Configuration start){
        List< Configuration > queue = new LinkedList<>();
        queue.add(start);

        Map<Configuration, Configuration> predecessors = new HashMap<>();
        predecessors.put(start, start);

        Configuration end = null;
        while(!queue.isEmpty()){
            Configuration current = queue.remove(0);
            if(current.isSolution()){
                end = current;
                break;
            }
            for(Configuration neighb: current.getNeighbors()){
                totalConfigs++;
                if(!predecessors.containsKey(neighb)){
                    uniqueConfigs++;
                    predecessors.put(neighb, current);
                    queue.add(neighb);
                }
            }
        }

        List< Configuration > path = new LinkedList<>();
        if(predecessors.containsKey(end)){
            Configuration currenT = end;
            while(currenT != start){
                path.add( 0 , currenT);
                currenT = predecessors.get(currenT);
            }
            path.add(0, start);
        }
        return path;
    }

    /**
     * total configs accessor
     *
     * @return the total configs
     */
    public int getTotalConfigs() {
        return totalConfigs;
    }

    /**
     * total unique configs accessor
     *
     * @return the total unique configs
     */
    public int getUniqueConfigs() {
        return uniqueConfigs;
    }
}
