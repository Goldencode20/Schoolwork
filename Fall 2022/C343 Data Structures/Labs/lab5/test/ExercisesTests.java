import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExercisesTests {

    @Test
    void mul() {
        assertEquals(371589120, Exercises.mul1());
        assertEquals(371589120, Exercises.mul2());
        assertEquals(371589120, Exercises.mul3());
        assertEquals(371589120, Exercises.mul4());
        assertEquals(371589120, Exercises.mul5());
    }

    // TODO: test each method
    @Test
    void even() {
        assertEquals(true, Exercises.even1());
        assertEquals(false, Exercises.even2());
        assertEquals(true, Exercises.even3());
        assertEquals(false, Exercises.even4());
        assertEquals(false, Exercises.even5());
    }

    @Test
    void max() {
        assertEquals(9, Exercises.max1());
        assertEquals(9, Exercises.max2());
        assertEquals(9, Exercises.max3());
        assertEquals(9, Exercises.max4());
        assertEquals(9, Exercises.max5());
    }

    @Test
    void count() {
        assertEquals(4, Exercises.count1(8));
        assertEquals(4, Exercises.count2(8));
        assertEquals(4, Exercises.count3(8));
        assertEquals(4, Exercises.count4(8));
        assertEquals(4, Exercises.count5(8));
    }

    @Test
    void trip() {
        List<Integer> ints = new ArrayList<>(Arrays.asList(1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8,9,9,9,2,2,2,8,8,8,8,8,8,8,8,8));
        assertEquals(ints, Exercises.trip1());
        assertEquals(ints, Exercises.trip2());
        assertEquals(ints, Exercises.trip3());
        assertEquals(ints, Exercises.trip4());
    }
}
