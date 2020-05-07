package Model.QuestionModel;

import java.io.Serializable;

/**
 * Class that stores the Questions.
 * @author Markus Gerdtsson
 * @version 1.0
 *
 */
public class Questions implements Serializable {

    private String question;

    private String alternative1;
    private String alternative2;
    private String alternative3;
    private String alternative4;

    private String correctAlternative;

    public Questions(String question, String alternative1, String alternative2, String alternative3, String alternative4, String correctAlternative) {

        this.question = question;

        this.alternative1 = alternative1;
        this.alternative2 = alternative2;
        this.alternative3 = alternative3;
        this.alternative4 = alternative4;

        this.correctAlternative = correctAlternative;
    }

    /**
     * returns the current question
     * @return String
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the first alternative
     * @return String
     */
    public String getAlternative1() {
        return alternative1;
    }

    /**
     * Returns the second alternative.
     * @return String
     */
    public String getAlternative2() {
        return alternative2;
    }

    /**
     * Returns the third alternative
     * @return String
     */
    public String getAlternative3() {
        return alternative3;
    }

    /**
     * Returns the fourth alternative
     * @return String
     */
    public String getAlternative4() {
        return alternative4;
    }

    /**
     * Returns the correct answer.
     * @return String
     */
    public String getCorrectAlternative() { return correctAlternative; }
}
