import utility.Network;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TronClient {

    final static String INET_ADDR = "224.0.0.3";
    final static int PORT = 8888;

    public static void main(String[] args) throws UnknownHostException {
        try {
            Network network = new Network(INET_ADDR, PORT);
            // read and record server info
            String[] serverInfo = network.listen().split(",");
            System.out.print(serverInfo);
            String serverIP = serverInfo[0];
            int serverPort = Integer.parseInt(serverInfo[1]);

            // SETUP player name
            while (true) {
                String name = get_name();
                network.send(serverIP, serverPort, "add player " + name);

                String status = network.read();
                if (status.equals("okay")) {
                    break;
                } else {
                    System.out.println(status);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String get_name(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        return scanner.nextLine();
    }
}