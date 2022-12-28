package org.example.Model.Console.Regist_and_Auth;

import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;


public class DefinitionFactory {
    private static volatile DefinitionFactory definitionFactory;

    private DefinitionFactory(){}

    public static DefinitionFactory getInstance(){
        if (definitionFactory == null){
            synchronized (DefinitionFactory.class){
                if (definitionFactory == null){
                    definitionFactory = new DefinitionFactory();
                }
            }
        }
        return definitionFactory;
    }
    public  Definition_Person createForm(DefinitionType type, SiteUI ui, PersonUI perUI) throws NullPointerException{
        Definition_Person definition = null;
        try {
            switch (type) {
                case REGIST -> definition = new Registration(ui, perUI);
                case AUTH -> definition = new Authorization(ui, perUI);

            }
        }catch (NullPointerException e){
            throw new NullPointerException("We have some problems with definition...");
        }
        return definition;
    }
}
