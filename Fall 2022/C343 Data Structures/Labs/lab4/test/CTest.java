import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CTest {
    // TODO: Implement tests that show the scaled growth in the methods you just implemented
    @Test
    void dup1(){
        C c = new C();
        List<Integer> test = List.of(1,2,3,4,3);
        List<Integer> answer = List.of(1,2,3,4);
        assertEquals(answer, c.removeDups(test));
    }

    @Test
    void dup2(){
        C c = new C();
        List<Integer> test = List.of(1,2,3,4,3);
        List<Integer> answer = List.of(1,2,3,4);
        assertEquals(answer, c.removeDups2(test));
    }

    @Test
    void dup3(){
        C c = new C();
        List<Integer> test = List.of(1,2,3,4,3);
        List<Integer> answer = List.of(1,2,3,4);
        assertEquals(answer, c.noDups3(test));
    }

    @Test
    void timeTest(){
        Random r = new Random();
        C c = new C();
        int n = 100000; //How many items
        ArrayList<Integer> test = new ArrayList();
        ArrayList<Integer> test2 = new ArrayList();
        ArrayList<Integer> test3 = new ArrayList();
        for (int i = 0; i < n; i++) {
            test.add(r.nextInt(50));
            test2.add(r.nextInt(50));
            test3.add(r.nextInt(50));
        }
        Instant t1, t2;
        t1 = Instant.now();
        c.removeDups(test);
        t2 = Instant.now();
        long ns = Duration.between(t1, t2).toNanos();
        System.out.println("Function 1 took " + ns + " nanoseconds.");
        t1 = Instant.now();
        c.removeDups2(test2);
        t2 = Instant.now();
        ns = Duration.between(t1, t2).toNanos();
        System.out.println("Function 1 took " + ns + " nanoseconds.");
        t1 = Instant.now();
        c.noDups3(test3);
        t2 = Instant.now();
        ns = Duration.between(t1, t2).toNanos();
        System.out.println("Function 1 took " + ns + " nanoseconds.");
    }

}
