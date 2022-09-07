package com.example.quizapp;

public class DatabaseClass {

    String Question;
    String QA;
    String QB;
    String QC;
    String QD;
    String ans;

    public DatabaseClass() {

    }

    public DatabaseClass(String question, String QA, String QB, String QC, String QD, String ans) {
        Question = question;
        this.QA = QA;
        this.QB = QB;
        this.QC = QC;
        this.QD = QD;
        this.ans = ans;

    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getQA() {
        return QA;
    }

    public void setQA(String QA) {
        this.QA = QA;
    }

    public String getQB() {
        return QB;
    }

    public void setQB(String QB) {
        this.QB = QB;
    }

    public String getQC() {
        return QC;
    }

    public void setQC(String QC) {
        this.QC = QC;
    }

    public String getQD() {
        return QD;
    }

    public void setQD(String QD) {
        this.QD = QD;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
