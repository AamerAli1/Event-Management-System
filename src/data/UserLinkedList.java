package data;

import java.io.*;

public class UserLinkedList {
    private UserNode first;

    public UserLinkedList() {
        first = null;
    }

    public boolean isEmpty(){
        if(first == null)
            return true;
        else
            return false;
    }

    public void outputList(){
        if(isEmpty())
            System.out.println("list is empty");
        else {
            UserNode position = first;
            while (position != null) {
                System.out.println(position.getUser());
                position = position.getLink();
            }
        }
    }

    public void add(User user){
        first = new UserNode(user, first);
    }


    public boolean checkSignIn(String UserName, String pass){
        UserNode current = first;
        while (current!=null){
            if(current.getUser().getUserName().matches(UserName) && current.getUser().getPassword().matches(pass))
                return true;
            current = current.getLink();
        }
        return false;
    }

    public boolean findUserName(String userName){
        UserNode current = first;
        while (current!=null){
            if(current.getUser().getUserName().matches(userName))
                return true;
            current = current.getLink();
        }
        return false;
    }

    public boolean findEmail(String email){
        UserNode current = first;
        while (current!=null){
            if(current.getUser().getEmail().matches(email))
                return true;
            current = current.getLink();
        }
        return false;
    }

    public String retrievePass(String username){
        UserNode current = first;
        while (current!=null){
            if(current.getUser().getUserName().matches(username))
                return current.getUser().getPassword();
            current = current.getLink();
        }
        return null;
    }


    public boolean isManager(String user, String pass){
        UserNode current = first;
        while (current!=null){
            if(current.getUser().getUserName().matches(user) && current.getUser().getPassword().matches(pass))
            {
                if(current.getUser().isManager())
                    return true;
            }

            current = current.getLink();
        }
        return false;
    }

    public void writeToFile() throws IOException {
        FileOutputStream f = new FileOutputStream(new File("userList.txt"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        if(isEmpty())
            System.out.println("list is empty");
        else {
            UserNode position = first;
            while (position != null) {
                o.writeObject(position.getUser());
                position = position.getLink();
            }
        }
    }


    public  void readFromFile(UserLinkedList UserList) throws IOException, ClassNotFoundException {
        int count = 0;
        boolean cont = true;
        try {
            FileInputStream fis = new FileInputStream("userList.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (cont){
                User user = (User) ois.readObject();
                if(user != null){
                    UserList.add(user);
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
