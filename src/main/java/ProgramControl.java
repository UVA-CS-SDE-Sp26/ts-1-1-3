import java.util.List;

public class ProgramControl {

    public ProgramControl() {

    }

    /**
     *
     * @return numbered list of available files for display
     */
    public List<String> getNumberedFileList(){
        //Implemented by Member B
        return null;
    }

    /**
     *
     * @param fileNumber
     * @return readable file contents using default key
     */
    public String getFileContentsByNum(String fileNumber){
        //Implemented by Member B
        return null;
    }

    /**
     *
     * @param fileNumber
     * @param keyArg
     * @return readable file contents using alt key
     */
    public String getFileContentsByNum(String fileNumber, String keyArg){
        String temp = getFileContentsByNum(fileNumber);
        return decipher(temp, keyArg);
    }

    /**
     *
     * @param cipheredText
     * @param keyArg
     * @return deciphered text using alt key
     */
    //Nate: How does keyArg translate to a cipher? Is it a String with 26 characters,
    // new line, then 26 characters?
    public String decipher(String cipheredText, String keyArg){
        //Implemented by Member D
        return null;
    }


}
