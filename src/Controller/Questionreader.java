package Controller;

import Model.QuestionModel.Questions;

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

    private Questions[] questions;

    public Questionreader(){

        try {
            BufferedReader reader = new BufferedReader(new FileReader("files/Questions"));

            int numOfLines = Integer.parseInt(reader.readLine());

            questions = new Questions[numOfLines];

            for (int i = 0; i < numOfLines; i++){

                Questions newQuestion = new Questions(reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine());

                questions[i] = newQuestion;

            }


    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Questions[] getQuestions() {
        return questions;
    }
}
