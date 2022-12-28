package org.example.Model.Console.Menu;

import org.example.DAO.*;
import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;

public abstract class Data {

    private final CityService cityService;

    private final ContractService contractService;

    private final FlatService flatService;

    private final FloorService floorService;

    private final HouseService houseService;

    private final SiteUI ui;

    private final Answer answer;

    private final PersonUI perUi;

    public SiteUI getUi() {
        return ui;
    }

    public Answer getAnswer() {
        return answer;
    }

    public PersonUI getPerUi() {
        return perUi;
    }

    public CityService getCityService() {
        return cityService;
    }

    public ContractService getContractService() {
        return contractService;
    }

    public FlatService getFlatService() {
        return flatService;
    }

    public FloorService getFloorService() {
        return floorService;
    }

    public HouseService getHouseService() {
        return houseService;
    }

    public Data(CityService cityService, ContractService contractService, FlatService flatService, FloorService floorService, HouseService houseService, SiteUI ui, Answer answer, PersonUI perUi) {
        this.cityService = cityService;
        this.contractService = contractService;
        this.flatService = flatService;
        this.floorService = floorService;
        this.houseService = houseService;
        this.ui = ui;
        this.answer = answer;
        this.perUi = perUi;
    }
}
