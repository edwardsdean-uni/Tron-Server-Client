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


            List<String> players = new ArrayList<>();

            //wait for players
            int number_of_players = get_number_of_player();
            for (int i = 0; i < number_of_players; ++i) {
                network.broadcast(network.getIP() + "," + network.getPort());
                String command = network.read();

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

            } catch(IOException e){
                e.printStackTrace();
            }
    }


    public static int get_number_of_player() {
        return number_of_player;
    }

    public static void set_number_of_player(int number_of_player) {
        TronServer.number_of_player = number_of_player;
    }
}
//
//    public static void main(String[] args) throws UnknownHostException, InterruptedException {
//        // Get the address that we are going to connect to.
//        InetAddress addr = InetAddress.getByName(INET_ADDR);
//        // Open a new DatagramSocket, which will be used to send the data.
//        try (DatagramSocket serverSocket = new DatagramSocket()) {
//            for (int i = 0; i < 5; i++) {
//                String msg = "Sent message no " + i;
//                // Create a packet that will contain the data
//                // (in the form of bytes) and send it.
//                DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),
//                        msg.getBytes().length, addr, PORT);
//                serverSocket.send(msgPacket);
//
//                System.out.println("Server sent packet with msg: " + msg);
//
//                Thread.sleep(500);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//}