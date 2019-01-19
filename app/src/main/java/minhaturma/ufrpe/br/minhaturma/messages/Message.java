package minhaturma.ufrpe.br.minhaturma.messages;

import javax.security.auth.Subject;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;
import minhaturma.ufrpe.br.minhaturma.students.User;

public class Message extends Entity {

    String subject;
    String content;

    User owner;

    public Message(int id, String subject, String content, User owner) {
        super(id);
        this.subject = subject;
        this.content = content;
        this.owner = owner;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
