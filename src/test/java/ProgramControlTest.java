import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
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
        void emptyFileListTest() { // There should be an exception thrown here if hte folder is empty.
            Exception exception = assertThrows(FileNotFoundException.class, () -> {
                controller.getNumberedFileList();
            });
            assertTrue(exception.getMessage().contains("Data folder"));
        }

        @Test
        void emptyGetFileContents() { // Same as the numbered file list, should be exception if the folder is empty.
            Exception exception = assertThrows(FileNotFoundException.class, () -> {
                controller.getFileContentsByNum("1");
            });
            assertTrue(exception.getMessage().contains("Data folder"));
        }

    }

    @Nested
    class DataFolderTests {

        @Test
        void numberedFileListTest() throws FileNotFoundException { // Checking that getNumberedFilesList shows the proper file number.
            List<String> files = controller.getNumberedFileList();
            assertNotNull(files);
            assertTrue(!files.isEmpty());
        }

        @Test
        void getFileContentsTest() throws FileNotFoundException { // This should properly return all contents of the file.
            List<String> files = controller.getNumberedFileList();
            String firstFile = "1";
            String fileContent = controller.getFileContentsByNum(firstFile);
            assertNotNull(fileContent);
            assertFalse(fileContent.isEmpty());
        }

        @Test
        void fileNumberTest() {
            assertThrows(FileNotFoundException.class, () -> { // This should throw an exception given an invalid number.
                controller.getFileContentsByNum("500"); // Any random invalid number works here.
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
