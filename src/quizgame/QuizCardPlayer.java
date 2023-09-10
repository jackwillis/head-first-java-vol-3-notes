package quizgame;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class QuizCardPlayer {
    private ArrayList<QuizCard> cardList;
    private int currentCardIndex;
    private QuizCard currentCard;
    private JTextArea display;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;

    public static void main(String[] args) {
        new QuizCardPlayer().go();
    }

    public void go() {
        // build and display gui

        frame = new JFrame("Quiz Card Player");
        var mainPanel = new JPanel();

        var headingFont = new Font(
            Font.SANS_SERIF,
            Font.BOLD,
            20 /* px */
        );

        display = new JTextArea(10 /* rows */, 20 /* cols */);
        display.setFont(headingFont);
        display.setLineWrap(true);
        display.setEditable(false);

        var scroller = new JScrollPane(display);
        scroller.setVerticalScrollBarPolicy(
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
        );
        scroller.setHorizontalScrollBarPolicy(
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        mainPanel.add(scroller);

        nextButton = new JButton("Show Question");
        nextButton.addActionListener(_e -> nextCard());
        mainPanel.add(nextButton);

        var menuBar = new JMenuBar();
        var fileMenu = new JMenu("File");

        var loadMenuItem = new JMenuItem("Load Card Set");
        loadMenuItem.addActionListener(_e -> open());

        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(
            BorderLayout.CENTER,
            mainPanel
        );
        frame.setSize(500 /* px */, 400 /* px */);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void nextCard() {
        // if this is a question, show the answer, otherwise show
        // next question set a flag for whether we’re viewing a
        // question or answer

        if (isShowAnswer) {
            // show the answer because they've seen the question
            display.setText(currentCard.getAnswer());
            nextButton.setText("Next Card");
            isShowAnswer = false;
        } else { // show the next question
            if (currentCardIndex < cardList.size()) {
                showNextCard();
            } else {
                // there are no more cards
                display.setText("That was the last card.");
                nextButton.setEnabled(false);
            }
        }
    }

    private void open() {
        // bring up a file dialog box
        // let the user navigate to and choose a card set to open

        var fileOpen = new JFileChooser();
        fileOpen.showOpenDialog(frame);
        loadFile(fileOpen.getSelectedFile());
    }

    private void loadFile(File file) {
        // must build an ArrayList of cards, by reading them from
        // a text file called from the OpenMenuListener event handler,
        // reads the file one line at a time and tells the makeCard()
        // method to make a new card out of the line (one line in the
        // file holds both the question and answer, separated by a “/”)

        cardList = new ArrayList<>();
        currentCardIndex = 0;

        try {
            var reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                makeCard(line);
            }

            reader.close();
        }
        catch (IOException exception) {
            System.err.println(
                "Couldn't write the card list out: " +
                exception.getMessage()
            );
        }

        showNextCard();
    }

    private void makeCard(String lineToParse) {
        // called by the loadFile method, takes a line from the text file
        // and parses into two pieces—question and answer—and creates a
        // new QuizCard and adds it to the ArrayList called CardList

        String[] result = lineToParse.split("/");
        String question = result[0];
        String answer = result[1];
        var card = new QuizCard(question, answer);

        cardList.add(card);

        System.out.println("Made a card: " + card);
    }

    private void showNextCard() {
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;

        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer = true;
    }
}
