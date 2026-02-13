import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
public class CipherServiceTest {
    @Test
    void testDecipherBasic() {
        try {
            CipherService cipher = new CipherService("ciphers/key.txt");

            String result = cipher.decipher("bcdef");

            assertEquals("abcde", result);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    void testWithSpaces() {
        try{
        CipherService cipher = new CipherService("ciphers/key.txt");

        String result = cipher.decipher("bc def");

        assertEquals("ab cde", result);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    void testEmpty() {
        try {
            CipherService cipher = new CipherService("ciphers/key.txt");

            assertEquals("", cipher.decipher(""));
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testInvalidCipher() {
        assertThrows(FileNotFoundException.class, () -> new CipherService("fake file path"));
    }
}
