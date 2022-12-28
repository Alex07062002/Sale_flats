package org.example.Model.Console.Menu;

import org.example.DAO.*;
import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;
import org.example.Model.Console.Site;
import org.example.Model.User.User;
import org.example.Model.User.UserDTO;


public abstract class AbstractMenu extends Site {

    private final UserService userService;

    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public UserService getUserService() {
        return userService;
    }


    public void setUser(UserDTO user) {
        this.user = user;
    }

    public AbstractMenu(SiteUI ui, PersonUI perUi, UserDTO user, UserService userService) {
        super(ui,perUi);
        this.user = user;
        this.userService = userService; // TODO abstract class for Services + pattern Factory???
    }
}

