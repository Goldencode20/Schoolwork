import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class overrides the solutions in DP with new methods that
 * manage an appropriate hash map (as implemented in our previous
 * assignment in the class HM).
 *
 * Each method will have its own hash map.
 */

public class DPHM extends DP {
    private HM<Integer,BigInteger> fibHM;
    private HM<HEntry<Stack<Integer>, Integer>, Boolean> sumHM;

    DPHM () {
        fibHM = new HM<>(11);
        sumHM = new HM<>(11);
    }



    // fib from lecture as a template
    @Override
    BigInteger fib (int n) {
        if (fibHM.containsKey(n)) {
            return fibHM.get(n);
        }
        else {
            BigInteger r = super.fib(n);
            fibHM.put(n,r);
            return r;
        }
    }

    @Override
    boolean subsetSum (Stack<Integer> s, int sum) {
        HEntry<Stack<Integer>, Integer> k = new HEntry<>(s, sum);
        if (sumHM.containsKey(k)) { return sumHM.get(k);}
        else {
            boolean temp = super.subsetSum(s, sum);
            sumHM.put(k, temp);
            return temp;
        }
    }
}