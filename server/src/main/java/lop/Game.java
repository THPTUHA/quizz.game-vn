package lop;

import java.util.ArrayList;

import model.object.CauHoi;

public class Game {
    private ArrayList<CauHoi> questions;

    public Game() {
    }


    public Game(ArrayList<CauHoi> questions) {
        this.questions = questions;
    }


    public ArrayList<CauHoi> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<CauHoi> questions) {
        this.questions = questions;
    }

}
