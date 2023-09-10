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
    }

    private void nextCard() {
        // if this is a question, show the answer, otherwise show
        // next question set a flag for whether we’re viewing a
        // question or answer
    }

    private void open() {
        // bring up a file dialog box
        // let the user navigate to and choose a card set to open
    }

    private void loadFile(File file) {
        // must build an ArrayList of cards, by reading them from
        // a text file called from the OpenMenuListener event handler,
        // reads the file one line at a time and tells the makeCard()
        // method to make a new card out of the line (one line in the
        // file holds both the question and answer, separated by a “/”)
    }

    private void makeCard(String lineToParse) {
        // called by the loadFile method, takes a line from the text file
        // and parses into two pieces—question and answer—and creates a
        // new QuizCard and adds it to the ArrayList called CardList
    }
}
