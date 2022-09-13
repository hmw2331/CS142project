package puzzles.crossing;

import puzzles.common.solver.Configuration;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

/**
 * represents individual crossing puzzle configurations
 *
 * @author harborwolff
 */
public class CrossingConfig implements Configuration {

    /** The number of wolves on the left */
    private int numLeftWolves;
    /** The number of pups on the left */
    private int numLeftPups;
    /** The number of wolves on the right */
    private int numRightWolves;
    /** The number of pups on the right */
    private int numRightPups;
    /** Whether or not the boat is at the right side of the river */
    private boolean boatAtRight;

    public CrossingConfig(int lp, int lw, int rp, int rw, boolean boatAtRight){
        numLeftWolves = lw;
        numLeftPups = lp;
        numRightWolves = rw;
        numRightPups = rp;
        this.boatAtRight = boatAtRight;
    }

    /**
     * Checks if the current config is the solution
     *
     * @return whether or not the current config is a solution to the puzzle
     */
    @Override
    public boolean isSolution() {
        if (numLeftPups == 0 && numLeftWolves == 0) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Finds all possible neighbor configurations to this
     *
     * @return a collection of all neighbor configs
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        LinkedList< Configuration > neighbors = new LinkedList<>();

        //left side moves
        //move one pup
        if(!boatAtRight) {
            //move 2 pups
            if (numLeftPups >= 2) {
                CrossingConfig two = new CrossingConfig(numLeftPups - 2, numLeftWolves, numRightPups + 2, numRightWolves, true);
                neighbors.add(two);
            }

            //move one pup
            if (numLeftPups >= 1) {
                CrossingConfig one = new CrossingConfig(numLeftPups - 1, numLeftWolves, numRightPups + 1, numRightWolves, true);
                neighbors.add(one);
            }

            //move 1 wolf
            if (numLeftWolves >= 1) {
                CrossingConfig three = new CrossingConfig(numLeftPups, numLeftWolves - 1, numRightPups, numRightWolves + 1, true);
                neighbors.add(three);
            }

        }else {
            //move 2 pups
            if (numRightPups >= 2) {
                CrossingConfig five = new CrossingConfig(numLeftPups + 2, numLeftWolves, numRightPups - 2, numRightWolves, false);
                neighbors.add(five);
            }

            //move 1 pup
            if (numRightPups >= 1) {
                CrossingConfig four = new CrossingConfig(numLeftPups + 1, numLeftWolves, numRightPups - 1, numRightWolves, false);
                neighbors.add(four);
            }

            //move 1 wolf
            if (numRightWolves >= 1) {
                CrossingConfig six = new CrossingConfig(numLeftPups, numLeftWolves + 1, numRightPups, numRightWolves - 1, false);
                neighbors.add(six);
            }

        }

        return neighbors;
    }

    /**
     * Guess
     *
     * @param o the comparison object
     * @return whether or not they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrossingConfig that = (CrossingConfig) o;
        return numLeftWolves == that.numLeftWolves && numLeftPups == that.numLeftPups && numRightWolves == that.numRightWolves && numRightPups == that.numRightPups && boatAtRight == that.boatAtRight;
    }

    /**
     * Generates a hashcode for the config
     *
     * @return the config's hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(numLeftWolves, numLeftPups, numRightWolves, numRightPups, boatAtRight);
    }

    /**
     * Gets a string representing the current config
     *
     * @return a string representing the position of each type of animal
     */
    public String toString(){
        if (boatAtRight){
            return " left=[" + numLeftPups + ", " + numLeftWolves + "], right=[" + numRightPups + ", " + numRightWolves + "] (BOAT)";
        }else{
            return " (BOAT) left=[" + numLeftPups + ", " + numLeftWolves + "], right=[" + numRightPups + ", " + numRightWolves + "]";
        }
    }
}
