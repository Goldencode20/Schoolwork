import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DPTest {

    @Test
    void busubsetSum(){
        // TODO
        ArrayList<Integer> s = new ArrayList<>();
        //Should return false if list is empty
        assertFalse(DP.busubsetSum(s, 0));
        assertFalse(DP.busubsetSum(s, 1));

        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);

        assertTrue(DP.busubsetSum(s, 4));
        assertTrue(DP.busubsetSum(s, 8));
        //Edge cases
        assertTrue(DP.busubsetSum(s, 0));
        assertFalse(DP.busubsetSum(s, 11));

        s.remove(0);
        s.remove(1);
        s.add(6);
        s.add(8);
        s.add(10);

        assertTrue(DP.busubsetSum(s, 20));
        assertFalse(DP.busubsetSum(s, 23));
        assertTrue(DP.busubsetSum(s, 6));
        assertFalse(DP.busubsetSum(s, 11));
    }

    @Test
    void bumakeChange(){
        // TODO
        assertEquals(DP.bumakeChange(0), 1);
        assertEquals(DP.bumakeChange(4), 1);
        assertEquals(DP.bumakeChange(5), 2);
        assertEquals(DP.bumakeChange(6), 3);
        assertEquals(DP.bumakeChange(7), 4);
        assertEquals(DP.bumakeChange(8), 5);
        assertEquals(DP.bumakeChange(9), 6);
        assertEquals(DP.bumakeChange(10), 9);
        assertEquals(DP.bumakeChange(25), 916);
        assertEquals(DP.bumakeChange(50), 1931845);
    }

}
