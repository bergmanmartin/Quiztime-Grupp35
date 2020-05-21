package Server;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author Markus Gerdtsson,Marianne Mukanga, Martin Bergman och Erik Nielsen.
 * This class reads the questions from text files and fills it into an array.
 */
public class QuestionReader {

    private Questions[] questions;


    /**
     * Reads the files "Questions" and takes every line in order and fills it in an array.
     * Fills the Questions class with the lines read from the textfile.
     */
    public QuestionReader(){

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
     *
     * @return questions.
     */
    public Questions[] getQuestions() {
        return questions;
    }
}
