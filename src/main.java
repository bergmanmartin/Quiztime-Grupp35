import java.io.IOException;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class main {

    public static void main(String[] args) throws IOException {
        server server = new server(2343);
        client1 client1 = new client1("127.0.0.1", 2343);
       // client1 client2 = new client1("127.0.0.1", 2343);
        clientGUI clientGUI = new clientGUI(client1);


    }

}
