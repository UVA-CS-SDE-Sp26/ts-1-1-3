import java.util.List;

public class UserInterface {

    /*
   METHODS THAT NEED TO BE IMPLEMENTED BY MEMBER C:

    interface name: ProgramControl (name doesnt really matter i can just
    change my class to the corresponding interface name)

    //method 1: getNumberedFileList()
        returns numbered list of available files for display

    //method 2: getFileContentsByNum(String fileNumber)
        returns readable file contents using default key

    //method 3: getFileContentsByNum(String fileNumber, String keyArg)
        returns readable file contents using alt key
     */


    //update programControl to the interface memberC implements
    private final ProgramControl controller;

    public UserInterface(ProgramControl controller) {
        this.controller = controller;

    }// userinterface method

    public void run(String[] args) {
       //steps:
        // no args: ask controller for #'ed file list & print
        // 1 arg: check to see if it looks like file #, get content and print
        // 2 args: check file #, ask for content w key, print
        // else: error

        //analyze / look at incoming args
        //print the file list
        //handle errors with exceptions

        try {
            if (args == null || args.length == 0) {
                List<String> files = controller.getNumberedFileList();
                for (String line : files) {
                    System.out.println(line);
                }
                return;

            }
            if (args.length == 1) {
                String fileNumber = args[0];
                validateFileNumber((fileNumber));

                String contents = controller.getFileContentsByNum(fileNumber);
                System.out.println(contents);
                return;

            }
            if (args.length == 2) {
                String fileNumber = args[0];
                String keyArg = args[1];
                validateFileNumber(fileNumber);

                String contents = controller.getFileContentsByNum(fileNumber, keyArg);
                System.out.println(contents);
                return;
            }
            System.out.println("Too many arguments.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    } // run method

    private void validateFileNumber(String fileNumber) {
        if (fileNumber == null || !fileNumber.matches("\\d{2}")) {
            throw new IllegalArgumentException("File number must be 2 digits (e.g., 01, 02, etc.).");
        }
    } //validateFileNumber method
} // userinterface class

