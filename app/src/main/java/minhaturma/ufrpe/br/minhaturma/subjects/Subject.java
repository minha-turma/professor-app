package minhaturma.ufrpe.br.minhaturma.subjects;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;

public class Subject extends Entity {

    String name;

    public Subject(int id) {
        super(id);
        this.name = null;
    }

    public Subject(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
