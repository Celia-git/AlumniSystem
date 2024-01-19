package alumni.src;

/**
 *
 * @author Celia
 */
public class Except extends Exception {

    // Constructor

    /**
     *
     * @param message describes the error to print
     */
    public Except(String message) {
        super(message);
        System.out.println(message);

    }
}
