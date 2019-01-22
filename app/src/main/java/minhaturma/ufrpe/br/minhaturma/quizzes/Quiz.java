package minhaturma.ufrpe.br.minhaturma.quizzes;

import java.util.List;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;
import minhaturma.ufrpe.br.minhaturma.students.User;

public class Quiz extends Entity {

    String statement;
    List<String> alternatives;
    String correct;
    User owner;

    public Quiz(int id, String statement, List<String> alternatives, String correct, User owner) {
        super(id);
        this.statement = statement;
        this.alternatives = alternatives;
        this.correct = correct;
        this.owner = owner;
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
}
