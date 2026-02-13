import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

public class CipherService {
    private Map<Character, Character> map;

    public CipherService(String filePath) {
        map = new HashMap<>();
        loadKey(filePath);
    }

    private void loadKey(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            if (lines.size() < 2) {
                System.out.println("Invalid");
                return;
            }

            String real = lines.get(0);
            String cipher = lines.get(1);
            if (real.length() != cipher.length()) {
                System.out.println("length does not match");
                return;
            }
            for (int i = 0; i < real.length(); i++) {
                char realChar = real.charAt(i);
                char cipherChar = cipher.charAt(i);
                map.put(cipherChar, realChar);
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public String decipher(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (map.containsKey(c)) {
                result += map.get(c);
            } else {
                result += c;
            }
        }

        return result;
    }
}