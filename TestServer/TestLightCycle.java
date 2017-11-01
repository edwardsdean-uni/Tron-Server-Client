import org.junit.Assert;
import org.junit.Test;
import server.LightCycle;

public class TestLightCycle {

    @Test
    public void testLightCycle(){
        String name = "Dean";
        int x = 12;
        int y = 14;
        LightCycle cycle = new LightCycle(name, x, y);

        Assert.assertEquals("Dean", cycle.getName());
        Assert.assertEquals(12, cycle.getX());
        Assert.assertEquals(14, cycle.getY());
    }
}
