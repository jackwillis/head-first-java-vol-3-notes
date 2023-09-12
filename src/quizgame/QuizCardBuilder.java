package quizgame;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class QuizCardBuilder {
    private ArrayList<QuizCard> cardList = new ArrayList<>();
    private JTextArea question;
    private JTextArea answer;
    private JFrame frame;

    public static void main(String[] args) {
        new QuizCardBuilder().go();
    }

    public void go() {
        frame = new JFrame("Quiz Card Builder");

        var mainPanel = new JPanel();
        var headingFont = new Font(
            Font.SANS_SERIF,
            Font.BOLD,
            20 /* px */
        );

        question = createTextArea(headingFont);
        var qScroller = createScroller(question);

        answer = createTextArea(headingFont);
        var aScroller = createScroller(answer);

        mainPanel.add(new JLabel("Question:"));
        mainPanel.add(qScroller);
        mainPanel.add(new JLabel("Answer:"));
        mainPanel.add(aScroller);

        var nextButton = new JButton("Next Card");
        nextButton.addActionListener(_e -> nextCard());
        mainPanel.add(nextButton);

        var menuBar = new JMenuBar();
        var fileMenu = new JMenu("File");

        var newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(_e -> clearAll());

        var saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(_e -> saveCard());

        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(
            BorderLayout.CENTER,
            mainPanel
        );
        frame.setSize(500 /* px */, 600 /* px */);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JScrollPane createScroller(JTextArea textArea) {
        var scroller = new JScrollPane(textArea);

        scroller.setVerticalScrollBarPolicy(
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
        );
        scroller.setHorizontalScrollBarPolicy(
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        return scroller;
    }

    private JTextArea createTextArea(Font font) {
        var textArea = new JTextArea(6 /* rows */, 20 /* cols */);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(font);
        return textArea;
    }

    private QuizCard getCurrentCard() {
        return new QuizCard(
            question.getText(),
            answer.getText()
        );
    }

    private void nextCard() {
        // add the current card to the list
        // and clear the text areas

        var card = getCurrentCard();
        cardList.add(card);
        clearCard();
    }

    private void saveCard() {
        // bring up a file dialog box
        // let the user name and save the set

        var card = getCurrentCard();
        cardList.add(card);

        var fileSave = new JFileChooser();
        fileSave.showSaveDialog(frame);
        saveFile(fileSave.getSelectedFile());
    }

    private void clearAll() {
        cardList.clear();
        clearCard();
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");

        question.requestFocus();
    }

    private void saveFile(File file) {
        // iterate through the list of cards and write
        // each one out to a text file in a parseable way
        // (in other words, with clear separations between parts)

        try (
            var writer = new BufferedWriter(new FileWriter(file))
        ) {
            for (var card : cardList) {
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
        }
        catch (IOException exception) {
            System.err.println(
                "Couldn't write the cardList: " +
                exception.getMessage()
            );
        }
    }
}