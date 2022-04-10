import java.util.Arrays;

/**
 * TODO
 */
public class ArrivalTime
{
    /**
     * TODO
     */
    static void getArrivalTimes()
    {
        boolean getOut = false;
        while (!getOut) {
            System.out.printf("""
                              %s
                              | Please enter the arrival time with the format (HH:MM:SS):\040""", Main.divString);
            String inLine = Main.input.nextLine().trim();
            System.out.printf("%s\n", Main.divString);
            if (inLine.equalsIgnoreCase("Back")) {
                getOut = true;
                System.out.printf("| Returning to menu\n%s\n", Main.divString);
            } else if (inLine.equalsIgnoreCase("Quit") || inLine.equalsIgnoreCase("Exit")) {
                getOut = true;
                Main.quit = true;
            } else {

                String[] splitInLine = inLine.split(":");

                try {
                    if (splitInLine.length == 3) {
                        for (String time : splitInLine) {
                            if (Integer.parseInt(time) >= 0 && Integer.parseInt(time) <= 23 && time.length() < 3) {
                                Integer.parseInt(time);
                            }
                        }

                        System.out.printf("""
                                          | Now searching for %s...
                                          %s
                                          | The Data is formatted as:
                                          | Trip ID, Arrival Time, Departure Time, Stop ID, Stop Sequence, Stop Headsign, Pickup Type, Drop Off Type, Shape Distance Traveled:
                                          """,
                                          inLine, Main.divString);

                        int count = 0;
                        for (String data :
                                Main.stopTimes) {
                            String[] split = data.split(",");
                            if (split[1].trim().equals(inLine)) {
                                count++;
                                System.out.println("| " + data);
                            }
                        }
                        if (count < 1) {
                            System.out.printf("| No trips for %s\n", inLine);
                        } else {
                            System.out.printf("%s\n| %s trips found\n", Main.divString, count);
                        }
                    } else {
                        System.out.println("| Incorrect input. Please enter a time in the format (HH:MM:SS)");
                    }

                } catch (Exception ignored) {
                    System.out.println("| Incorrect input. Please enter a time in the format (HH:MM:SS)");
                }
            }
        }
    }


}
