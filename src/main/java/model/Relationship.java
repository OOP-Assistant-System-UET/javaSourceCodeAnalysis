package model;

public class Relationship {
    private int from;
    private int to;
    private String type;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void printInfor() {
        System.out.println("From: " + this.from + "  To: " + this.to + " Type:" + type);
    }
}
