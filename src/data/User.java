package data;


import security.Security;

public class User implements java.io.Serializable{
    private String name;
    private String userName;
    private String email;
    private String password;
    private boolean isManager;

    public User(){

    }

    public User(String name,String userName, String email, String password, boolean isManager) {
        this.name = name;
        this.userName = name;
        this.email = email;
        this.password = Security.encrypt(password,Security.secret) ; // secure.encrypt(password,secure.secret);
        this.isManager = isManager;
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
        return Security.decrypt(password,Security.secret);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }



    @Override
    public String toString() {
        return "User{" +
                "Username='" + userName + '\'' +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isManager=" + isManager +
                '}';
    }
}
