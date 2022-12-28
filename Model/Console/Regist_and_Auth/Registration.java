package org.example.Model.Console.Regist_and_Auth;

import org.example.DAO.UserService;
import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;
import org.example.Model.User.User;
import org.example.Model.User.UserDTO;
import org.example.Model.User.UserFactory;
import org.example.Model.User.UserType;



public class Registration extends Definition_Person {

    public Registration(SiteUI ui, PersonUI perUI){
        super(ui, perUI);
    }
    @Override
    public UserDTO form(UserService userService){
        User user;
        this.getUi().messageToUser("Введите name");
        String name = this.getPerUI().answerFromUser();
        this.getUi().messageToUser("Введите email");
        String email = this.getPerUI().answerFromUser();
        this.getUi().messageToUser("Введите login");
        String login = this.getPerUI().answerFromUser();
        this.getUi().messageToUser("Введите password");
        String pswd = this.getPerUI().answerFromUser();
        this.getUi().messageToUser("Вы пользователь или админ? (U/A)");
        if (this.getPerUI().answerFromUser().equals("U"))  {
             user = UserFactory.getInstance().createUser(UserType.user, name, email, login, pswd);
        } else if (this.getPerUI().answerFromUser().equals("A")) {
            user = UserFactory.getInstance().createUser(UserType.admin, name, email, login, pswd);
        }else{
            user = null;
        }
        return (user != null) ? userService.create(user) : null;
    }
}

