package minhaturma.ufrpe.br.minhaturma.quizzes;

import java.util.List;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;
import minhaturma.ufrpe.br.minhaturma.students.User;

public class Quiz extends Entity {

    String statement;
    List<String> alternatives;
    String correct;
    User owner;
    boolean isOpen;
    String topic;

    public Quiz(int id, String statement, List<String> alternatives, String correct, User owner, boolean IsOpen, String topic) {
        super(id);
        this.statement = statement;
        this.alternatives = alternatives;
        this.correct = correct;
        this.owner = owner;
        this.isOpen = IsOpen;
        this.topic = topic;
    }

    public Quiz(int id) {
        super(id);
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "statement='" + statement + '\'' +
                ", alternatives=" + alternatives +
                ", correct='" + correct + '\'' +
                ", owner=" + owner +
                ", IsOpen=" + isOpen +
                ", topic='" + topic + '\'' +
                '}';
    }
}
