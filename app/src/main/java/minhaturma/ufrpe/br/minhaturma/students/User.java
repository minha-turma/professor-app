package minhaturma.ufrpe.br.minhaturma.students;

import minhaturma.ufrpe.br.minhaturma.commons.Entity;

public class User extends Entity {

    String name;
    String username;
    String password;

    String accessToken;

    public User(int id, String name, String username, String password, String accessToken) {
        super(id);
        this.name = name;
        this.username = username;
        this.password = password;
        this.accessToken = accessToken;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
