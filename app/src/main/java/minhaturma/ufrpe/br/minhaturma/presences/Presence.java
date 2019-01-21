package minhaturma.ufrpe.br.minhaturma.presences;

import java.util.Date;

import minhaturma.ufrpe.br.minhaturma.lectures.Lecture;
import minhaturma.ufrpe.br.minhaturma.students.Student;
import minhaturma.ufrpe.br.minhaturma.students.User;
import minhaturma.ufrpe.br.minhaturma.subjects.Subject;

public class Presence {

    Lecture lecture;
    Student student;

    public Presence(Lecture lecture, Student student) {
        this.lecture = lecture;
        this.student = student;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
