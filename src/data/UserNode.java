package data;

public class UserNode {
    private User user;
    private UserNode link;

    public UserNode() {
        link = null;
        user = null;
    }

    public UserNode(User user, UserNode link) {
        this.user = user;
        this.link = link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserNode getLink() {
        return link;
    }

    public void setLink(UserNode link) {
        this.link = link;
    }
}
