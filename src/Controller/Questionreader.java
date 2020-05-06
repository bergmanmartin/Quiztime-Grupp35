package Controller;

import Model.QuestionModel.Questions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that reads the lines from the file. The file is structured in a way so the first read line is the question every iteration.
 * and then four lines are the question and then the line after that is the line that shows which answer is correct
 * @author Markus Gerdtsson
 * @author Erik Nielsen
 */
public class Questionreader {

    private Questions[] questions;

    /**
     * Initializes the reader and then reads questions from the file and store them in the array.
     */
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
