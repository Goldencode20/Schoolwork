import java.util.*;

class C {

    /**
     * Method 1 of removal: O(N^2)
     *
     * Create an empty list of ints. Iterate through the entire list of ints.
     * If the int doesn't already exist in the returned/accumulated list, then we add it to our returned list.
     * This is O(N^2) because we have to iterate through our OG list and then iterate through our
     * new list once more to check.
     *
     * @return The list of integers without duplicates
     */

    // TODO: Implement O(N^2) method of removal
    static List<Integer> removeDups(List<Integer> ints) {
        List<Integer> returnMe = new ArrayList<>();
        returnMe.add(ints.get(0));
        for (int i = 0; i < ints.size(); i++) {
            boolean contains = false;
            for (int j = 0; j < returnMe.size(); j++){
                if(ints.get(i) == returnMe.get(j)){
                    contains = true;
                }
            }
            if (!contains){
                returnMe.add(ints.get(i));
            }
        }

        return returnMe;
    }

    /**
     * Method 2 of removal: O(N)
     *
     * Probabilistic (order is not guaranteed)
     *
     * Turn the list into a HashSet (see java collections Set/HashSet).
     * When creating a HashSet of integers, the HashSet relies upon a map to "map" values into this set.
     * The map has O(1) retrieval and putting. However, an important difference between HashSet and HashMap is that
     * the latter does not rely upon differing key/value but more so the key is the value and the value is just whether it is
     * present. An important similarity is that the key can NOT BE DUPLICATED. Hence, we get O(N)
     * since we have to just iterate through the original list.
     *
     * @return The list of integers without duplicates
     */

    // TODO: Implement O(N) method of removal
    static List<Integer> removeDups2(List<Integer> ints) {
        HashSet<Integer> dupRemove = new HashSet<>(ints);
        List<Integer> returnMe = new ArrayList<>();
        for (Integer num : dupRemove){
            returnMe.add(num);
        }
        return returnMe;
    }

    /**
     * Method 3 of removal: O(N log N)
     *
     * First, we can sort the list of integers which should be (N log N) time complexity.
     * Then, we can create an empty list and then add the sorted ints inside. To do so,
     * we keep track of what the previously added int is. If the current int is not equal to the previous
     * int, we add it to our list and update our previously added int.
     *
     * In total we get O(N log N) + O(N) which would be O(N log N).
     *
     * @return The list of integers without duplicates
     */
    // TODO:
    static List<Integer> noDups3(List<Integer> ints) {
        List<Integer> tempints = new ArrayList<>(ints);
        Collections.sort(tempints);
        List<Integer> returnMe = new ArrayList<>();
        returnMe.add(ints.get(0));
        for(Integer num : tempints){
            if(num != returnMe.get(returnMe.size() - 1)){
                returnMe.add(num);
            }
        }
        return returnMe;
    }
}

