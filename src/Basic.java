package alumni.src;

import static java.lang.Character.*;
import static java.lang.String.*;

/**
 *
 * @author Celia
 */
public abstract class Basic {

    /**
     * Default Constructor
     */
    public Basic() {
    }

    /**
     *
     * @return strings of class attributes to main
     */
    public abstract String[] init();

    /**
     *
     * @param variables is a 2D array: 1: includes the index of the instance var
     * to be changed 2: includes the value to which the instance var must be set
     */
    public abstract void setVariables(String[][] variables);

    /**
     *
     * @param extent describes length of report
     * @return array of values stored in instance variables
     */
    public abstract String[] createReport(int extent);

    /**
     * converts int to String
     *
     * @param entry is input value integer data type
     * @return entry converted to string type
     */
    public String toString(int entry) {
        return valueOf(entry);
    }

    /**
     * Convert String to int
     *
     * @param entry is input value String type
     * @return entry converted to int data type
     */
    public int toInt(String entry) {
        for (int i = 0; i < entry.length(); i++) {
            if (isDigit(entry.charAt(i))) {
                try {
                    return Integer.parseInt(entry);
                } // Input is not a number-- return 0
                catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
        // Method failed-- return 0
        return 0;
    }

    /**
     *
     * @param memberName is the name associated with the member signing up for a
     * role
     * @param role represents attendee, donor, or host
     * @return boolean true if sign up was successful, false if no
     * 
     */
    public boolean setRole(String memberName, String role) {
        return false;
    }

    /**
     *
     * @return a formatted string of all values stored in roles for training or event
     */
    public String getRole() {
        return "";
    }
    
    /**
     * 
     * @param dono is amount member donates
     * @return total amount member has donated
     */
    
    public double addDono(double dono){
        return 0.0;
    }

}
