import java.math.BigInteger;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This class contains the recursive solutions for the
 * dynamic programming problems
 *
 * These solutions will only work for small problems as they
 * involve many repeated subcomputations.
 */

public class DP {
    // fib from lecture repeated here as an template
    BigInteger fib (int n) {
        if (n < 2) return BigInteger.valueOf(n);
        else return fib(n-1).add(fib(n-2));
    }

    /**
     * Take a stack of numbers, a desired sum 'sum', and
     * return T/F depending on whether it is possible to use some
     * of the numbers in the stack to produce the desired sum.
     */
    boolean subsetSum (Stack<Integer> s, int sum) {
        if(s.empty() && sum == 0) {return true;}
        if(s.empty()) {return false;}
        if(s.search(sum) != -1) {return true;}

        Stack<Integer> s1 = new Stack<>(); //used to deep copy
        Stack<Integer> s2 = new Stack<>(); //used to deep copy

        while (!s.empty()) { // Deep copy
            int temp1 = s.pop();
            s1.add(temp1);
            s2.add(temp1);
        }
        while (!s2.isEmpty()) {
            s.add(s2.pop());
        }

        int temp = s1.pop();
        int newSum = sum - temp;

        if (subsetSum(s1, sum)) {return true;}
        if (subsetSum(s1, newSum)) {return true;}
        return false; // TODO
    }

}