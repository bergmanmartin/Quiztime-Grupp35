package Controll;

import Controll.Server;
import TiuzTimeView.Gameface;

import java.io.IOException;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Server server = new Server(2343);
        Client Client = new Client("127.0.0.1", 2343);

    }

}
