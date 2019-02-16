package minhaturma.ufrpe.br.minhaturma.subjects;

import java.util.List;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;

public class Subject extends Entity {

    String name;
    String topic;

    public Subject(int id) {
        super(id);
        this.name = null;
        this.topic = "Vida devolva minhas fantasias";
    }

    public Subject(int id, String name) {
        super(id);
        this.name = name;
        this.topic = "Vida";
    }

    public Subject(int id, String name, String topic) {
        super(id);
        this.name = name;
        this.topic = "Ximbalaie";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }
}
