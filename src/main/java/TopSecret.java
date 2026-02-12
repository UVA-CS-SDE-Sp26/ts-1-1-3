/**
 * Commmand Line Utility
 */
public class TopSecret {

    public static void main(String[] args){

       // create program control that has file access, data retrieval, and deciphering logic
        ProgramControl controller = new ProgramControl();

        //create user interface object and give it access to program control so it can request data
        UserInterface ui = new UserInterface(controller);

        //start program by passing arguments
        ui.run(args);

    } //main method

} //top secret class
