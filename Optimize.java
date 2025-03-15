/**
 * Assignment 6 Task 2
 * 16 May 2023
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The hash function contained in the HashTable class uses an array of 
 * 9 weights (with values 0..4) to create a linear combination of 
 * the 9 characters in a student ID
 * This task concerns computing weights that optimise insertion of a 
 * specific list of names in an LPHashTable. 
 * Insertion is optimised if it uses the least number of probes. 
 * There may be more than one combination of weights that achieve this.
 */
public class Optimize {

    public static void main(String[] args) {
        ArrayList<int[]> nums = genNums(new int[]{}, 9);    // generates the array
        int lowest = Integer.MAX_VALUE;     // tracks lowest probe count
        int lowestCountNums = 0;    // number of combos to achieve lowest probe 

        for (int[] combination : nums) {
            LPHashTable hash = new LPHashTable(37);     // new hash table generated for each combo
            hash.setWeights(combination);

            try (BufferedReader r = new BufferedReader(new FileReader("src/mydata.txt"))) {
                String line;
                while ((line = r.readLine()) != null) {
                    hash.insert(line);  // inserts read line into hashtable
                }
                int probeCount = hash.getProbeCount();

                if (probeCount < lowest) {
                    lowest = probeCount;    // updates to new lowest
                    lowestCountNums = 1;
                } 
                else if (probeCount == lowest) {
                    lowestCountNums++;
                }

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }

        writeToFile(lowest, lowestCountNums);
    }

    // writes contents to file
    public static void writeToFile(int lowest, int lowestCountNums) {
        try (FileWriter w = new FileWriter(new File("src/results.txt"))) {
            w.write(lowest + " " + lowestCountNums);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<int[]> genNums(int[] prefix, int leftover) {
        ArrayList<int[]> nums = new ArrayList<>();
        if (leftover == 0) {
            nums.add(prefix);   
            return nums;
        }
        // recursively gens all possible number combos
        for (int i = 0; i <= 4; i++) {
            int[] newPrefix = Arrays.copyOf(prefix, prefix.length + 1);
            newPrefix[newPrefix.length - 1] = i;
            nums.addAll(genNums(newPrefix, leftover - 1));
        }
        return nums;
    }
}
