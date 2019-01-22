package minhaturma.ufrpe.br.minhaturma.quizzes;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;
import minhaturma.ufrpe.br.minhaturma.students.Student;

public class Answer {

    Quiz quiz;
    Student student;
    String choice;

    public Answer(Quiz quiz, Student student, String choice) {
        this.quiz = quiz;
        this.student = student;
        this.choice = choice;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
