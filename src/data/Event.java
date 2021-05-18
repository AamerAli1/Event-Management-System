package data;


import java.util.ArrayList;

public class Event implements java.io.Serializable{
    private String name;
    private String performer;
    private String Location;
    private String date;
    private int maxInvitees;
    private ArrayList<Integer> invitees;
    private int UUID;



    public Event(String name, String performer, String location,String date, int maxInvitees, int UUID) {
        this.name = name;
        this.performer = performer;
        Location = location;
        this.maxInvitees = maxInvitees;
        this.invitees = new ArrayList<>();
        this.UUID = UUID;
        this.date = date;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMaxInvitees() {
        return maxInvitees;
    }

    public void setMaxInvitees(int maxInvitees) {
        this.maxInvitees = maxInvitees;
    }




    public int getUUID() {
        return UUID;
    }

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }

    public ArrayList<Integer> getInvitees() {
        return invitees;
    }

    public void setInvitees(int UUID) {
        this.invitees.add(UUID);
    }

    //    public boolean existsinArraylist(User user){
//    int userUUID = user.getUUID();
//    for(int i = 0; i < this.invitees.size();i++){
//       if(this.invitees.indexOf(user.getUUID()))
//           return true;
//    }
//
//    return false;
//    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", performer='" + performer + '\'' +
                ", Location='" + Location + '\'' +
                ", date='" + getDate() + '\'' +
                ", maxInvitees=" + maxInvitees +
                ", invitees=" + invitees +
                ", UUID=" + UUID +
                '}';
    }
}
