import utility.Network;

import javax.swing.*;
import java.awt.*;
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


            //gui start
            gui_client(serverIP, serverPort);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String get_name(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        return scanner.nextLine();
    }

    public static void gui_client(String serverIP, int ServerPort){
        JFrame frame = new JFrame("JFrame Example");
        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("This is a label!");

        JButton button = new JButton();
        button.setText("Press me");

        panel.add(label);
        panel.add(button);

        frame.add(panel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

