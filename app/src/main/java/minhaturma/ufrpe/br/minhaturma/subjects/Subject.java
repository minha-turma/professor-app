package minhaturma.ufrpe.br.minhaturma.subjects;

import java.util.List;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;

public class Subject extends Entity {

    String name;
    List<String> topics;

    public Subject(int id) {
        super(id);
        this.name = null;
        this.topics = null;
    }

    public Subject(int id, String name) {
        super(id);
        this.name = name;
        this.topics = null;
    }

    public Subject(int id, String name, List<String> topics) {
        super(id);
        this.name = name;
        this.topics = topics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopic() {
        return topics;
    }

    public void setTopic(List<String> topic) {
        this.topics = topic;
    }
}
