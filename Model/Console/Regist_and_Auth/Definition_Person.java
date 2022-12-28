package org.example.Model.Console.Regist_and_Auth;

import org.example.DAO.UserService;
import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;
import org.example.Model.User.User;
import org.example.Model.User.UserDTO;

public abstract class Definition_Person {

    public SiteUI getUi() {
        return ui;
    }

    private final SiteUI ui;

    public PersonUI getPerUI() {
        return perUI;
    }

    private final PersonUI perUI;

    public Definition_Person(SiteUI ui,PersonUI perUI){
        this.ui = ui;
        this.perUI = perUI;
    }

    public abstract UserDTO form(UserService userService);
}
