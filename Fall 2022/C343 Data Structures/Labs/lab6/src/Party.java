import java.lang.Integer;

public class Party {
    /*
    Key is the table number. The group is the party name.
    */
    private int key;
    private String group;

    Party(){
        this.key = -1;
        this.group = "";
    }

    Party(String group){
        this.key = -1;
        this.group = group;
    }

    int getKey(){
        return this.key;
    }

    void setKey(Integer i){
        this.key = i;
    }

    String getGroup(){
        return this.group;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Party) {
            Party otherParty = (Party) other;
            return key == (otherParty.key) && group.equals(otherParty.group);
        }
        return false;
    }


    @Override
    public java.lang.String toString() {
        return "<" + this.key + ", " + this.group + ">";
    }
}
