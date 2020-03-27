import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class client2 {

    private String ip;
    private int port;
    private Socket socket;

    public client2(String ip, int port){

        this.ip = ip;
        this.port = port;

        try {
            socket = new Socket(ip,port);

            new ClientGo().start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class ClientGo extends Thread{

        public void run(){

            try{


                DataInputStream dis = new DataInputStream(socket.getInputStream());

                while(true) {

                    String a = dis.readUTF();

                    System.out.println(a + "2");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
