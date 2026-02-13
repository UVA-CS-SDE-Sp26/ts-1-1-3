import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProgramControlTest {
    private ProgramControl controller;
    @BeforeEach
    void setUp() {
        controller = new ProgramControl();
    }

    @Nested
    class EmptyFolderTests {

        @Test
        void emptyFileListTest() { // There should be an exception thrown here if the folder is empty.
            Exception exception = assertThrows(FileNotFoundException.class, () -> {
                controller.getNumberedFileListTestVersion();
            });
            assertTrue(exception.getMessage().contains("Data folder"));
        }

        @Test
        void emptyGetFileContents() { // Same as the numbered file list, should be exception if the folder is empty.
            Exception exception = assertThrows(FileNotFoundException.class, () -> {
                controller.getFileContentsByNumTestVersion("1");
            });
        }

    }

    @Nested
    class DataFolderTests {

        @Test
        void numberedFileListTest() throws FileNotFoundException { // Checking that getNumberedFilesList shows the proper file number.
            List<String> files = controller.getNumberedFileList();
            assertNotNull(files);
            assertTrue(!files.isEmpty());
            List<String> dataFileList = new ArrayList<String>();
            dataFileList.add("1 carnivore.cip");
            dataFileList.add("2 carnivore.txt");
            dataFileList.add("3 cointelpro.cip");
            dataFileList.add("4 cointelpro.txt");
            assertEquals(dataFileList, files,"Files in docs folder do not match");
        }

        @Test
        void getFileContentsTest() throws FileNotFoundException { // This should properly return all contents of the file.
            List<String> files = controller.getNumberedFileList();
            String secondFile = "2";
            String fileContent = controller.getFileContentsByNum(secondFile);
            assertNotNull(fileContent);
            assertFalse(fileContent.isEmpty());
            assertEquals("Carnivore, later renamed DCS1000, was a system implemented by the Federal Bureau of Investigation (FBI) that was"+
                    "\ndesigned to monitor email and electronic communications. It used a customizable packet sniffer that could monitor all"+
                    "\nof a target user's Internet traffic. Carnivore was implemented in October 1997. By 2005 it had been replaced with"+
            "\nimproved commercial software.",fileContent,"Carnivore text does not match");

        }

        @Test
        void fileNumberTest() {
            assertThrows(FileNotFoundException.class, () -> { // This should throw an exception given an invalid number.
                controller.getFileContentsByNum("500"); // Any random invalid number works here.
            });
            assertThrows(FileNotFoundException.class, () -> { // This should throw an exception given an invalid number.
                controller.getFileContentsByNum("0"); // Any random invalid number works here.
            });
        }

        @Test
        void numberedFileTest() {
            assertThrows(NumberFormatException.class, () -> { // This should throw an exception for an invalid file number.
                controller.getFileContentsByNum("string"); // Any non-numeric file "number" works here.
            });
        }
    }


}
