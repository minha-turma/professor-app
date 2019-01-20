package minhaturma.ufrpe.br.minhaturma.students;

public class Student extends User {

    String feeling;

    public Student(String username, String password) {
        super(0, null, username, password, null);
        this.feeling = null;
    }

    public Student(int id, String name, String username, String password, String accessToken, String feeling) {
        super(id, name, username, password, accessToken);
        this.feeling = feeling;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }
}
