package Server;

import java.io.Serializable;

/**
 * @Author Markus Gerdtsson, Marianne Mukanga, Martin Bergman och Erik Nielsen.
 * This class contains all data with game questions. Alternatives and answers are stored in this class.
 */
public class Questions implements Serializable {

    private String question;
    private String alternative1;
    private String alternative2;
    private String alternative3;
    private String alternative4;
    private String correctAlternative;

    /**
     * Initializes the different alternatives and correctAlternative which is passed on and filled by the @QuestionReader class.
     * @param question
     * @param alternative1
     * @param alternative2
     * @param alternative3
     * @param alternative4
     * @param correctAlternative
     */
    public Questions(String question, String alternative1, String alternative2, String alternative3, String alternative4, String correctAlternative){

        this.question = question;
        this.alternative1 = alternative1;
        this.alternative2 = alternative2;
        this.alternative3 = alternative3;
        this.alternative4 = alternative4;
        this.correctAlternative = correctAlternative;
    }

    /**
     *
     * @return the game questions with all data
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @return first alternative for the question
     */
    public String getAlternative1() {
        return alternative1;
    }

    /**
     *
     * @return second alternative for the question
     */
    public String getAlternative2() {
        return alternative2;
    }

    /**
     *
     * @return third alternative for the question
     */
    public String getAlternative3() {
        return alternative3;
    }

    /**
     *
     * @return fourth alternative for the question
     */
    public String getAlternative4() {
        return alternative4;
    }
    /**
     *
     * @return correct alternative for the question
     */
    public String getCorrectAlternative() {
        return correctAlternative;
    }
}
