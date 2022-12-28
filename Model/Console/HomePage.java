package org.example.Model.Console;

import org.example.DAO.UserService;
import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;
import org.example.Model.Console.Regist_and_Auth.DefinitionFactory;
import org.example.Model.Console.Regist_and_Auth.DefinitionType;
import org.example.Model.User.User;
import org.example.Model.User.UserDTO;

public class HomePage{
    private SiteUI ui;
    private PersonUI perUI;


    public HomePage (SiteUI ui, PersonUI perUI){
        this.ui = ui;
        this.perUI = perUI;
    }

    public UserDTO start_work(UserService userService) {
        this.ui.messageToUser("Hello, user. Do you have personal account? (Y/N)");
        String condition = this.perUI.answerFromUser();
        switch (condition) {
            case "Y" -> {
                this.ui.messageToUser("Ok. Let's go to authorization form.");
                 UserDTO user = DefinitionFactory.getInstance().createForm(DefinitionType.AUTH, this.ui, this.perUI).form(userService);
                 return user;
            }
            case "N" -> {
                this.ui.messageToUser("It's sad. Let's go to registration form.");
                UserDTO user = DefinitionFactory.getInstance().createForm(DefinitionType.REGIST, this.ui, this.perUI).form(userService);
                return user;
            }
            default -> {
                this.ui.messageToUser("You choose bad answer.");
                return null;
            }
        }
    }
}
