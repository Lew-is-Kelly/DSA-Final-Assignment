import edu.princeton.cs.algs4.*;

import java.io.*;
import java.util.*;

/**
 * Class that contains methods to read in the data files and populate the data structures.
 *
 * @author Lewis Kelly 20335015
 */
public class Constructors
{
    /**
     * Calls all the other read functions, so I can call one function in main.
     *
     * @param stopsFileName Name of the file that contains the stop info (stops.txt)
     * @param transFileName Name of the file that contains the transfer info (transfers.txt)
     * @param timesFileName Name of the file that contains the stop times info (stop_times.txt)
     */
    public static void readAll(String stopsFileName, String transFileName, String timesFileName)
    {
        System.out.printf("%s\n| Reading Files...\n", Main.divString);
        readStops(stopsFileName);
        readTransfers(transFileName);
        readTimes(timesFileName);
        System.out.println("| Done!");
    }

    /**
     * Takes the stop information from the file passed and creates a TST (Ternary Search Tree) to enable efficient
     * searching of the stop information.
     *
     * @param fileName Name of the file containing the stop information
     */
    public static void readStops(String fileName)
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
                String trueName = Functions.getTrueName(currLine[2]);
                currLine[2] = trueName;
                Functions.rotateLeftBy(currLine, 2);
                String correctLine = Functions.makeNewString(currLine, ",");
                Main.searchTree.put(correctLine, Integer.toString(pos));
                pos++;
            }
            Collections.sort(Main.stops);
            Main.stopGraph = new EdgeWeightedDigraph(Main.stops.size());
            file.close();
        } catch (FileNotFoundException e) {
            System.err.printf("| %s not found.\n", fileName);
            e.printStackTrace();
        }
    }

    /**
     * Reads the data from transfers.txt and adds the information into the stopsGraph EdgeWeightedDiGraph
     *
     * @param fileName Name of the file that contains the transfer info in this case it will always be transfers.txt
     */
    public static void readTransfers(String fileName)
    {
        try {
            if (fileName == null) {
                return;
            }

            Scanner file = new Scanner(new FileReader(fileName));
            file.nextLine();
            while (file.hasNextLine()) {
                String[] currLine = file.nextLine().split(",");

                int valOne = Functions.binarySearch(Integer.parseInt(currLine[0]));
                int valTwo = Functions.binarySearch(Integer.parseInt(currLine[1]));

                DirectedEdge newEdge;

                if (currLine[2].equals("0")) {
                    newEdge = new DirectedEdge(valOne, valTwo, 2);
                } else {
                    int cost = (Integer.parseInt(currLine[3]) / 100);
                    newEdge = new DirectedEdge(valOne, valTwo, cost);
                }
                Main.stopGraph.addEdge(newEdge);
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.err.printf("| %s not found.\n", fileName);
            e.printStackTrace();
        }
    }

    /**
     * Read in the times from stop_times.txt and puts them into the stopGraph EdgeWeightedDiGraph
     *
     * @param fileName name of the file, in our case its stop_times.txt
     */
    static void readTimes(String fileName)
    {
        if (fileName == null) {
            return;
        }

        try {
            Scanner file = new Scanner(new FileReader(fileName));
            Main.stopTimes = new ArrayList<>();
            file.nextLine();

            String[] lineOne = specialLineReader(file);

            while (file.hasNextLine()) {
                String[] lineTwo = specialLineReader(file);

                if (lineOne[0].equals(lineTwo[0])) {
                    int valOne = Functions.binarySearch(Integer.parseInt(lineOne[3]));
                    int valTwo = Functions.binarySearch(Integer.parseInt(lineTwo[3]));
                    DirectedEdge newEdge = new DirectedEdge(valOne, valTwo, 1);
                    Main.stopGraph.addEdge(newEdge);
                }
                lineOne = lineTwo;
            }

            Main.stopTimes.sort((o1, o2) -> {
                String[] lineSplit = o1.split(",");
                String[] secondLineSplit = o2.split(",");
                return lineSplit[0].compareTo(secondLineSplit[0]);
            });

            file.close();
        } catch (FileNotFoundException e) {
            System.err.printf("| %s not found.\n", fileName);
            e.printStackTrace();
        }
    }

    /**
     * Parses up the line read in from the file and adds it to the stopTimes ArrayList then returns the line that was
     * parsed
     *
     * @param file A scanner object that reads in the file
     * @return String array that has the line that is read in
     */
    static String[] specialLineReader(Scanner file)
    {
        String inLine = file.nextLine();

        String[] fileLine = inLine.split(",");
        String[] timeLine = fileLine[1].split(":");

        timeLine[0] = timeLine[0].trim();

        if (Integer.parseInt(timeLine[0]) >= 0 && Integer.parseInt(timeLine[0]) <= 23) { // Check if the line is valid
            Main.stopTimes.add(inLine);
        }

        return fileLine;
    }
}
