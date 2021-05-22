package data;

import tools.Encryption;

public class User implements java.io.Serializable{
    private String name;
    private String userName;
    private String email;
    private String password;
    private boolean isManager;
    private int UUID;


    public User(){

    }
    public User(String name,String userName, String email, String password, boolean isManager) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = Encryption.encrypt(password, Encryption.secret) ; // secure.encrypt(password,secure.secret);
        this.isManager = isManager;
        setUUID();
    }

    public User(String name, int UUID,String userName) {
        this.name = name;
        this.userName = userName;
        this.UUID = UUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Encryption.decrypt(password, Encryption.secret);
    }

    public void setPassword(String password) {
        this.password = Encryption.encrypt(password, Encryption.secret) ; // secure.encrypt(password,secure.secret);;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }


    public int getUUID() {
        return UUID;
    }

    public void setUUID() {
        this.UUID = ((int)(Math.random()*9000)+1000);
    }


    @Override
    public String toString() {
        return "User{" +
                "Username='" + userName + '\'' +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isManager=" + isManager +
                ", UUID=" + UUID +
                '}';
    }
}
