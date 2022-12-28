package org.example.Model.Console;

import org.example.DAO.UserService;
import org.example.Model.Console.HomePage;
import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;
import org.example.Model.Console.Menu.AdminMenu;
import org.example.Model.Console.Menu.UserMenu;
import org.example.Model.User.User;
import org.example.Model.User.UserDTO;
import org.example.Model.User.UserType;

public class Site {

    private final SiteUI ui;

    private final PersonUI perUI;

    private final UserService userService;

    private UserDTO user;

    public SiteUI getUi() {
        return ui;
    }

    public PersonUI getPerUI() {
        return perUI;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserDTO getUser() {
        return user;
    }


    public Site (SiteUI ui, PersonUI perUI){
        this.ui = ui;
        this.perUI = perUI;
        this.userService = new UserService();
        this.user = null;

    }

    public void site_working() {
        while (true) {
            HomePage homePage = new HomePage(ui, perUI);
            while (user == null) {
                 user = homePage.start_work(userService);
            }
            if (user.getType().equals(UserType.admin)) {
                AdminMenu menu = new AdminMenu(ui, perUI, user, userService);
                while (user != null) {

                }
            } else {
                UserMenu menu = new UserMenu(ui, perUI, user, userService);
                while (user != null) {

                }
            }
        }
    }
}



