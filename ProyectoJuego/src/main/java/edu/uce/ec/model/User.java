package edu.uce.ec.model;

public class User {
    private long id;
    private String name;
    private String password;
    private int health;
    private int score;

    public User(long id, String name, String password, int health, int score) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.health = health;
        this.score = score;
    }

    public User(String name, String password, int health, int score) {
        this.name = name;
        this.password = password;
        this.health = health;
        this.score = score;
    }


    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

