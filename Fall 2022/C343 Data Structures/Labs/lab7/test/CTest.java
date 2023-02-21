import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CTest {

    @Test
    void test1 () {
        C maker = new C();
        assertEquals(1, maker.makeChange(1));
        assertEquals(1, maker.makeChange(4));
        assertEquals(2, maker.makeChange(5));
        assertEquals(6, maker.makeChange(9));
        assertEquals(9, maker.makeChange(10));
    }

    @Test
    void test2 () {
        C maker = new C();
        C makerH = new CHash();
        assertEquals(maker.makeChange(1), makerH.makeChange(1));
        assertEquals(maker.makeChange(4), makerH.makeChange(4));
        assertEquals(maker.makeChange(5), makerH.makeChange(5));
        assertEquals(maker.makeChange(9), makerH.makeChange(9));
        assertEquals(maker.makeChange(10), makerH.makeChange(10));

        System.out.println(makerH.makeChange(100));
    }

}