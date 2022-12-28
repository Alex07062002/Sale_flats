package org.example.Model.Console.Menu;

import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;

import java.text.ParseException;
import java.util.Date;

public class Answer {

    private final SiteUI ui;

    private final PersonUI perUI;

    public SiteUI getUi() {
        return ui;
    }

    public PersonUI getPerUI() {
        return perUI;
    }

    public Answer(SiteUI ui, PersonUI perUI) {
        this.ui = ui;
        this.perUI = perUI;
    }

    public String answerString(String string){
        getUi().messageToUser(string);
        return getPerUI().answerFromUser();
    }

    public int answerNumber(String string){
        getUi().messageToUser(string);
        return getPerUI().answerNumFromUser();
    }

    public double answerDouble(String string){
        getUi().messageToUser(string);
        return getPerUI().answerDoubleFromUser();
    }

    public Date answerDate(String string) throws ParseException {
        getUi().messageToUser(string);
        return getPerUI().answerDateFromUser();
    }
}
