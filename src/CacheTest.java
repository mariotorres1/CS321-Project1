import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;

/**
 * Test class reads a file and test one-level or two-level Cache implementation
 * and prints how many references and hits each cache have. It also prints the
 * hit ratio for each cache level and global ratio.
 *
 * @author Mario Torres
 *
 */
public class CacheTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        File input;
        if (args.length == 3) {
            // creating one-level-cache
            if (args[0].equals("1")) {
                if (args[1].matches("[0-9]+")) {
                    String fileName = args[2];
                    int size = Integer.parseInt(args[1]);
                    try {
                        input = new File(fileName);
                        oneLevelCache(size, input);
                    } catch (IOException e) {
                        System.out.println("File not Found");
                        printUsage();
                        System.exit(1);
                    }
                } else {
                    printUsage();
                    System.exit(1);
                }
            } else {
                printUsage();
                System.exit(1);
            }

        } else if (args.length == 4) {
            // creating two-level-cache
            if (args[0].equals("2")) {
                if (args[1].matches("[0-9]+") && args[2].matches("[0-9]+")) {
                    String fileName = args[3];
                    int size1 = Integer.parseInt(args[1]);
                    int size2 = Integer.parseInt(args[2]);
                    if (size1 < size2) {
                        try {
                            input = new File(fileName);
                            twoLevelCache(size1, size2, input);
                        } catch (IOException e) {
                            System.out.println("File not Found");
                            printUsage();
                            System.exit(1);
                        }
                    } else {
                        System.out.println("\nLevel-one-Cache size must be lower than Level-two-Cache size\n");
                        printUsage();
                        System.exit(1);
                    }
                } else {
                    printUsage();
                    System.exit(1);
                }
            } else {
                printUsage();
                System.exit(1);
            }
        } else {
            printUsage();
            System.exit(1);
        }
        long end = System.currentTimeMillis();
        System.out.println("\nTime elapsed: " + (end - start) + " milliseconds");
    }

    /**
     * Tests two-level Cache implementation
     *
     * @param size1 the size of the first-level cache
     * @param size2 the size of the second-level cache
     * @param input the file to be read
     */
    private static void twoLevelCache(int size1, int size2, File input) throws FileNotFoundException {
        Cache<String> levelOneCache = new Cache<>(size1);
        System.out.println("First Level cache with " + size1 + " entries has been created.");
        Cache<String> levelTwoCache = new Cache<>(size2);
        System.out.println("Second Level cache with " + size2 + " entries has been created.");
        System.out.println("-------------------------------------------------------------------");

        Scanner scan = new Scanner(input);
        int NH1 = 0;
        int NH2 = 0;
        int NR1 = 0;
        int NR2 = 0;
        String fileString;

        // starts reading the file
        while (scan.hasNext()) {
            NR1++;
            fileString = scan.next();

            // checks to see if in cache1, if so increments NH1 and also increments NR1 while moving to top of cache1 and cache2
            if (levelOneCache.getCacheLinkedList().contains(fileString)) {
                NH1++;
                int index = levelOneCache.getCacheLinkedList().indexOf(fileString);
                levelOneCache.removeFromCache(index);
                levelOneCache.addToCache(fileString);

                levelTwoCache.removeFromCache(index);
                levelTwoCache.addToCache(fileString);
            } else if (levelTwoCache.getCacheLinkedList().contains(fileString)) { //checks to see if in cache2, if so increments NH2 and also increments NR2 while moving to top of cache1 and cache 2
                NR2++;
                NH2++;
                int index = levelTwoCache.getCacheLinkedList().indexOf(fileString);
                levelTwoCache.removeFromCache(index);
                levelTwoCache.addToCache(fileString);

                if (levelOneCache.cacheFull()) {
                    levelOneCache.removeLastCache();
                    levelOneCache.addToCache(fileString);
                } else {
                    levelOneCache.addToCache(fileString);
                }
            } else { // if it's not in cache1 or cache2, increments NH2 and adds to top of cache1 and cache2
                NR2++;
                if (levelOneCache.cacheFull()) {
                    levelOneCache.removeLastCache();
                    levelOneCache.addToCache(fileString);
                } else {
                    levelOneCache.addToCache(fileString);
                }
                if (levelTwoCache.cacheFull()) {
                    levelTwoCache.removeLastCache();
                    levelTwoCache.addToCache(fileString);
                } else {
                    levelTwoCache.addToCache(fileString);
                }
            }
        }
        // prints data for two-level cache
        printTwoLevelCache(NR1, NR2, NH1, NH2);
    }

    /**
     * Tests a one-level Cache implementation
     *
     * @param size is the size of the cache
     * @param input is the file to be read
     */
    private static void oneLevelCache(int size, File input) throws FileNotFoundException {
        Cache<String> oneLevelCache = new Cache<>(size);
        System.out.println("One-Level cache with " + size + " entries has been created.\n");

        Scanner scan = new Scanner(input);
        int NH = 0;
        int NR = 0;
        String fileString;

        // starts to read the file, increments NR
        while (scan.hasNext()) {
            fileString = scan.next();
            NR++;

            // checks to see if value is in cache1, increments NH and if in, moves to top of cache
            if (oneLevelCache.getCacheLinkedList().contains(fileString)) {
                int index = oneLevelCache.getCacheLinkedList().indexOf(fileString);
                oneLevelCache.removeFromCache(index);
                oneLevelCache.addToCache(fileString);
                NH++;
            } else { // if not in cache, adds to top of cache
                if (oneLevelCache.cacheFull()) {
                    oneLevelCache.removeLastCache();
                    oneLevelCache.addToCache(fileString);
                } else {
                    oneLevelCache.addToCache(fileString);
                }
            }
        }
        // prints data for one-level cache
        printLevelOneCache(NH, NR);
    }

    /**
     * Prints one-level cache references, hits and ratio.
     *
     * @param NH (number of hits) is total hits in one-level cache
     * @param NR (number of references) is total references in one-level cache
     */
    private static void printLevelOneCache(int NH, int NR) {
        DecimalFormat df = new DecimalFormat("0.0000");
        double HR;
        HR = (double) NH / NR;
        System.out.println("Total References: " + NR + "\nTotal cache hits: " + NH
                + "\nCache hit ratio: " + df.format(HR));
    }

    /**
     * Prints the Two-level Cache references, hits and ratios.
     *
     * @param NR1 is the number of references for level-one cache search
     * @param NR2 is the number of references for level-two cache search
     * @param NH1 is level-one cache hits
     * @param NH2 is level-two cache hits
     */
    private static void printTwoLevelCache(int NR1, int NR2, int NH1, int NH2) {
        DecimalFormat df = new DecimalFormat("0.0000");
        double HR1;
        double HR2;
        double HR;
        int totalHit;
        HR1 = (double) NH1 / NR1;
        HR2 = (double) NH2 / NR2;
        totalHit = (NH1 + NH2);
        HR = (double) totalHit / NR1;

        System.out.println("Total number of References: " + NR1 + "\nTotal number of cache hits: " + totalHit + "\nGlobal hit ratio: "
                + df.format(HR));
        System.out.println("\nThe number of 1st-level references: " + NR1 + "\nThe number of 1st-level cache hits: "
                        + NH1 + "\nThe 1st-level hit ratio: " + df.format(HR1));
        System.out.println("\nThe number of 2nd-level references: " + NR2 + "\nThe number of 2nd-level cache hits: "
                        + NH2 + "\nThe 2nd-level hit ratio: " + df.format(HR2));
    }

    /**
     * Prints Usage
     */
    private static void printUsage() {
        System.out.println("Usage: $ java Test [1] [Level-one-Cache size] [filename]");
        System.out.println("Usage: $ java Test [2] [Level-one-Cache-size] [Level-two-Cache-size] [filename]");
        System.out.println("1 for one-level-Cache \n2 for two-level Cache");
    }
}