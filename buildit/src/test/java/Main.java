import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

/**
 *
 * @author Levent
 */
public class Main {
    
    @Test
    public void testCase_0() {
        System.out.println("test case 0");
        assertEquals(1, 1);
    }
    
    @Test
    public void testCase_1() {
        System.out.println("test case 1");
        assertEquals("string assertion: ", "jenkins", "jenkins");
    }
}
