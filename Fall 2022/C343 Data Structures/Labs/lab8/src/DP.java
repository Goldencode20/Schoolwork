import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

public class DP {

    /**
     * subsetSum: take an ArrayList list, a desired sum 's', and return
     * T/F depending on whether it is possible to find the sum
     * from the elements in the list
     */
    static boolean subsetSum (ArrayList<Integer> s, int sum) {
        if (sum == 0 ){
            return true;
        }
        if (sum < 0){
            return false;
        }
        Integer first = s.remove(0);
        return subsetSum(s, sum - first) || subsetSum(s, sum);
    }

    // TODO
    // Hint: Look at the number of arguments/what we have to compare to start
    static boolean busubsetSum(ArrayList<Integer> s, int sum){
        if (s.isEmpty()) {return false;}
        int bigTotal = 1;
        for(Integer temp: s) {
            bigTotal += temp;
        }
        if (sum > bigTotal - 1) {return false;}
        ArrayList<ArrayList<Boolean>> matrix = new ArrayList<>();
        for (int x = 0; x < s.size(); x++) {
            ArrayList<Boolean> list = new ArrayList<>();
            matrix.add(list);
            for (int y = 0; y < bigTotal; y++) {
                matrix.get(x).add(false);
            }
        }

        for (int x = 0; x < s.size(); x++) {
            for (int y = 0; y < bigTotal; y++) {
                if (x == 0){
                    if(s.get(x) == y || y == 0) {
                        matrix.get(x).set(y, true);
                    }
                } else {
                    //TODO Move trues down a row add the new trues
                    if (matrix.get(x - 1).get(y) == true) {
                        matrix.get(x).set(y, true);
                        matrix.get(x).set(y + s.get(x), true);
                    } else if (s.get(x) == y) {
                        matrix.get(x).set(y, true);
                    }
                }
            }
        }
        return matrix.get(s.size() - 1).get(sum);
    }

    static int makeChange (int amount) {
        if (amount < 0) return 0;
        else if (amount == 0) return 1;
        else return makeChange(amount - 25) +
                    makeChange(amount - 10) +
                    makeChange(amount - 5) +
                    makeChange(amount - 1);
    }

    // TODO
    // permutations of the ways to make change
    // i. e 6 = 3 because {1, 1, 1, 1, 1, 1}, {5, 1}, {1, 5}
    // Hint: Look at number of arguments for how to start the problem
    static int bumakeChange(int amount) {
        ArrayList<Integer> changeList = new ArrayList<>();
        changeList.add(1);
        for (int i = 1; i < amount + 1; i++){
            int temp = changeList.get(i - 1);
            if (i >= 5){temp += changeList.get(i - 5);
            }
            if (i >= 10) {temp += changeList.get(i - 10);
            }
            if (i >= 25) {temp += changeList.get(i - 25);
            }
            changeList.add(temp);
        }
        return changeList.get(amount);
    }
}