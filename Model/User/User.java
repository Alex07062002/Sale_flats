package org.example.Model.User;

import org.jetbrains.annotations.NotNull;

public class User extends SuperUser{

    @NotNull
    private String pswd;


    public String getPswd() {
        return pswd;
    }


    public User(@NotNull String name,@NotNull String email,@NotNull String login,@NotNull String pswd,@NotNull UserType type) {
        super(name, email, login,type);
        this.pswd = pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

}
