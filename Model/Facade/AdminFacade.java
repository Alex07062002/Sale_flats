package org.example.Model.Facade;

import org.apache.commons.lang3.tuple.Pair;
import org.example.DAO.PriceService;
import org.example.Model.City;
import org.example.Model.Contract.Contract;
import org.example.Model.Contract.StatusType;
import org.example.Model.Flat.Flat;
import org.example.Model.Flat.OwnerType;
import org.example.Model.Flat.PriceType;
import org.example.Model.Flat.StrategyCost.Price;
import org.example.Model.Flat.StrategyCost.PriceForM2;
import org.example.Model.Floor;
import org.example.Model.House;
import org.example.Model.User.UserDTO;

import java.util.Date;
import java.util.List;

public class AdminFacade extends Facade{

    private final PriceService priceService = new PriceService();

    //getAll operations
    public List<House> getAllHouses(){
        return getHouseService().getAll();
    }
    public List<Floor> getAllFloors(){
        return getFloorService().getAll();
    }
    public List<Flat> getAllFlats(){
        return getFlatService().getAll();
    }
    public List<PriceForM2> getAllPricesForM2(){
        return getCostM2Service().getAll();
    }
    public List<Contract> getAllContracts(){return getContractService().getAll();}
    public List<Price> getAllPrices(){ return priceService.getAll();}


    // delete operations
    public void deleteUser(UserDTO user){
        getUserService().delete(user);
    }
    public void deleteFlat(int id) {
        List<Contract> contracts = getContractService().getByFlatId(id);
        if (contracts.isEmpty()) getFlatService().delete(id);
        else {
            for (Contract contract : contracts) {
                if (!contract.getStatusType().equals(StatusType.cancel)) {
                    throw new RuntimeException(contract+"isn't cancelled!!! Call to"+contract.getPersonId());
                    // TODO Exception (list<contract> aren't cancelled)
                }
            }
            getFlatService().delete(id);
            for (Contract contract : contracts) {
                getContractService().delete(contract.getContractId());
            }
        }
    }
    private double returnMultiplier(PriceType type, List<Price> list){
        for (Price price: list){
            if (price.type().equals(type)){
                return price.multiplier();
            }
        }
        return -1;
    }
    public void deletePriceForM2(int rooms){
        PriceForM2 price = getCostM2Service().get(rooms);
        List<Price> multiplier = priceService.getAll();
        List<Flat> flats = getFlatService().getAll();
        for (Flat flat: flats){
            for (PriceType type : PriceType.values()){
                if (flat.getPriceType().equals(type) && flat.getCost() == flat.getSquare()*price.price()*returnMultiplier(type,multiplier)){
                    throw new RuntimeException(); //TODO Exception
                }
            }
        }
        getCostM2Service().delete(rooms);
    }

    public void deleteFloor(int id){
        List<Flat> flats = getFlatService().getByFloorId(id);
        if (!flats.isEmpty()){
            throw new RuntimeException(); //TODO Exception
        }
        getFloorService().delete(id);
    }

    public void deleteHouse(int id){
        List<Floor> floors = getFloorService().getByHouseId(id);
        if (!floors.isEmpty()){
            throw new RuntimeException();  //TODO Exception
        }
        getHouseService().delete(id);
    }
    public void deleteCity(int id){
        List<House> houses = getHouseService().getByCityId(id);
        if (!houses.isEmpty()){
            throw new RuntimeException();  //TODO Exception
        }
        getCityService().delete(id);
    }
    public void deletePrice(PriceType type) {
        List<Flat> flats = getFlatService().getAll();
        for (Flat flat : flats) {
            if (flat.getPriceType().equals(type)) {
                throw new RuntimeException(); //TODO Exception
            }
        }
    priceService.delete(type);
    }



