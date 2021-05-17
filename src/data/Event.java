package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Event implements java.io.Serializable{
    private String name;
    private String performer;
    private String Location;
    private String date;
    private int maxInvitees;
    private UserLinkedList invitees;
    private int UUID;

    public Event() {
        setUUID();
    }


    public Event(String name, String performer, String location,String date, int maxInvitees) {
        this.name = name;
        this.performer = performer;
        Location = location;
        this.maxInvitees = maxInvitees;
        this.invitees = null;
        setUUID();
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

    public UserLinkedList getInvitees() {
        return invitees;
    }

    public void setInvitees(UserLinkedList invitees) {
        this.invitees = invitees;
    }

    public int getUUID() {
        return UUID;
    }

    public void setUUID() {
        this.UUID = ((int)(Math.random()*9000)+1000);
    }

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
