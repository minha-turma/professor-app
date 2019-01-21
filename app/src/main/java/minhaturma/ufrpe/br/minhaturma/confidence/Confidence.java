package minhaturma.ufrpe.br.minhaturma.confidence;

import minhaturma.ufrpe.br.minhaturma.students.Student;
import minhaturma.ufrpe.br.minhaturma.subjects.Subject;

public class Confidence {

    String status;
    Student student;
    Subject subject;

    public Confidence(String status, Student student, Subject subject) {
        this.status = status;
        this.student = student;
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
