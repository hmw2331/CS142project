package puzzles.strings;

import puzzles.common.solver.Configuration;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

/**
 * the class that represents individual string configurations
 *
 * @author harborwolff
 */
public class StringConfig implements Configuration {

    /** The current word */
    private String word;
    /** The target word */
    private static String target;

    /**
     * Constructor for a configuration
     *
     * @param str the starting word
     * @param target the target word
     */
    public StringConfig(String str, String target){
        word = str;
        this.target = target;
    }

    /**
     * Checks to see if the current word is the target
     *
     * @return if its the solution
     */
    @Override
    public boolean isSolution() {
        return word.equals(target);
    }

    /**
     * Gets all possible words that can be accessed from the current word
     *
     * @return a collection of all possible neighbors
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        LinkedList< Configuration > neighbors = new LinkedList<>();

        //for each c case: pull out, inc 1, squeeze, add to neighb, dec 1, squeeze, add to neigh

        for(int i = 0; i < word.length(); i++){
            char[] wordArr = word.toCharArray();

            //case A
            if(wordArr[i] == 'A'){
                //increment
                wordArr[i] = 'Z';
                StringBuilder inc1 = new StringBuilder();
                for(char c: wordArr){
                    inc1.append(c);
                }
                neighbors.add(new StringConfig(inc1.toString(), target));
                wordArr[i] = 'A';

                //decrement
                wordArr[i] = 'B';
                StringBuilder inc2 = new StringBuilder();
                for(char c: wordArr){
                    inc2.append(c);
                }
                neighbors.add(new StringConfig(inc2.toString(), target));
                wordArr[i] = 'A';
            }

            //case Z
            if(wordArr[i] == 'Z'){
                //increment
                wordArr[i] = 'A';
                StringBuilder inc3 = new StringBuilder();
                for(char c: wordArr){
                    inc3.append(c);
                }
                neighbors.add(new StringConfig(inc3.toString(), target));
                wordArr[i] = 'Z';

                //decrement
                wordArr[i] = 'Y';
                StringBuilder inc4 = new StringBuilder();
                for(char c: wordArr){
                    inc4.append(c);
                }
                neighbors.add(new StringConfig(inc4.toString(), target));
                wordArr[i] = 'Z';
            }

            //else
            //increment
            wordArr[i] += 1;
            StringBuilder inc5 = new StringBuilder();
            for(char c: wordArr){
                inc5.append(c);
            }
            neighbors.add(new StringConfig(inc5.toString(), target));
            wordArr[i] -= 1;

            //decrement
            wordArr[i] -= 1;
            StringBuilder inc6 = new StringBuilder();
            for(char c: wordArr){
                inc6.append(c);
            }
            neighbors.add(new StringConfig(inc6.toString(), target));
            wordArr[i] += 1;
        }

        return neighbors;
    }

    /**
     * Standard equals method
     *
     * @param o other object
     * @return whether or not o is eaual to this
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringConfig that = (StringConfig) o;
        return Objects.equals(word, that.word);
    }

    /**
     * Dude I don't know I auto-generated these
     *
     * @return the Configuration's hashcode
     */
    @Override
    public int hashCode() {
        return word.hashCode();
    }

    /**
     * Returns a string representation of the word
     *
     * @return the string of the word
     */
    @Override
    public String toString() {
        return word;
    }
}
