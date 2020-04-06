package QuestionModel;

import java.io.Serializable;

/**
 * @Created 11/02/2020
 * @project P1
 * @Markus Gerdtsson
 */
public class Questions implements Serializable {

    private String question;
    private String alternative1;
    private String alternative2;
    private String alternative3;
    private String alternative4;
    private String correctAlternative;

    public Questions(String question, String alternative1, String alternative2, String alternative3, String alternative4, String correctAlternative){

        this.question = question;
        this.alternative1 = alternative1;
        this.alternative2 = alternative2;
        this.alternative3 = alternative3;
        this.alternative4 = alternative4;
        this.correctAlternative = correctAlternative;

    }

    public String getQuestion() {
        return question;
    }

    public String getAlternative1() {
        return alternative1;
    }

    public String getAlternative2() {
        return alternative2;
    }

    public String getAlternative3() {
        return alternative3;
    }

    public String getAlternative4() {
        return alternative4;
    }

    public String getCorrectAlternative() {
        return correctAlternative;
    }
}
