import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.max;

public class Exercises {
    private static List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,2,8,8,8));

    // ------------------------------------------------------------------------
    // Exercise I: multiply the elements in the list using the five approaches
    // ------------------------------------------------------------------------

    static int mul1 () {
        int res = 1;
        for (int i=0; i<ints.size(); i++) res *= ints.get(i);
        return res;
    }

    static int mul2 () {
        int res = 1;
        for (int k : ints) res *= k;
        return res;
    }

    static int mul3 () {
        int res = 1;
        ListIterator<Integer> iterator = ints.listIterator();
        while (iterator.hasNext()) res *= iterator.next();
        return res;
    }

    /* Notice that we have two of the same solutions for mul. Exercises 2-4 have multiple
     * unique solutions using Streams, so please find at least two Stream solutions for
     * those exercises!
     */
    static int mul4 () {
        return ints.stream().reduce(1, (res,k) -> res * k);
    }

    static int mul5 () {
        return ints.stream().reduce((res, k) -> res * k).orElse(1);
    }

    // ------------------------------------------------------------------------
    // Exercise II: check if all elements in the list are even
    // ------------------------------------------------------------------------

    static boolean even1 () {
        // TODO
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(2,2,2,2,2));
        for(int i = 0; i < test.size(); i++) {
            if (test.get(i) % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    static boolean even2 () {
        for(int i = 0; i < ints.size(); i++) {
            if (ints.get(i) % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    static boolean even3 () {
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(2,2,2,2,2));
        boolean isEven = true;
        for(int i = 0; i < test.size(); i++) {
            if (test.get(i) % 2 == 1) {
                isEven = false;
            }
        }
        return isEven;
    }

    static boolean even4 () {
        ArrayList<Integer> evens = new ArrayList<>(ints);
        ints.stream().filter(n -> n % 2 == 0).filter(n -> n % 2 == 0).collect(Collectors.toList());
        return !evens.equals(ints);
    }

    static boolean even5 () {
        // TODO
        ArrayList<Integer> evens = new ArrayList<>(ints);
        ints.stream().filter(n -> n % 2 == 0).filter(n -> n % 2 != 1).collect(Collectors.toList());
        return !evens.equals(ints);
    }

    // ------------------------------------------------------------------------
    // Exercise III: compute the maximum
    // ------------------------------------------------------------------------

    static int max1 () {
        // TODO
        return ints.stream().max(Integer::compare).get();
    }

    static int max2 () {
        // TODO
        return max(ints);
    }

    static int max3 () {
        // TODO
        return ints.stream().max((i, j) -> i.compareTo(j)).get();
    }

    static int max4 () {
        // TODO
        Collections.sort(ints);
        return ints.get(ints.size() - 1);
    }

    static int max5 () {
        // TODO
        Collections.sort(ints, Collections.reverseOrder());
        return ints.get(0);
    }

    // ------------------------------------------------------------------------
    // Exercise IV: count occurrences
    // ------------------------------------------------------------------------

    static int count1 (int c) {
        // TODO
        int count = 0;
        for (int i = 0; i < ints.size() - 1; i++) {
            if (ints.get(i) == c) {
                count++;
            }
        }
        return count;
    }

    static int count2 (int c) {
        // TODO
        int count = 0;
        for (int i = 0; i < ints.size() - 1; i++) {
            if (ints.get(i) != c) {
                count++;
            }
        }
        return ints.size() - count - 1;
    }

    static int count3 (int c) {
        // TODO
        Map<Integer, Integer> occurances = new HashMap<Integer, Integer>();
        for (Integer i : ints) {
            Integer j = occurances.get(i);
            occurances.put(i, (j == null) ? 1 : j + 1);
        }
        for (Map.Entry<Integer, Integer> val : occurances.entrySet()) {
            if(val.getKey() == c) {
                return val.getValue();
            }
        }
        return -1;
    }

    static int count4 (int c) {
        // TODO
        List<Integer> filtered = ints.stream().filter(num -> Objects.equals(ints.get(num), c)).collect(Collectors.toList());
        return filtered.size() - 1;
    }

    static int count5 (int c) {
        // TODO
        int startSize = ints.size() - 1;
        List<Integer> filtered = ints.stream().filter(num -> !Objects.equals(ints.get(num), c)).collect(Collectors.toList());
        return startSize - filtered.size();
    }

    // ------------------------------------------------------------------------
    // Exercise V: triplicate
    // ------------------------------------------------------------------------

    static List<Integer> trip1 () {
        // TODO
        List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,2,8,8,8));
        ArrayList<Integer> returnMe = new ArrayList<>();
        for (int i = 0; i < ints.size(); i++) {
            returnMe.add(ints.get(i));
            returnMe.add(ints.get(i));
            returnMe.add(ints.get(i));
        }
        return returnMe;
    }

    static List<Integer> trip2 () {
        // TODO
        List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,2,8,8,8));
        ArrayList<Integer> returnMe = new ArrayList<>();
        for (int i = 0; i < ints.size(); i++) {
            for (int j = 0; j < 3; j++ ) {
                returnMe.add(ints.get(i));
            }
        }
        return returnMe;
    }

    static List<Integer> trip3 () {
        // TODO
        List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,2,8,8,8));
        ArrayList<Integer> returnMe = new ArrayList<>();
        Stream.iterate(0, n -> n + 1).limit(ints.size() * 3).forEach(returnMe::add);
        for (int i = 0; i < returnMe.size(); i++) {
            returnMe.set(i, ints.get(i/3));
            returnMe.set(i, ints.get(i/3));
            returnMe.set(i, ints.get(i/3));
        }
        return returnMe;
    }

    static List<Integer> trip4 () {
        // TODO
        List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,2,8,8,8));
        ArrayList<Integer> returnMe = new ArrayList<>();
        for (int i : ints) {
            for (int j = 1; j < 4; j++ ) {
                returnMe.add(i);
            }
        }
        return returnMe;
    }


}
