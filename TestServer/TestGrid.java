import org.junit.Assert;
import org.junit.Test;
import server.Grid;
import server.LightCycle;

public class TestGrid {

    @Test
    public void create_grid(){
        Grid game_grid = new Grid(500,400);

        Assert.assertEquals(500, game_grid.getX());
        Assert.assertEquals(400, game_grid.getY());
    }

    @Test
    public void  add_LightCycle(){
        Grid game_grid = new Grid(500,400);
        game_grid.add_light_wall("Dean", 5, 50);

        for (int i = 0; i < game_grid.getGame_grid().size(); i++) {
            LightCycle test = game_grid.getGame_grid().get(i);
            Assert.assertEquals("Dean", test.getName());
            Assert.assertEquals(5, test.getX());
            Assert.assertEquals(50, test.getY());
        }

    }
}
