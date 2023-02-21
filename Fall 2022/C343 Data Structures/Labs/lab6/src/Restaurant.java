// Distribution
import java.util.ArrayList;
import java.lang.Math;

/*
Annie and Kevin have decided to open up two competing restaurants. They both take reservations but have different
systems of doing so.

Before continuing in the storyline, please read the Party.java file.
 */

public interface Restaurant {
    void insert(Party party);
    void removeParty(Party party);
    void resize();
}

/*
Annie opens up a Ramen Bar. Her specialty is spicy miso noodles. Sometimes the noodles can be really mid but at least
the broth and meat taste great. She plays an awful lot of Harry Styles music in her restaurant but that's besides the point.
 */

class AnniesRamenBar implements Restaurant{
    private ArrayList<Party> tables;
    private int totalCapacity;
    private int currentCapacity;

    public AnniesRamenBar(){
        this.totalCapacity = 7;
        this.currentCapacity = 0;
        this.tables = new ArrayList<>(this.totalCapacity);
        for(int i = 0; i < totalCapacity; i++){
            this.tables.add(i, new Party());
        }
    }

    public ArrayList<Party> getTables(){
        return this.tables;
    }

    public int hash(String name){
        return name.length() % totalCapacity;
    }

    /*
    TODO: Quad-probing
    Annie's insertion of parties into her reservation queue of tables (not data structure) is that she'll
    try to separate the tables as much as she can if there's already someone in that spot. She cares a lot about
    public health and safety and thus she decides to set it up so that the next open table is at:
    within the total capacity: hash(party name) + collisions^2. If the currenCapacity (amount of tables being used)
    is half of the total capacity, then she wants to bring out more tables and keep the current parties in
    the system but replaced according to the new total capacity.
    This is quad-probing. She wants to avoid clustering if possible.
     */
    public void insert(Party party) {
        if (this.currentCapacity >= this.totalCapacity / 2) {
            this.resize();
        }
        int loop = 0;
        while(loop < totalCapacity) {
            int collision = (int) Math.sqrt(loop);
            if (getTables().get((hash(party.getGroup()) + collision) % totalCapacity).getGroup() == "" ||  getTables().get((hash(party.getGroup()) + collision) % totalCapacity).getGroup() == "*") {
                getTables().set((hash(party.getGroup()) + collision) % totalCapacity, party);
                this.currentCapacity++;
                break;
            }
            loop++;
        }
    }

    /*
    TODO: resize()
    This method will update the totalCapacity to the next prime number and translate the current parties to their new
    table reservation according to the updated totalCapacity.
    */
    public void resize() {
        ArrayList<Party> parties = new ArrayList<>();
        for (Party p : this.tables) {
            if (p.getGroup() != ""){
                parties.add(p);
            }
        }
        nextPrime();
        for(int i = 0; i < totalCapacity - 1; i++){
            this.tables.add(i, new Party());
        }
        this.currentCapacity = 0;
        for (Party p : parties) {
            this.insert(p);
        }
    }

    public void removeParty(Party party) {
        for(int i = 0; i < this.tables.size(); i++){
            if(this.tables.get(i).getGroup().equals(party.getGroup())){
                this.tables.set(i, new Party("*"));
                break;
            }
        }
    }

    public void nextPrime() {
        this.totalCapacity++;
        for (int i = 2; i < this.totalCapacity; i++) {
            if(this.totalCapacity%i == 0) {
                this.totalCapacity++;
                i=2;
            } else {
                continue;
            }
        }
    }
}
/*
Kevin decides to open up a Sushi bar. He cuts corners and doesn't use wasabi nor proper rice. He likes to use one minute
rice for sushi and also has a bad Yelp review where he yelled at a customer for being vegetarian. The upside is that
he likes to play KPop in his restaurant. That's besides the point.
 */
class KevinsSushiBar implements Restaurant{

    private ArrayList<Party> tables;
    private int totalCapacity;
    private int currentCapacity;

    public KevinsSushiBar(){
        this.totalCapacity = 7;
        this.currentCapacity = 0;
        this.tables = new ArrayList<>(this.totalCapacity);
        for(int i = 0; i < totalCapacity; i++){
            this.tables.add(i, new Party());
        }
    }

    public ArrayList<Party> getTables(){
        return this.tables;
    }

    public int hash(String name){
        return name.length() % totalCapacity;
    }

    /*
    TODO: Linear probing
    Kevin doesn't care about public health and just wants to get people in his restaurant. So his system of putting parties
    to tables is that he will find the next available one if it's open. Aka, 1 -> 2. or 1 -> 2 -> 3 (open). He decides to
    set it up so that the next open table is at:
    within the total capacity: hash(party name) + collisions. If the currenCapacity (amount of tables being used)
    is half of the total capacity, then he wants to bring out more tables and keep the current parties in
    the system but replaced according to the new total capacity.
     */
    public void insert(Party party) {
        if (this.currentCapacity >= (this.totalCapacity / 2)) {
            this.resize();
        }
        int loop = 0;
        while(loop < totalCapacity) {
            if (getTables().get((hash(party.getGroup()) + loop) % totalCapacity).getGroup() == "" ||  getTables().get((hash(party.getGroup()) + loop) % totalCapacity).getGroup() == "*") {
                getTables().set((hash(party.getGroup()) + loop) % totalCapacity, party);
                this.currentCapacity++;
                break;
            }
            loop++;
        }
    }

    /*
    TODO: resize()
    This method will update the totalCapacity to the next prime number and translate the current parties to their new
    table reservation according to the updated totalCapacity. You can use the resize function from above.
    */
    public void resize() {
        ArrayList<Party> parties = new ArrayList<>();
        for (Party p : this.tables) {
            if (p.getGroup() != ""){
                parties.add(p);
            }
        }
        nextPrime();
        for(int i = 0; i < totalCapacity - 1; i++){
            this.tables.add(i, new Party());
        }
        this.currentCapacity = 0;
        for (Party p : parties) {
            this.insert(p);
        }
    }

    public void removeParty(Party party) {
        for(int i = 0; i < this.tables.size(); i++){
            if(this.tables.get(i).getGroup().equals(party.getGroup())){
                this.tables.set(i, new Party("*"));
                break;
            }
        }
    }

    public void nextPrime() {
        this.totalCapacity++;
        for (int i = 2; i < this.totalCapacity; i++) {
            if(this.totalCapacity%i == 0) {
                this.totalCapacity++;
                i=2;
            } else {
                continue;
            }
        }
    }
}