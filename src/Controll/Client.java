package Controll;

import TiuzTimeView.Gameface;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class Client {
    // Martin
    private String ip;
    private int port;
    private Socket socket;
    private Gameface gameface;

    public Client(String ip, int port){

        this.gameface = new Gameface();
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

                String[] s = new String[5];

                while(true) {

                    for (int i = 0; i <5; i++){
                        s[i] = dis.readUTF();
                    }

                    gameface.setQuestion(s[0],s[1],s[2],s[3],s[4]);

                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void Ready(){



    }




}
