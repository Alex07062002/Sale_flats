package org.example.Model.Console.Menu;

import org.example.DAO.*;
import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;

public class NewData extends Data {

    public NewData(CityService cityService, ContractService contractService, FlatService flatService, FloorService floorService, HouseService houseService, Answer answer, SiteUI ui, PersonUI perUi) {
        super(cityService, contractService, flatService, floorService, houseService, ui, answer, perUi);
    }

    public void createNewObjects(){
        int condition = getAnswer().answerNumber("1 - город\n 2 - дом\n 3 - этаж\n 4 - квартира\n 5 - контракт\n");
        switch (condition){
            case(1) -> {}
            case(2) -> {}
            case(3) -> {}
            case(4) -> {}
            case(5) -> {}
            default -> getUi().messageToUser("Bad choice");
        }
    }


}