    //create operations
    public void createCity(City city){
        getCityService().create(city);
    }
    public void createHouse(House house){
        if (getCityService().get(house.getCityId()) == null){
            throw new RuntimeException();
        }
        getHouseService().create(house);
    }
    public void createFloor(Floor floor) {
        if (getHouseService().get(floor.getHouseId()) == null) {
            throw new RuntimeException();
        }
        if (getFloorService().maxFloorInHouse(floor.getHouseId()) != floor.getFloor() + 1) {
            throw new RuntimeException();
        }
        getFloorService().create(floor);
        List<Pair<String,Integer>> floors = getFloorService().floorWithLowCostFlats(floor.getHouseId());
        for (Pair<String,Integer> pair : floors) {
            if (pair.getLeft().equals("Upper")) {
                List<Flat> flats = getFlatService().getByFloorId(pair.getRight());
                for (Flat flat : flats) {
                    if (flat.getOwnerType().equals(OwnerType.free)) {
                        flat.setPriceType(PriceType.full);
                        Price price = priceService.get(PriceType.full);
                        int maxRoomsWithPrice = getCostM2Service().maxRooms();
                        PriceForM2 priceForM2;
                        if (maxRoomsWithPrice <= flat.getNumberRooms()) {
                            priceForM2 = getCostM2Service().get(maxRoomsWithPrice);
                        } else priceForM2 = getCostM2Service().get(flat.getNumberRooms());
                        flat.setCost(price.setCostAuto(flat.getSquare(), priceForM2.price()));
                        getFlatService().updateCost(flat);
                    }
                }
            }
        }
    }

    public void createFlat(Flat flat){
        if (getFloorService().get(flat.getFloorId()) == null){
            throw new RuntimeException();
        }
        int houseId = getFloorService().houseIdByFloorId(flat.getFloorId());
        List<Pair<String,Integer>> floors = getFloorService().floorWithLowCostFlats(houseId);
        for (Pair<String,Integer> pair : floors){
            if (pair.getRight() == flat.getFloorId()){
                if (pair.getLeft().equals("Lower")){
                    flat.setPriceType(PriceType.low);
                    Price price = priceService.get(PriceType.low);
                    int maxRoomsWithPrice = getCostM2Service().maxRooms();
                    PriceForM2 priceForM2;
                    if (maxRoomsWithPrice<=flat.getNumberRooms()){
                        priceForM2 = getCostM2Service().get(maxRoomsWithPrice);
                    }
                    else priceForM2 = getCostM2Service().get(flat.getNumberRooms());
                    flat.setCost(price.setCostAuto(flat.getSquare(),priceForM2.price()));
                }
            }
        }
        getFlatService().create(flat);
    }
    public void createPriceForM2(PriceForM2 price){
        int maxRoomsWithPrice = getCostM2Service().maxRooms();
        if (price.numberOfRooms() != maxRoomsWithPrice+1){
            throw new RuntimeException();
        }
        getCostM2Service().create(price);
    }
    public void createPrice(Price price){
        if (PriceType.values().length == priceService.countRows()){
            throw new RuntimeException();
        }
        else{
         priceService.create(price); // TODO WARNING!!! метод не защищён от неправильного ввода коэффициента
        }
    }



    //update operations
    public void updateCity(int id, List<?> params){
        if (getCityService().get(id) == null) throw new RuntimeException();
        else getCityService().update(id,params);
    }
    public void updateHouse(int id, List<?> params) {
        if (getHouseService().get(id) == null) throw new RuntimeException();
        else {
            if (params.size() != 6) throw new RuntimeException();
            else {
                if (!((params.get(0)) instanceof Integer)) throw new IllegalArgumentException();
                else {
                    if (getCityService().get(Integer.parseInt(params.get(0).toString())) == null)
                        throw new RuntimeException();
                    else {
                        if ((!(params.get(1) instanceof String)) || (!(params.get(2) instanceof String)))
                            throw new IllegalArgumentException();
                        else {
                            if ((!(params.get(3) instanceof Date)) || (!(params.get(4) instanceof Date)) ||
                                    new Date((long) params.get(3)).after(new Date((long) params.get(4))))
                                throw new IllegalArgumentException();
                            else {
                                if ((!(params.get(5) instanceof Date)) || (params.get(5) != null))
                                    throw new IllegalArgumentException();
                                else getHouseService().update(id, params);
                            }
                        }
                    }
                }
            }
        }
    }

