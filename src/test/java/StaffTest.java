import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class StaffTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    
    
    @Test
    public void loginSuccess() {
        String input = "staff";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        StaffSystem s = new StaffSystem();
        s.loginScreen();
        System.setIn(in);
        input = "st@ff";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(true, s.staffLoggedIn());
    }
    
    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
}