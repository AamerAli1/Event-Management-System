package data;


import java.util.ArrayList;

public class Event implements java.io.Serializable{
    private String name;
    private String performer;
    private String location;
    private String date;
    private int maxInvitees;
    private ArrayList<Integer> inviteesList;
    private int UUID;



    public Event(String name, String performer, String location,String date, int maxInvitees, int UUID) {
        this.name = name;
        this.performer = performer;
        this.location = location;
        this.maxInvitees = maxInvitees;
        this.inviteesList = new ArrayList<>();
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
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public ArrayList<Integer> getInviteesList() {
        return inviteesList;
    }

    public void setInviteesList(int UUID) {
        this.inviteesList.add(UUID);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", performer='" + performer + '\'' +
                ", Location='" + location + '\'' +
                ", date='" + getDate() + '\'' +
                ", maxInvitees=" + maxInvitees +
                ", invitees=" + inviteesList +
                ", UUID=" + UUID +
                '}';
    }
}
