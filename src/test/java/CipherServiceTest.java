import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CipherServiceTest {
    @Test
    void testDecipherBasic() {
        CipherService cipher = new CipherService("ciphers/key.txt");

        String result = cipher.decipher("bcdef");

        assertEquals("abcde", result);
    }
    @Test
    void testWithSpaces() {
        CipherService cipher = new CipherService("ciphers/key.txt");

        String result = cipher.decipher("bc def");

        assertEquals("ab cde", result);
    }
    @Test
    void testEmpty() {
        CipherService cipher = new CipherService("ciphers/key.txt");

        assertEquals("", cipher.decipher(""));
    }
}
