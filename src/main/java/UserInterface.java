import java.io.FileNotFoundException;
import java.util.List;

public class UserInterface {

    private final ProgramControl controller;

    public UserInterface(ProgramControl controller) {
        this.controller = controller;
    }

    public void run(String[] args) {
        try {
            if (args == null || args.length == 0) {
                // no args: print numbered file list
                List<String> files = controller.getNumberedFileList();
                for (String line : files) {
                    System.out.println(line);
                }
                return;
            }

            if (args.length == 1) {
                // 1 arg: treat it as file number
                int fileNum = parseAndValidateFileNumber(args[0]);
                String contents = controller.getFileContentsByNum(String.valueOf(fileNum));
                System.out.println(contents);
                return;
            }

            if (args.length == 2) {
                // 2 args: file number + alt key
                int fileNum = parseAndValidateFileNumber(args[0]);
                String keyArg = args[1];

                String contents = controller.getFileContentsByNum(String.valueOf(fileNum), keyArg);
                System.out.println(contents);
                return;
            }

            System.out.println("Error: Too many arguments.");
        } catch (FileNotFoundException e) {
            // ProgramControl uses FileNotFoundException for missing folder / bad file number
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Accepts single or double digit file number and fails for any case that isn't an Int
     */
    private int parseAndValidateFileNumber(String fileNumber) {
        if (fileNumber == null) {
            throw new IllegalArgumentException("File number is required.");
        }

        String s = fileNumber.trim();

        int n;
        try {
            n = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("File number must be an integer (e.g., 1 or 01).");
        }

        if (n < 1) {
            throw new IllegalArgumentException("File number must be >= 1.");
        }
        return n;
    }
}
