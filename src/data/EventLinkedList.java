package data;

import authentication.homePage.Launcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class EventLinkedList {
    private EventNode first;

    public EventLinkedList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void outputList() {
        if (isEmpty())
            System.out.println("list is empty");
        else {
            EventNode position = first;
            while (position != null) {
                System.out.println(position.getEvent());
                position = position.getLink();
            }
        }
    }
    //add event to linked list
    public void add(Event event){
        first = new EventNode(event, first);
    }

    //removes event from linked list and hash table
    public boolean remove(Event event){
        if(isEmpty()){
            System.out.println("the list is empty");
            return false;
        }
        else{
            EventNode current = first;
            EventNode prev = null;
            while(current != null)
            {
                if(current.getEvent() == event)
                {
                    if(current == first)
                    {
                        first = current.getLink();
                        Launcher.eventUUIDHash.remove(event.getUUID());
                        return true;
                    }
                    else{
                        prev.setLink(current.getLink());
                        Launcher.eventUUIDHash.remove(event.getUUID());
                        return true;
                    }
                }
                prev = current;
                current = current.getLink();
            }
            System.out.println(event.getName() + " is not in the list");
            return false;
        }
    }


    //add UUIDs and event from event linked list to hash table
    public void populateUUIDHashTable(){
        EventNode current = first;
        while (current != null){
            int id = current.getEvent().getUUID();
            Event event = current.getEvent();
            Launcher.eventUUIDHash.put(id,event);
            current = current.getLink();
        }
    }

    //adds user to the event's object user array list
    public boolean RegisterToEvent(Event event,int UUID){
        EventNode current = first;
        while(current != null){
            if (current.getEvent() == event){
                current.getEvent().setInviteesList(UUID);
                return true;
            }
            current = current.getLink();
        }
        return false;
    }
    //removes user fromthe event user array list
    public boolean unRegisterFromEvent(Event event,Integer UUID){
        EventNode current = first;
        while(current != null){
            if (current.getEvent() == event){
                ArrayList<Integer> arrayList= current.getEvent().getInviteesList();
                arrayList.remove(UUID);
                return true;
            }
            current = current.getLink();
        }
        return false;
    }

    //checks if a user exists in an user Arraylist inside of an event object
    public boolean userExistsInEvent(Event event, int UUID){
        EventNode current = first;
        while(current != null){
            if (current.getEvent() == event && current.getEvent().getInviteesList().contains(UUID)){
                return true;
            }
            current = current.getLink();
        }
        return false;
    }



    //creates an observable list from the event linked list that will dispaly all events in the system
    public ObservableList<Event> getEvents(){
        EventNode current= first;
        ObservableList<Event> event = FXCollections.observableArrayList();
        while(current != null) {
            event.add(current.getEvent());
            current = current.getLink();
        }
        return event;
    }

    //creates an observable list from a specific event object user Arraylist that will display users registered to a
    //specific event
    public ObservableList<User> getUserinEvent(Event eventToSearch){
        EventNode current= first;
        ObservableList<User> users = FXCollections.observableArrayList();

        while(current != null) {
            if(current.getEvent() == eventToSearch) {
                ArrayList<Integer> arrayList= current.getEvent().getInviteesList();
                for(int i = 0; i < arrayList.size() ;i++) {
                    User user = Launcher.userList.retrieveUserFromUUID(current.getEvent().getInviteesList().get(i));
                    users.add(new User(user.getName(), user.getUUID(),user.getUserName()));
                }
            }
            current = current.getLink();
        }
        return users;
    }

    //checks if an given UUID belongs to any Event
    public boolean checkUUID(Integer UUID){
        EventNode current = first;
        while (current!=null){
            if(java.util.Objects.equals(current.getEvent().getUUID(),UUID))
                return true;
            current = current.getLink();
        }
        return false;
    }

    //writes the Event linked list to an external file
    public void writeToFile() throws IOException {
        FileOutputStream f = new FileOutputStream(new File("eventList.dat"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        if(isEmpty())
            System.out.println("list is empty");
        else {
            EventNode position = first;
            while (position != null) {
                o.writeObject(position.getEvent());
                position = position.getLink();
            }
        }
    }

    //reads the event linked list from an external file
    public  void readFromFile(EventLinkedList eventList) throws IOException, ClassNotFoundException {
        int count = 0;
        boolean cont = true;
        try {
            FileInputStream fis = new FileInputStream("eventList.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (cont){
                Event event = (Event) ois.readObject();
                if(event != null){
                    eventList.add(event);
                }else
                    cont = false;
                count++;
            }
        }  catch (EOFException e) {
            // ... this is fine
        } catch(IOException e) {
            // handle exception which is not expected
            e.printStackTrace();
        }
    }




}