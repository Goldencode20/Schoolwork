import java.util.HashMap;

public class C {

    int makeChange (int amount) {
        // TODO
        int count = 0;
        if (amount >= 25) {
            count = count + makeChange (amount - 25);
        }
        if (amount >= 10) {
            count = count + makeChange (amount - 10);
        }
        if (amount >= 5) {
            count = count + makeChange (amount - 5);
        }
        if (amount >= 1 ) {
            count = count + makeChange (amount - 1);
        }
        if (amount == 0) {return 1;}
        return count;
    }

}

class CHash extends C {
    private HashMap<Integer, Integer> hm;
    CHash() { hm = new HashMap<>(); }

    int makeChange (int amount) {
        if (hm.containsKey(amount)) {
            return hm.get(amount);
        } else {
            int temp = super.makeChange(amount);
            hm.put(amount, temp);
            return temp;
        }
    }
}