    public void updateFloor(int id, List<?> params){
        if (getFloorService().get(id) == null) throw new RuntimeException();
        else {
            if (params.size() != 1) {
                throw new RuntimeException();
            } else {
                if (!(params.get(0) instanceof Integer)) {
                    throw new IllegalArgumentException();
                }
                getFloorService().update(id, params);
            }
        }
    }
    public void updateFlat(int id, List<?> params){
        if (getFlatService().get(id) == null) throw new RuntimeException();
        else {
            if (params.size() != 3) {
                throw new RuntimeException();
            } else {
                if ((!(params.get(0) instanceof Integer)) || (!(params.get(1) instanceof Integer)))
                    throw new IllegalArgumentException();
                else {
                    for (OwnerType type : OwnerType.values()) {
                        if (params.get(2).toString().equals(type.toString())) {
                            break;
                        }
                    }
                    if (false) throw new IllegalArgumentException();
                    else getFlatService().update(id, params);
                }
                getFloorService().update(id, params);
            }
        }
    }
    public void updateContract(int id, List<?> params) { // TODO по-хорошему ввести роль "риелтор", который обрабатывает контракты
        if (getContractService().get(id) == null) throw new RuntimeException();
        else {
            if (params.size() != 1) {
                throw new RuntimeException();
            }
            for (StatusType type : StatusType.values()) {
                if (params.get(0).equals(type.toString())) {
                    break;
                }
            }
            if (false) throw new RuntimeException(); // TODO Realized flag
            else getContractService().update(id, params);
        }
    }
    public void updatePriceForM2(int countOfRooms, List<?> params){
        if (getCostM2Service().get(countOfRooms) == null) throw new RuntimeException();
        else {
            if (params.size() != 1) {
                throw new RuntimeException();
            } else {
                if (!(params.get(0) instanceof Integer)) {
                    throw new IllegalArgumentException();
                }
                getCostM2Service().update(countOfRooms, params);
            }
        }
    }
    public void updatePrice(String type,List<?> params) {
        for (PriceType price : PriceType.values()) {
            if (price.toString().equals(type)) {
                break;
            }
        }
        if (false) throw new RuntimeException();
        else {
            if (priceService.get(PriceType.valueOf(type)) == null) throw new RuntimeException();
            else {
                if (params.size() != 1) {
                    throw new RuntimeException();
                } else {
                    if (!(params.get(0) instanceof Double)) {
                        throw new IllegalArgumentException();
                    } else priceService.update(PriceType.valueOf(type), params);
                }
            }
        }
    }



    //get and getByFilter operations
    public List<Contract> getByFlatId(int flatId) {
        if (getFlatService().get(flatId) == null) throw new RuntimeException();
        else return getContractService().getByFlatId(flatId);
    }
    public List<Contract> getByPersonId(int personId) {
        if (getUserService().get(personId) == null) throw new RuntimeException();
        else return getContractService().getByPersonId(personId);
    }
    public List<Contract> getByStatus(String type) {
        for (StatusType status : StatusType.values()){
            if (status.toString().equals(type)){
                break;
            }
        }
        if (false) throw new RuntimeException();
        else return getContractService().getByStatus(StatusType.valueOf(type));
    }
    public List<Floor> getByHouseId(int houseId) {
        if (getHouseService().get(houseId) == null) throw new RuntimeException();
        else return getFloorService().getByHouseId(houseId);
    }
    public List<Flat> getByFloorId(int floorId) {
        if (getFloorService().get(floorId) == null) throw new RuntimeException();
        else return getFlatService().getByFloorId(floorId);
    }
    public List<Flat> getByOwner(String type) {
        for (OwnerType owner : OwnerType.values()){
            if (owner.toString().equals(type)){
                break;
            }
        }
        if (false) throw new RuntimeException();
        else return getFlatService().getByOwner(OwnerType.valueOf(type));
    }
    public List<House> getByCityId(int cityId) {
        if (getCityService().get(cityId) == null) throw new RuntimeException();
        else return getHouseService().getByCityId(cityId);
    }
    public UserDTO getByLogin(String login) {
        return getUserService().getByLogin(login);
    }
}
