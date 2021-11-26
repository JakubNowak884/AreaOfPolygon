package view;

/**
 * View class shows given messages in console.
 *
 * @author Jakub Nowak gr 5
 * @version 2.0
 */
public class View {

    /**
     * Class constructor.
     */
    public View() {
    }

    /**
     * Method shows message in console using standard output stream.
     *
     * @param message message to show
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Method shows error in console using error output stream.
     *
     * @param error error to show
     */
    public void showError(String error) {
        System.err.println(error);
    }
}
