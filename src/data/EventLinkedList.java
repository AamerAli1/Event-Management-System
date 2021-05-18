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
        if (first == null)
            return true;
        else
            return false;
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

//    public void populateBST(){
//        EventNode current = first;
//        while(current != null){
//            debug.eventBST.insert(current.getEvent().getUUID());
//            current = current.getLink();
//        }
//    }



    public void add(Event event){
        first = new EventNode(event, first);
    }


    public void populateUUIDHashTable(){
        EventNode current = first;
        while (current != null){
            int id = current.getEvent().getUUID();
            Event event = current.getEvent();
            Launcher.eventUUIDHash.put(id,event);
            current = current.getLink();
        }
    }

    public boolean RegisterToEvent(Event event,int UUID){
        EventNode current = first;
        while(current != null){
            if (current.getEvent() == event){
                current.getEvent().setInvitees(UUID);
                return true;
            }
            current = current.getLink();
        }
        return false;
    }
    public boolean unRegisterFromEvent(Event event,Integer UUID){
        EventNode current = first;
        while(current != null){
            if (current.getEvent() == event){
                ArrayList<Integer> arrayList= current.getEvent().getInvitees();
                arrayList.remove(UUID);
                return true;
            }
            current = current.getLink();
        }
        return false;
    }
    public boolean userExists(Event event,int UUID){
        EventNode current = first;
        while(current != null){
            if (current.getEvent() == event && current.getEvent().getInvitees().contains(UUID)){
                return true;
            }
            current = current.getLink();
        }
        return false;
    }



    public ObservableList<Event> getEvents(){
        EventNode current= first;
        ObservableList<Event> event = FXCollections.observableArrayList();
        while(current != null) {
            event.add(new Event(current.getEvent().getName(), current.getEvent().getPerformer(),
                    current.getEvent().getLocation(), current.getEvent().getDate(),
                    current.getEvent().getMaxInvitees(), current.getEvent().getUUID()));
            current = current.getLink();
        }
        return event;
    }

    public ObservableList<User> getUserinEvent(Event eventToSearch){
        EventNode current= first;
        ObservableList<User> users = FXCollections.observableArrayList();

        while(current != null) {
            if(current.getEvent() == eventToSearch) {
                ArrayList<Integer> arrayList= current.getEvent().getInvitees();
                for(int i = 0; i < arrayList.size() ;i++) {
                    User user = Launcher.userList.retrieveUserFromUUID(current.getEvent().getInvitees().get(i));
                    users.add(new User(user.getName(), user.getUUID()));
                }
            }
            current = current.getLink();
        }
        return users;
    }

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