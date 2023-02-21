import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {
    // write a test showing kevin and annies restaurants insertin/removing/rehashing properly
    @Test
    void AnnieInsert(){
        AnniesRamenBar bar = new AnniesRamenBar();
        Party p1 = new Party("Gamers");
        Party p2 = new Party("Jocks");
        Party p3 = new Party("Nerds");
        bar.insert(p1);
        bar.insert(p2);
        bar.insert(p3);

        ArrayList<Party> expected = new ArrayList<>();
        Party e = new Party();
        expected.add(p3);
        expected.add(e);
        expected.add(e);
        expected.add(e);
        expected.add(e);
        expected.add(p2);
        expected.add(p1);
        assertEquals(expected, bar.getTables());
    }

    @Test
    void KevinInsert() {
        KevinsSushiBar bar = new KevinsSushiBar();
        Party p1 = new Party("Gamers");
        Party p2 = new Party("Jocks");
        Party p3 = new Party("Nerds");
        bar.insert(p1);
        bar.insert(p2);
        bar.insert(p3);

        ArrayList<Party> expected = new ArrayList<>();
        Party e = new Party();
        expected.add(p3);
        expected.add(e);
        expected.add(e);
        expected.add(e);
        expected.add(e);
        expected.add(p2);
        expected.add(p1);
        assertEquals(expected , bar.getTables());
    }

    @Test
    void AnnieRemove(){
        AnniesRamenBar bar = new AnniesRamenBar();
        Party p1 = new Party("Gamers");
        Party p2 = new Party("Jocks");
        Party p3 = new Party("Nerds");
        bar.insert(p1);
        bar.insert(p2);
        bar.insert(p3);
        bar.removeParty(p2);

        ArrayList<Party> expected = new ArrayList<>();
        Party e = new Party();
        Party eStar = new Party("*");
        expected.add(p3);
        expected.add(e);
        expected.add(e);
        expected.add(e);
        expected.add(e);
        expected.add(eStar);
        expected.add(p1);
        assertEquals(expected , bar.getTables());
    }

    @Test
    void KevinRemove() {
        KevinsSushiBar bar = new KevinsSushiBar();
        Party p1 = new Party("Gamers");
        Party p2 = new Party("Jocks");
        Party p3 = new Party("Nerds");
        bar.insert(p1);
        bar.insert(p2);
        bar.insert(p3);
        bar.removeParty(p2);

        ArrayList<Party> expected = new ArrayList<>();
        Party e = new Party();
        Party eStar = new Party("*");
        expected.add(p3);
        expected.add(e);
        expected.add(e);
        expected.add(e);
        expected.add(e);
        expected.add(eStar);
        expected.add(p1);
        assertEquals(expected, bar.getTables());
    }

    @Test
    void AnnieRehash(){
        AnniesRamenBar bar = new AnniesRamenBar();
        Party p1 = new Party("Gamers");
        Party p2 = new Party("Jocks");
        Party p3 = new Party("Nerds");
        Party p4 = new Party("Kelly Kids"); // I work for the Kelly Library, and I am work working on this, and they popped into my head for a group name
        bar.insert(p1);
        bar.insert(p2);
        bar.insert(p3);
        bar.insert(p4);
        assertEquals(17, bar.getTables().size());
    }

    @Test
    void KevinRehash() {
        KevinsSushiBar bar = new KevinsSushiBar();
        Party p1 = new Party("Gamers");
        Party p2 = new Party("Jocks");
        Party p3 = new Party("Nerds");
        Party p4 = new Party("Kelly Kids");
        bar.insert(p1);
        bar.insert(p2);
        bar.insert(p3);
        bar.insert(p4);
        assertEquals(17, bar.getTables().size());
    }
}
