package minhaturma.ufrpe.br.minhaturma.lectures;

import android.util.Log;

import java.util.Date;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;
import minhaturma.ufrpe.br.minhaturma.students.User;
import minhaturma.ufrpe.br.minhaturma.subjects.Subject;

public class Lecture extends Entity {

    Date date;
    Subject subject;
    User owner;
    String topic;
    boolean isOpen;

    public Lecture(int id, Date date, Subject subject, String topic, User owner, boolean isOpen) {
        super(id);
        this.date = date;
        this.subject = subject;
        this.owner = owner;
        this.isOpen = isOpen;
        this.topic = topic;

        Log.e("LECTURE","Subject: "+subject+" Topic: "+topic);
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

    public String getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "date=" + date +
                ", subject=" + subject.getName() +
                ", topic=" + topic +
                '}';
    }
}
