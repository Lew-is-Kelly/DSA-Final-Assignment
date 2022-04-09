import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * DSA 2 Final Assignment. Bus system for Vancouver. Can (1) do nothing, (2) Search for a bus stop by name or the first
 * few letters of the name and (3) also do nothing. Runs a loop and only terminates when the user enters 'quit' or
 * 'exit' and is not case-sensitive at any time.
 *
 * @author Lewis Kelly 20335015
 */
public class Main
{
    static ArrayList<Integer> stops;
    static TST<String> searchTree;
    static boolean quit = false;
    static String divString =
            "+--------------------------------------------------------------------------------------------------------------------------------------------------------------------+";
    // For making things look pretty

    static Scanner input = new Scanner(System.in);

    /**
     * Searches for a bus stop by its name
     */
    public static void searchBusStopsByName()
    {
        boolean getOut = false;
        while (!getOut) {
            System.out.printf("""
                              %s
                              | Please enter the name or the first few letters of the stop you would like to search for or "Back" to return to the menu:""",
                              divString);
            String inLine = input.nextLine();
            System.out.println(divString);
            if (inLine.equalsIgnoreCase("Back")) {
                getOut = true;
                System.out.println("Returning to menu");
            } else if (inLine.equalsIgnoreCase("quit") || inLine.equalsIgnoreCase("exit")) {
                getOut = true;
                quit = true;
            } else {
                System.out.printf("""
                                  | Now searching for %s
                                  %s
                                  """, inLine.toUpperCase(), divString);
                int numOfStops = 0;
                for (String stop : searchTree.keysWithPrefix(inLine.toUpperCase())) {
                    numOfStops++;
                    System.out.println(stop);
                }
                if (numOfStops < 1) {
                    System.out.printf("No stops match %s. Sorry.\n", inLine.toUpperCase());
                } else {
                    System.out.printf("Found %d stops.\n", numOfStops);
                }
            }
        }
    }

    /**
     * Finds the shortest path between two bus stops.
     */
    public static void findShortestPath()
    {
        boolean getOut = false;
        while (!getOut) {
            System.out.printf("""
                              %s
                              | Please enter the stop ID of the stop you wish to depart from or "Back" to go back:""",
                              divString);
            String firstStop = input.nextLine();
            System.out.println(divString);
            if (firstStop.equalsIgnoreCase("Back")) {
                getOut = true;
                System.out.println("Returning to menu");
            } else if (firstStop.equalsIgnoreCase("Quit") || firstStop.equalsIgnoreCase("Exit")) {
                getOut = true;
                quit = true;
            } else {
                System.out.print("""
                                 | Please enter the stop ID of the stop you wish to arrive at:""");
                String secondStop = input.nextLine();
                System.out.println(divString);
                if (secondStop.equalsIgnoreCase("Back")) {
                    getOut = true;
                    System.out.println("Returning to menu");
                } else if (secondStop.equalsIgnoreCase("Quit") || secondStop.equalsIgnoreCase("Exit")) {
                    getOut = true;
                    quit = true;
                } else {
                    System.out.println(firstStop + secondStop);
                    System.out.println("Not Yet Implemented");
                }
            }
        }
    }

    public static void main(String[] args)
    {
        constructTST.stopsToTST("stops.txt");

        System.out.printf("""
                          %s
                          | Welcome to the some sort of Vancouver bus system. I'm not quite sure what its meant to do yet because I have not yet fully read the brief. This text is temporary.
                          %s
                          """, divString, divString);
        while (!quit) {
            System.out.printf("""
                              | Would you like to (1) do nothing, (2), Search for a buss stop or (3) do nothing.
                              | Please enter the number (1, 2 or 3) for the functionality you would like or "Quit"/"Exit" to quit.
                              %s
                              | What would you like to do?:""", divString);
            String inLine = input.nextLine();
            System.out.println(divString);
            switch (inLine.toUpperCase()) {
                case "1", "SHORTEST" -> {
                    System.out.println("Find shortest path");
                    findShortestPath();
                }
                case "2", "SEARCH" -> {
                    System.out.println("Search for bus stop");
                    searchBusStopsByName();
                }
                case "3" -> System.out.println("Also no functionality yet");
                case "QUIT", "EXIT" -> quit = true;
                default -> System.out.printf("""
                                             | Please enter a either 1, 2, 3 or "Quit/Exit"
                                             %s
                                             """, divString);
            }
        }
        input.close();
        System.out.println("Goodbye!");
    }
}
