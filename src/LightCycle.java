package server;

public class LightCycle {
    String name;
    int x;
    int y;

    public LightCycle(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
