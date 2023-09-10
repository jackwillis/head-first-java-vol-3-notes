package quizgame;

/**
 * Represents a quiz card, which has a question and answer.
 */
public class QuizCard {
    private String question;
    private String answer;

    public QuizCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String toString() {
        return "QuizCard(\"" + question + "\", \"" + answer + "\")";
    }
}
