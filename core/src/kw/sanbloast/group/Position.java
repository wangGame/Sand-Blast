package kw.sanbloast.group;

public class Position {
    private int px;
    private int py;

    public Position(int startX, int startY) {
        this.px = startX;
        this.py = startY;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    @Override
    public String toString() {
        return "Position{" +
                "px=" + px +
                ", py=" + py +
                '}';
    }
}
