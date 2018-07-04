package Cards;

import javax.swing.*;

/**
 * Created by Миша on 19.06.2018.
 */
public class QuizCard {
    private String answer;
    private String question;

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public QuizCard(String answer, String question) {
        this.answer = answer;
        this.question = question;
    }
}
