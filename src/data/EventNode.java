package data;

public class EventNode {
    private Event event;
    private EventNode link;

    public EventNode() {
        link = null;
        event = null;
    }

    public EventNode(Event event, EventNode link) {
        this.event = event;
        this.link = link;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public EventNode getLink() {
        return link;
    }

    public void setLink(EventNode link) {
        this.link = link;
    }
}
