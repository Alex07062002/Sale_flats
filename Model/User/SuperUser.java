package org.example.Model.User;

public abstract class SuperUser {
    private int personId;

    private final String name;

    private final String email;

    private final String login;

    private final UserType type;

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public UserType getType() {
        return type;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public SuperUser(String name, String email, String login, UserType type) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.type = type;
    }
}
