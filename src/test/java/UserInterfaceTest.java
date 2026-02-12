import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    private PrintStream originalOut;
    private ByteArrayOutputStream outputBytes;

    @BeforeEach
    void setup() {
        originalOut = System.out;
        outputBytes = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputBytes));
    }

    @AfterEach
    void reset() {
        System.setOut(originalOut);
    }

    @Test
    void fileListPrintNoArgsTest() { // This is checking if the file list is printable.
        ProgramControl test = new ProgramControl() {
            @Override
            public List<String> getNumberedFileList() {
                return List.of("01 filea.txt", "02 fileb.txt");
            }
        };

        UserInterface u = new UserInterface(test);
        u.run(new String[]{});

        String output = outputBytes.toString();
        assertTrue(output.contains("01 filea.txt")); // These should be true, since these were the test files given.
        assertTrue(output.contains("02 fileb.txt"));
    }

    @Test
    void getFileContentsValidArg() throws Exception {
        ProgramControl test = new ProgramControl() {
            @Override
            public String getFileContentsByNum(String fileNumber) {
                assertEquals("01", fileNumber);
                return "Test print"; // This is to verify something prints.
            }
        };

        new UserInterface(test).run(new String[]{"01"});
        assertTrue(outputBytes.toString().contains("Test print")); // This matches the return from earlier, which is the verification.
    }

    @Test
    void invalidArgsNumber() {
        ProgramControl test = new ProgramControl();
        new UserInterface(test).run(new String[]{"Too", "Many", "Arguments"}); // The arguments themselves don't matter here, rather the amount of arguments.
        assertTrue(outputBytes.toString().contains("Too many arguments"));
    }

    @Test
    void missingProgramControlFolderTest() { // This tests when ProgramControl has an error with missing a data folder, which then affects UserInterface.
        ProgramControl test = new ProgramControl() {
            @Override
            public List<String> getNumberedFileList() throws FileNotFoundException {
                throw new FileNotFoundException("No data folder present");
            }
        };

        new UserInterface(test).run(new String[]{});

        String output = outputBytes.toString();
        assertTrue(output.contains("Error:"));
        assertTrue(output.contains("No data folder present")); // Matching the error message above.
    }

}
