import java.util.List;

public record WordList (List<String> words) implements WordCollection {

    /**
     * See WordListTest for the expected behavior of these two methods.
     */
    public boolean contains (String w) {
        return words.contains(w); // TODO
    }

    public boolean possiblePrefix (String w) {
        for (String word : words){
            if(word.startsWith(w)) {return true;}
        }

        return false; // TODO
    }
}
