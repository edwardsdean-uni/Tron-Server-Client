import java.util.ArrayList;

public class Grid {

    int x;
    int y;
    ArrayList<LightCycle> game_grid = new ArrayList<LightCycle>();

    Grid(int x, int y){

        this.x = x;
        this.y = y;
    }

    public void add_light_wall(String player, int x, int y){
        LightCycle cycle = new LightCycle(player, x, y);
        game_grid.add(cycle);
    }

    //getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<LightCycle> getGame_grid() {
        return game_grid;
    }
}
