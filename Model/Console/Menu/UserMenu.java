package org.example.Model.Console.Menu;

import org.example.DAO.UserService;
import org.example.Model.Contract.Contract;
import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;
import org.example.Model.User.User;
import org.example.Model.User.UserDTO;

public class UserMenu extends AbstractMenu implements Menu{

    public UserMenu(SiteUI ui, PersonUI perUi, UserDTO user, UserService userService) {
        super(ui,perUi,user,userService);
    }

    @Override
    public String getInfoAboutCompany() {
        return null;
    }

    @Override
    public User updateUser() {
        return null;
    }

    @Override
    public Contract createContract() { //TODO автозаполнение на основе user (передан в качестве параметра) + flat (JDBC)
        return null;
    }
}
