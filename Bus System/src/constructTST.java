import edu.princeton.cs.algs4.TST;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that makes a TST of the bus stops with the names of the stops as the first item in the line and certain
 * keywords shifted to the end of the stop name.
 *
 * @author Lewis Kelly 20335015
 */
public class constructTST
{
    /**
     * Takes a string containing the full name of a stop and returns the stop name with the keywords
     * "flagstop, wb, nb, sb, eb" removed from the start and added to the end of the name.
     *
     * @param name String containing full name of the bus stop
     * @return The correct name of the bus stop
     */
    public static String getTrueName(String name)
    {
        String[] rotateMe = name.split(" ");
        String[] skipStrings = {"flagstop", "wb", "nb", "sb", "eb"};
        int toRotateBy = 0;
        for (int i = 0; i <= 2; i++) {
            for (String skipString : skipStrings) {
                if (rotateMe.length > 2) {
                    if (rotateMe[i].equalsIgnoreCase(skipString)) {
                        toRotateBy++;
                    }
                }
            }
        }
        rotateLeftBy(rotateMe, toRotateBy);
        name = makeNewString(rotateMe, " ");
        return name.trim();
    }

    /**
     * Takes a string array and puts the elements closest to the 0th index at the end of the array.
     *
     * @param rotateMe   String array to be rotated
     * @param byThisMuch Number of elements you want removed from the front of the array and put on the end
     */
    public static void rotateLeftBy(String[] rotateMe, int byThisMuch)
    {
        for (int i = 0; i < byThisMuch; i++) {
            int j = 0;
            String putMeLast = rotateMe[0];
            for (; j < rotateMe.length - 1; j++) {
                rotateMe[j] = rotateMe[j + 1];
            }
            rotateMe[j] = putMeLast;
        }
    }

    /**
     * Creates string from an array of strings and separates elements of the array in the new string with given
     * delimiter.
     *
     * @param arrayForString an array of strings
     * @param delim          delimiter to separate elements of the array in the new string
     * @return a new string with elements from arrayForString separated by the string in delim
     */
    public static String makeNewString(String[] arrayForString, String delim)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (String curr : arrayForString) {
            stringBuilder.append(curr).append(delim);
        }
        return stringBuilder.toString();
    }

    /**
     * Takes the stop information from the file passed and creates a TST (Ternary Search Tree) to enable efficient
     * searching of the stop information.
     *
     * @param fileName Name of the file containing the stop information
     */
    public static void stopsToTST(String fileName)
    {
        try {
            if (fileName == null) {
                return;
            }

            Scanner file = new Scanner(new FileReader(fileName));
            file.nextLine();        // First line is redundant
            int pos = 0;
            Main.stops = new ArrayList<>();
            Main.searchTree = new TST<>();
            while (file.hasNextLine()) {
                String[] currLine = file.nextLine().split(",");
                Main.stops.add(Integer.parseInt(currLine[0]));
                String trueName = getTrueName(currLine[2]);
                currLine[2] = trueName;
                rotateLeftBy(currLine, 2);
                String correctLine = makeNewString(currLine, ",");
                Main.searchTree.put(correctLine, Integer.toString(pos));
                pos++;
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        }
    }
}
