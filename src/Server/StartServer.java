package Server;

import Server.Server;

import java.io.IOException;

/**
 * @Author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
 * This class starts the server.
 */
public class StartServer {

    public static void main(String[] args) throws IOException {
        Server server = new Server(2343);
    }
}
