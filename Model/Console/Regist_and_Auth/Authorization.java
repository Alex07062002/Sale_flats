package org.example.Model.Console.Regist_and_Auth;

import org.example.DAO.UserService;
import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;
import org.example.Model.User.User;
import org.example.Model.User.UserDTO;

public class Authorization extends Definition_Person {

    public Authorization(SiteUI ui, PersonUI perUI){
       super(ui,perUI);
    }

    /**
     * login is unique value for each person
     */
    @Override
    public UserDTO form(UserService userService){
        this.getUi().messageToUser("Введите login");
        String login = this.getPerUI().answerFromUser();
        this.getUi().messageToUser("Введите password");
        String pswd = this.getPerUI().answerFromUser();
        return !(login.equals("") || pswd.equals("")) ? userService.authorization_form(login,pswd) : null;
    }
}
