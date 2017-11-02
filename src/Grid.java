package server;

import java.util.ArrayList;

public class Grid {

    int x;
    int y;
    ArrayList<server.LightCycle> game_grid = new ArrayList<server.LightCycle>();

    public Grid(int x, int y){

        this.x = x;
        this.y = y;
    }

    public void add_light_wall(String player, int x, int y){
        server.LightCycle cycle = new server.LightCycle(player, x, y);
        game_grid.add(cycle);
    }

    //getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<server.LightCycle> getGame_grid() {
        return game_grid;
    }
}
