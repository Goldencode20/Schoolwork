import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {
    private Counter bc;

    @BeforeEach
    void setUp() {
        bc = new Counter(16);
    }

    /*
     * TODO: Your implementation of "BinaryCounter.incrementEfficient()" should run in O(1); please provide a brief
     *  explanation of how you achieved this using amortized analysis.
     *
     * Answer
     *9995/5000=1.999
     *19995/10000=1.9995
     *29995/15000=1.999
     * 39995/20000=1.999
     * 49995/25000=1.999
     *
     * Since they all ended around the exact value of almost 2 the code is O(1) in time becuase it stays as a line.
     *
     */

    int[] incrementEfficient(int[] countCost, int iterations) throws Counter.CountOutOfBoundsException {
        for (int i = 0; i < iterations; i++) {
            countCost[0]++;
            countCost[1] += bc.incrementEfficient();
        }

        return countCost;
    }

    @Test
    void countEfficientTest() throws Counter.CountOutOfBoundsException {
        assertEquals("[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]", bc.toString());

        final int COUNT = 0;
        final int COST = 1;
        int[] countAndCost = new int[] {0, 0};
        int steps = 5000;
        countAndCost = incrementEfficient(countAndCost, steps);
        assertEquals(5000, countAndCost[COUNT]);
        assertEquals(9995, countAndCost[COST]);

        countAndCost = incrementEfficient(countAndCost, steps);
        assertEquals(10000, countAndCost[COUNT]);
        assertEquals(19995, countAndCost[COST]);

        countAndCost = incrementEfficient(countAndCost, steps);
        assertEquals(15000, countAndCost[COUNT]);
        assertEquals(29993, countAndCost[COST]);
        assertEquals("[ 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0 ]", bc.toString());

        countAndCost = incrementEfficient(countAndCost, steps);
        assertEquals(20000, countAndCost[COUNT]);
        assertEquals(39995, countAndCost[COST]);

        countAndCost = incrementEfficient(countAndCost, steps);
        assertEquals(25000, countAndCost[COUNT]);
        assertEquals(49994, countAndCost[COST]);
        assertEquals("[ 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0 ]", bc.toString());
    }

    /*
     * TODO: Do you notice any patterns in the cost of calling "BinaryCounter.incrementSlow()"? Take a few minutes to perform
     *  (write down in the comment block below) an amortized analysis of "BinaryCounter.incrementSlow()".
     *
     * Answer
     *80000/5000=16
     *160000/10000=16
     *240000/15000=16
     * 320000/20000=16
     * 40000/25000=16
     *
     * Since they all ended around the exact value of almost 16 the code is O(1) in time becuase it stays as a line. Mine is better is because it is storing and just updating the binary number rather than rewrting it everytime.
     */

    int[] incrementSlow(int[] countCost, int iterations) throws Counter.CountOutOfBoundsException {
        for (int i = 0; i < iterations; i++) {
            countCost[0]++;
            countCost[1] += bc.incrementSlow();
        }

        return countCost;
    }

    @Test
    void countSlowTest() throws Counter.CountOutOfBoundsException {
        assertEquals("[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]", bc.toString());

        final int COUNT = 0;
        final int COST = 1;
        int[] countAndCost = new int[] {0, 0};
        int steps = 5000;
        countAndCost = incrementSlow(countAndCost, steps);
        assertEquals(5000, countAndCost[COUNT]);
        assertEquals(80000, countAndCost[COST]);

        countAndCost = incrementSlow(countAndCost, steps);
        assertEquals(10000, countAndCost[COUNT]);
        assertEquals(160000, countAndCost[COST]);

        countAndCost = incrementSlow(countAndCost, steps);
        assertEquals(15000, countAndCost[COUNT]);
        assertEquals(240000, countAndCost[COST]);
        assertEquals("[ 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0 ]", bc.toString());

        countAndCost = incrementSlow(countAndCost, steps);
        assertEquals(20000, countAndCost[COUNT]);
        assertEquals(320000, countAndCost[COST]);

        countAndCost = incrementSlow(countAndCost, steps);
        assertEquals(25000, countAndCost[COUNT]);
        assertEquals(400000, countAndCost[COST]);
        assertEquals("[ 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0 ]", bc.toString());
    }

    @Test
    void countMixedTest() throws Counter.CountOutOfBoundsException {
        assertEquals("[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]", bc.toString());

        final int COUNT = 0;
        final int COST = 1;
        int[] countAndCost = new int[] {0, 0};
        int steps = 5000;
        countAndCost = incrementSlow(countAndCost, steps);
        assertEquals(5000, countAndCost[COUNT]);
        assertEquals(80000, countAndCost[COST]);

        countAndCost = incrementEfficient(countAndCost, steps);
        assertEquals(10000, countAndCost[COUNT]);
        assertEquals(90000, countAndCost[COST]);

        countAndCost = incrementSlow(countAndCost, steps);
        assertEquals(15000, countAndCost[COUNT]);
        assertEquals(170000, countAndCost[COST]);
        assertEquals("[ 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0 ]", bc.toString());

        countAndCost = incrementSlow(countAndCost, steps);
        assertEquals(20000, countAndCost[COUNT]);
        assertEquals(250000, countAndCost[COST]);

        countAndCost = incrementEfficient(countAndCost, steps);
        assertEquals(25000, countAndCost[COUNT]);
        assertEquals(259999, countAndCost[COST]);
        assertEquals("[ 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0 ]", bc.toString());
    }

    @Test
    void countFlip() throws Counter.CountOutOfBoundsException {
        final int COUNT = 0;
        final int COST = 1;
        int[] countAndCost = new int[] {0, 0};
        int steps = 5000;
        countAndCost = incrementSlow(countAndCost, steps);
        assertEquals(5000, countAndCost[COUNT]);
        assertEquals(80000, countAndCost[COST]);

        final int COUNT1 = 0;
        final int COST1 = 1;
        int[] countAndCost1 = new int[] {0, 0};
        int steps1 = 5000;
        countAndCost1 = incrementEfficient(countAndCost1, steps1);
        assertEquals(5000, countAndCost1[COUNT1]);
        assertEquals(10000, countAndCost1[COST1]);
    }

    @Test
    void  testFunction() throws Counter.CountOutOfBoundsException {
        assertEquals("[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]", bc.toString());

        final int COUNT = 0;
        final int COST = 1;
        int[] countAndCost = new int[] {0, 0};
        int steps = 5000;

        countAndCost = incrementEfficient(countAndCost, steps);
        assertEquals(5000, countAndCost[COUNT]);
        assertEquals(9995, countAndCost[COST]);
        assertEquals("[ 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0 ]", bc.toString());

        countAndCost = incrementEfficient(countAndCost, steps);
        assertEquals(10000, countAndCost[COUNT]);
        assertEquals(19995, countAndCost[COST]);
        assertEquals("[ 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0 ]", bc.toString());
    }
}