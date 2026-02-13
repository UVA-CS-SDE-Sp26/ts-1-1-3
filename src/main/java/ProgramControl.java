import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramControl {

    public ProgramControl() {

    }

    /**
     *
     * @return numbered list of available files for display
     */
    public List<String> getNumberedFileList() throws FileNotFoundException {
        //Implemented by Member B

            File dataFolder = new File("data");
            File[] dataFileList = dataFolder.listFiles();
            if (dataFileList == null){
                System.out.println("No files found");
                throw new FileNotFoundException("Data folder is null");
            }
            if (dataFileList.length == 0){
                throw new FileNotFoundException("Data folder is empty");
            }

            else {
                List<String> NumberedFileList = new ArrayList<>();
                for (int i = 0; i < dataFileList.length; i++) {
                    NumberedFileList.add((i + 1) + " " + dataFileList[i].getName());
                }
                return NumberedFileList;
            }

    }

    public List<String> getNumberedFileListTestVersion() throws FileNotFoundException {
        File dataFolder = new File("testdata");
        File[] dataFileList = dataFolder.listFiles();
        if (dataFileList == null){
            throw new FileNotFoundException("Data folder is null");
        }
        if (dataFileList.length == 0){
            throw new FileNotFoundException("Data folder is empty");
        }

        else {
            List<String> NumberedFileList = new ArrayList<>();
            for (int i = 0; i < dataFileList.length; i++) {
                NumberedFileList.add((i + 1) + " " + dataFileList[i].getName());
            }
            return NumberedFileList;
        }
    }

    /**
     *
     * @param fileNumber
     * @return readable file contents using default key
     */
    public String getFileContentsByNum(String fileNumber) throws FileNotFoundException {
        //Implemented by Member B

        File dataFolder = new File("data");
        File[] dataFileList = dataFolder.listFiles();
        if (dataFileList == null){
            throw new FileNotFoundException("Data folder is null");
        }
        int fileNumberInt = Integer.parseInt(fileNumber);

        if (fileNumberInt < 1 || fileNumberInt > dataFileList.length){
            throw new FileNotFoundException("Invalid file number");
        }

        File file = dataFileList[fileNumberInt-1];
        String fileContents = "";
        Scanner sc = new Scanner(file);
        if (sc.hasNextLine()){
            fileContents += sc.nextLine();
        }
        while (sc.hasNextLine()){
            fileContents += "\n"+sc.nextLine();
        }

        return fileContents;
    }

    public String getFileContentsByNumTestVersion(String fileNumber) throws FileNotFoundException {
        //Implemented by Member B

        File dataFolder = new File("testdata");
        File[] dataFileList = dataFolder.listFiles();
        if (dataFileList == null){
            throw new FileNotFoundException("Data folder is null");
        }
        int fileNumberInt = Integer.parseInt(fileNumber);

        if (fileNumberInt < 1 || fileNumberInt > dataFileList.length){
            throw new FileNotFoundException("Invalid file number");
        }

        File file = dataFileList[fileNumberInt-1];
        String fileContents = "";
        Scanner sc = new Scanner(file);
        if (sc.hasNextLine()){
            fileContents += sc.nextLine();
        }
        while (sc.hasNextLine()){
            fileContents += "\n"+sc.nextLine();
        }

        return fileContents;
    }

    /**
     *
     * @param fileNumber
     * @param keyArg
     * @return readable file contents using alt key
     */
    public String getFileContentsByNum (String fileNumber, String keyArg) throws FileNotFoundException {
        String temp = getFileContentsByNum(fileNumber);
        return decipher(temp, keyArg);
    }

    /**
     *
     * @param cipheredText
     * @param keyArg
     * @return deciphered text using alt key
     */
    //Nate: keyArg should be the name of a file in the ciphers folder, should validate the cipher
    public String decipher(String cipheredText, String keyArg) throws FileNotFoundException {
        //Implemented by Member D
        CipherService cs =  new CipherService(keyArg);
        return cs.decipher(cipheredText);
    }


}
