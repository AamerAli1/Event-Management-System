package data;

import authentication.homePage.Launcher;
import mainApplication.createEvent.debug;

import java.io.*;

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


    public void populateHashTable(){
        EventNode current = first;
        while (current != null){
            int id = current.getEvent().getUUID();
            Event event = current.getEvent();
            Launcher.eventHash.put(id,event);
            current = current.getLink();
        }
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