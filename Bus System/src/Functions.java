
/**
 * Class that makes a TST of the bus stops with the names of the stops as the first item in the line and certain
 * keywords shifted to the end of the stop name.
 *
 * @author Lewis Kelly 20335015
 */
public class Functions
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
     * Basic binary search for the stops ArrayList.
     *
     * @param stop Stop you are searching for
     * @return Index of stop or -1 if the stop was not found
     */
    public static int binarySearch(int stop)
    {
        int left = 0, right = Main.stops.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (Main.stops.get(mid) == stop) {
                return mid;
            }

            if (Main.stops.get(mid) < stop) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
