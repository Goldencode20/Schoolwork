// Your job is to implement these concepts: checked and unchecked exception and try-catch.
// You **must** use the Exception and Error class.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DeadFromStress extends Exception{}
class EmptyTrackList extends Exception{}

/*
Let me set the scene. You're on a one-on-one date with Blackpink Jisoo. You are sweating beads
because Blackpink Jisoo just asked you about your music taste. You hype yourself up and say that you
have the best music taste. She asks you your favorite song on the spot.
 */

// TODO: Implement SpotifyPlaylist
class SpotifyPlaylist {

    private ArrayList<String> tracklist;
    static Random rand;

    SpotifyPlaylist(String[] songs){
        tracklist = new ArrayList<String>(List.of(songs));
        rand = new Random();
    }

    // ==== TUTORIAL ====
    // Sweating, you excuse yourself for a second and pull out your phone to text your friend.
    // You attempt to ask your friend for a recommendation to play.
    // If your friend doesn't give you a proper response (an exception), then just default
    // to everyone's favorite song -- Montero by Lil Nas X. You kind of hope they don't respond
    // because wouldn't it be strange to play a Blackpink song to Blackpink Jisoo herself???
    String askFriend(){
        try{
            return friendRecommendation();
        } catch (DeadFromStress d) {
            return "Montero";
        }
    }

    // Your friend is a hardcore K-Pop stan and more specifically a Blackpink stan.
    // They will always recommend a Blackpink song unless they are dead from stress.
    // The chances of them *that* dead is 25%. It's no longer syllabus week give them a break.
    String friendRecommendation() throws DeadFromStress{
        int choice = rand.nextInt(4);
        if (choice == 3){
            throw new DeadFromStress();
        } else if (choice == 2){
            return "How you like that";
        } else if (choice == 1){
            return "Kill this love";
        } else {
            return "Pink Venom";
        }
    }

    // === END TUTORIAL ===
    // Jisoo is embarassed by the song your friend recommended (not that she knows).
    // Jisoo doesn't like Lil Nas X (so not slay). She ended up not being part of the everyone that
    // loves Lil Nas X! Who knew?!
    // Now she wants you to play your best playlist.
    // Following the method structure above, complete the following methods.

    // TODO: Create a method that throws a EmptyTracklist Exception if the tracklist is empty
    // This method should return the first song in the tracklist if it exists.
    // Hint: You will need to edit the method signature.
<<<<<<< HEAD
    void addSong(String song) throws TracklistF
    {
        if(this.index > tracklist.length)
        {
            throw TracklistF ;
        }
        else
        {
            tracklist[index] = song ;
            index++ ;
        }
    }

    // TODO: Create a method that catches TracklistF when addSong is called and throws an Error with a custom message
    // Hint: Use try-catch
    void addSongIfPossible(String song)
    {
        try
        {
            addSong(song) ;
        }
        catch TracklistF
        {
            System.out.println("There is not enough room in the playlist") ;
        }
    }

    // TODO: Create a method that throws a TracklistE Exception if the tracklist is empty
    // Hint: You will need to edit the method signature.
    String removeSong(String song) throws TracklistE Exception
    {
        if(index == 0)
        {
            throw TracklistE Exception ;
        }
        else
        {
            tracklist[index] = null ; 
        }
=======
    String playPlaylist(){
>>>>>>> 0aab51acd633adc0601d9d1d180e29317ece7b36
        return null;
    }

    // TODO: Create a method that catches EmptyTracklist when playPlaylist is called and throws an Error with a custom message
    // Hint: Use try-catch
<<<<<<< HEAD
    void removeSongIfPossible(String song)
    {

=======
    void playPlaylistIfPossible(){
>>>>>>> 0aab51acd633adc0601d9d1d180e29317ece7b36
    }


}


