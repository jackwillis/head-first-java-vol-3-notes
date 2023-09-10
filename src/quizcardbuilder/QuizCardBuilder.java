package quizcardbuilder;

public class QuizCardBuilder {
    public static void main(String[] args) {
        QuizCardBuilder builder = new QuizCardBuilder();
        builder.go();
    }

    public void go() {
        System.out.println("QuizCardBuilder");
        // build and display gui
    }

    private void nextCard() {
        // add the current card to the list
        // and clear the text areas
    }

    private void saveCard() {
        // bring up a file dialog box
        // let the user name and save the set
    }

    private void saveFile(File file) {
        // iterate through the list of cards and write
        // each one out to a text file in a parseable way
        // (in other words, with clear separations between parts)
    }
}