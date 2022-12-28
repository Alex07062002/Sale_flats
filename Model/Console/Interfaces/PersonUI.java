package org.example.Model.Console.Interfaces;

import java.text.ParseException;
import java.util.Date;

public interface PersonUI {

    String answerFromUser();

    int answerNumFromUser();

    Date answerDateFromUser() throws ParseException;

    double answerDoubleFromUser();

}
