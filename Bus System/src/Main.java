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
    static ArrayList<String> stopTimes;
    static EdgeWeightedDigraph stopGraph;
    static ArrayList<Integer> stops;
    static TST<String> searchTree;
    static boolean quit = false;
    static String divString =
            "+--------------------------------------------------------------------------------------------------------------------------------------------------------------------+";
    // For making things look pretty

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        Constructors.readAll("stops.txt", "transfers.txt", "stop_times.txt");

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
                    System.out.println("Shortest Path Finder");
                    ShortestPath.findShortestPath();
                }
                case "2", "SEARCH" -> {
                    System.out.println("Bus Stop Search");
                    SearchStop.searchBusStopsByName();
                }
                case "3", "ARRIVAL" -> {
                    System.out.println("No functionality yet");
                    ArrivalTime.getArrivalTimes();
                }
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
