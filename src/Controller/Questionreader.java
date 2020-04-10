package Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class Questionreader {


    private String[] namn = new String[21];


    public Questionreader{



    }


    public void readFromFile(){

        try {
            BufferedReader reader = new BufferedReader(new FileReader("files/Questions"));

            for (int i = 0; i < 21; i++) {
                namn[i] = reader.readLine();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
