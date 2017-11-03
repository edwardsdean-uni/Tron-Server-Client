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



            List<String> players = new ArrayList<>();

            //wait for players
            int number_of_players = get_number_of_player();
            for (int i = 0; i < number_of_players; ++i) {

                String command = network.read();  //will wait till recieves a message
                System.out.println("cammnad " + command);

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
            }

            //game


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