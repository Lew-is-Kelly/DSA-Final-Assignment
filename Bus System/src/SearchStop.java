/**
 * Class to search for the name of a bus stop.
 */
public class SearchStop
{
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
                              Main.divString);
            String inLine = Main.input.nextLine();
            System.out.println(Main.divString);
            if (inLine.equalsIgnoreCase("Back")) {
                getOut = true;
                System.out.println("Returning to menu");
            } else if (inLine.equalsIgnoreCase("quit") || inLine.equalsIgnoreCase("exit")) {
                getOut = true;
                Main.quit = true;
            } else {
                System.out.printf("""
                                  | Now searching for %s
                                  %s
                                  """, inLine.toUpperCase(), Main.divString);
                int numOfStops = 0;
                for (String stop : Main.searchTree.keysWithPrefix(inLine.toUpperCase())) {
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
}
