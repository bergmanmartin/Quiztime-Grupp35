package Controller;

import Model.QuestionModel.Questions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
<<<<<<< HEAD
 * @Created 11/02/2020
 * @project QuizTime
 * @Markus Gerdtsson
=======
 * Class that reads the lines from the file. The file is structured in a way so the first read line is the question every iteration.
 * and then four lines are the question and then the line after that is the line that shows which answer is correct
 * @author Markus Gerdtsson
 * @author Erik Nielsen
>>>>>>> Comment
 */

public class Questionreader {

    private Questions[] questions;

    /**
<<<<<<< HEAD
     * Creating a Question-object and reads the following lines.
     */

=======
     * Initializes the reader and then reads questions from the file and store them in the array.
     */
>>>>>>> Comment
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

    /**
     * Returning the list with the question-objects
     * @return
     */

    public Questions[] getQuestions() {
        return questions;
    }
}
