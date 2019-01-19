package minhaturma.ufrpe.br.minhaturma.students;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;

public class User extends Entity {

    String name;
    String username;
    String password;

    String access_token;

    public User(int id, String name, String username, String password, String accessToken) {
        super(id);
        this.name = name;
        this.username = username;
        this.password = password;
        this.access_token = accessToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String accessToken) {
        this.access_token = accessToken;
    }
}
