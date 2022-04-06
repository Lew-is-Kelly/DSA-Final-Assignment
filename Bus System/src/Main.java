/**
 * @author Lewis Kelly 20335015
 */

import edu.princeton.cs.algs4.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;



public class Main
{
    static ArrayList<Integer> stops;
    static TST<String> searchTree;

    static Scanner input = new Scanner(System.in);

    public static void stopsToTST(String fileName)
    {
        try
        {
            if (fileName == null)
                return;

            Scanner file = new Scanner( new FileReader(fileName));
            file.nextLine();        // First line is redundant
            int pos = 0;
            stops = new ArrayList<>();
            searchTree = new TST<>();
            while(file.hasNextLine())
            {
                String[] currLine = file.nextLine().split(",");
                stops.add(Integer.parseInt(currLine[0]));
                String trueName = getTrueName(currLine[2]);
                currLine[2] = trueName;
                rotateLeftBy(currLine, 2);
                String correctLine = makeNewString(currLine, ",");
                searchTree.put(correctLine, Integer.toString(pos));
                pos++;
            }

        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found.");
            e.printStackTrace();
        }
    }

    public static String getTrueName(String name)
    {
        String[] fullName = name.split(" ");
        //TODO
        return "not yet implemented";
    }

    public static String makeNewString(String[] arr, String del)
    {
        //TODO
        return "not yet implemented";
    }

    public static void rotateLeftBy(String[] s, int amount)
    {
        //TODO
    }

    public static void searchBusStopsByName() throws FileNotFoundException
    {
        boolean getOut = false;
        while(!getOut)
        {
            System.out.println("Please enter the name or the first few letters of the stop you would like to search "
                    + "for or \"Go Back\" to return to the menu: ");
            String inLine = input.next();
            if(inLine.equalsIgnoreCase("Go Back"))
            {
                getOut = true;
            }
            else
            {
                System.out.println("Now searching for " + inLine);
                int numOfStops = 0;
                for (String stop : searchTree.keysWithPrefix(inLine.toUpperCase()))
                {
                    numOfStops++;
                    System.out.println(stop);
                }
                if (numOfStops > 1)
                {
                    System.out.println("No stops begin with those letters or match that name. Sorry.");
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        stopsToTST("stops.txt");

        System.out.println("Welcome to the some sort of Vancouver bus system. I'm not quite sure what its meant to do "
                + "yet because I have not yet fully read the brief. This text is temporary.");
        boolean quit = false;
        while (!quit)
        {
            System.out.println("Would you like to (1) do nothing, (2), Search for a buss stop or (3) do nothing.\n"
            + "Please enter the number (1, 2 or 3) for the functionality you would like or \"Quit\" to quit: ");
            String inLine = input.next();
            switch (inLine)
            {
                case "1" -> System.out.println("No functionality yet");
                case "2" -> {
                    System.out.println("Search for bus stop");
                    searchBusStopsByName();
                }
                case "3" -> System.out.println("Also no functionality yet");
                case "quit", "Quit", "exit", "Exit" -> {
                    System.out.println("Goodbye!");
                    quit = true;
                }
                default -> System.out.println("Please enter a either 1, 2, 3 or \"Quit\"");
            }
        }
    }
}
