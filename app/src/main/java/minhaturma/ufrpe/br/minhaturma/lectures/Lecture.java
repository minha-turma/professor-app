package minhaturma.ufrpe.br.minhaturma.lectures;

import java.util.Date;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;
import minhaturma.ufrpe.br.minhaturma.students.User;
import minhaturma.ufrpe.br.minhaturma.subjects.Subject;

public class Lecture extends Entity {

    Date date;
    Subject subject;
    User owner;
    boolean isOpen;

    public Lecture(int id, Date date, Subject subject, User owner, boolean isOpen) {
        super(id);
        this.date = date;
        this.subject = subject;
        this.owner = owner;
        this.isOpen = isOpen;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
