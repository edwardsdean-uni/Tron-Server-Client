import server.Grid;
import utility.Network;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TronServer {
    final static String INET_ADDR = "224.0.0.3";
    final static int PORT = 8888;
    public static int number_of_player = 2;

    public static void main(String[] args) {
        try {
            Network network = new Network(INET_ADDR, PORT);

            //broadcast ip to already running clients
            network.broadcast(network.getIP() + "," + network.getPort());


            int grid_x = 500;
            int grid_y = 500;
            Grid light_gird = new Grid(grid_x, grid_y);

            List<String> players = new ArrayList<>();

            //wait for players
            int number_of_players = get_number_of_player();


            //read send loop
            while (true) {
                String command = network.read();  //will wait till recieves a message
                System.out.println("command: " + command);

                if (command.startsWith("add player")) {
                    String[] tokens = command.split(" ");
                    String name = tokens[2];
                    if (players.contains(name)) {
                        network.send(network.getLatestReadIP(),
                                network.getLatestReadPort(),
                                "error name already in use");
                    } else {
                        players.add(name);
                        network.send(network.getLatestReadIP(),
                                network.getLatestReadPort(),
                                "okay");
                    }
                }

                if (command.startsWith("send grid x")) {
                    System.out.println("sending gird x");
                    network.send(network.getLatestReadIP(), network.getLatestReadPort(), Integer.toString(grid_x));
                }
                if (command.startsWith("send grid y")) {
                    network.send(network.getLatestReadIP(), network.getLatestReadPort(), Integer.toString(grid_y));
                } else {
                    network.send(network.getLatestReadIP(),
                            network.getLatestReadPort(),
                            "error");
                }
            }



            } catch(IOException e){
                e.printStackTrace();
            }
    }

    public static void sleep(int millis){
        try {
            Thread.sleep(millis);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }



    public static int get_number_of_player() {
        return number_of_player;
    }

    public static void set_number_of_player(int number_of_player) {
        TronServer.number_of_player = number_of_player;
    }
}