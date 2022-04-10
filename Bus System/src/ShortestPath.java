import edu.princeton.cs.algs4.*;

/**
 * Class that contains the method to find the shortest path between two stops using Dijkstra.
 *
 * @author Lewis Kelly 20335015
 */
public class ShortestPath
{
    /**
     * Finds the shortest path between two bus stops.
     *
     * @author Lewis Kelly 20335015
     */
    public static void findShortestPath()
    {
        boolean getOut = false;
        while (!getOut) {
            System.out.printf("""
                              %s
                              | Please enter the stop ID of the stop you wish to depart from or "Back" to go back:\040""",
                              Main.divString);
            String inLineOne = Main.input.nextLine();
            System.out.println(Main.divString);
            if (inLineOne.equalsIgnoreCase("Back")) {
                getOut = true;
                System.out.printf("| Returning to menu\n%s\n", Main.divString);
            } else if (inLineOne.equalsIgnoreCase("Quit") || inLineOne.equalsIgnoreCase("Exit")) {
                getOut = true;
                Main.quit = true;
            } else {
                try {
                    int fromStop = Integer.parseInt(inLineOne);
                    fromStop = Functions.binarySearch(fromStop);
                    if (fromStop == -1) {
                        System.out.printf("| %s does not exist, please enter a valid stop.\n", inLineOne);
                        continue;
                    }
                    System.out.print("""
                                     | Please enter the stop ID of the stop you wish to arrive at:\040""");
                    String inLineTwo = Main.input.nextLine();
                    System.out.println(Main.divString);
                    if (inLineTwo.equalsIgnoreCase("Back")) {
                        getOut = true;
                        System.out.printf("| Returning to menu\n%s\n", Main.divString);
                    } else if (inLineTwo.equalsIgnoreCase("Quit") || inLineTwo.equalsIgnoreCase("Exit")) {
                        getOut = true;
                        Main.quit = true;
                    } else {

                        int toStop = Functions.binarySearch(Integer.parseInt(inLineTwo));
                        if (toStop == -1) {
                            System.out.printf("| %s does not exist, please enter a valid stop.\n", inLineTwo);
                            continue;
                        }
                        DijkstraSP dsp = new DijkstraSP(Main.stopGraph, fromStop);

                        if (dsp.hasPathTo(toStop)) {
                            System.out.printf("| Finding path between stop %s and stop %s\n", inLineOne, inLineTwo);
                            for (DirectedEdge edge : dsp.pathTo(toStop)) {
                                System.out.printf("| Path between stop %d and stop %d will cost (%.0f)\n",
                                                  Main.stops.get(edge.from()), Main.stops.get(edge.to()),
                                                  edge.weight());
                            }
                            System.out.printf("| Total cost of trip: %.0f\n", dsp.distTo(toStop));
                        } else {
                            System.out.println("| No Path");
                        }

                    }
                } catch (NumberFormatException e) {
                    System.out.println("| Please only enter a bus stop number, \"Back\" or \"Quit\"");
                }
            }
        }
    }
}
