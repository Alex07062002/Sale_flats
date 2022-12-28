package org.example.Model.Facade;

import org.example.Model.City;
import org.example.Model.Contract.Contract;
import org.example.Model.Contract.PayType;
import org.example.Model.Contract.StatusType;
import org.example.Model.Flat.Flat;
import org.example.Model.Flat.OwnerType;
import org.example.Model.Floor;
import org.example.Model.House;
import org.example.Model.User.UserDTO;

import java.util.List;

public class UserFacade extends Facade{


    // getAllMyContracts
    public List<Contract> getByPersonId(int personId) {
        if (getUserService().get(personId) == null) throw new RuntimeException();
        else return getContractService().getByPersonId(personId);
    }


    // delete operations
    public void deleteUser(UserDTO user){
        if(getUserService().get(user.getPersonId()) == null) throw new RuntimeException();
        else{
            List<Contract> myContracts = getContractService().getByPersonId(user.getPersonId());
            for (Contract contract : myContracts){
                getContractService().delete(contract.getContractId());
            }
            getUserService().delete(user);
        }
    }
    public void deleteContract(int contractId){
        Contract contract = getContractService().get(contractId);
        if (contract == null) throw new RuntimeException();
        else {
            Flat flat = getFlatService().get(contract.getFlatId());
            flat.setOwnerType(OwnerType.free);
            getFlatService().updateStatusFlat(flat);
            getContractService().delete(contractId);
        }
    }


    public class Filter{
        private City city = null;
        private House house = null;
        private Floor floor = null;
        private Flat flat = null;

        public void setCity(City city) {this.city = city;}
        public void setHouse(House house) {this.house = house;}
        public void setFloor(Floor floor) {this.floor = floor;}
        public void setFlat(Flat flat) {this.flat = flat;}

        public City getCityById(int cityId){
            City city = getCityService().get(cityId);
            if (city == null) throw new RuntimeException();
            else{
                setCity(getCityService().get(cityId));
                return city;
            }
        }
        public List<House> getByCityId() {
            return getHouseService().getByCityId(city.getCityId());
        }
        public House getHouseById(int houseId){
            House house = getHouseService().get(houseId);
            if (house == null) throw new RuntimeException();
            else {
                if (house.getCityId() != city.getCityId()) throw new RuntimeException();
                else {
                    setHouse(house);
                    return house;
                }
            }
        }

        // type 1
        public List<Floor> getByHouseId() {
            return getFloorService().getByHouseId(house.getHouseId());
        }

        public Floor getFloorById(int floorId){
            Floor floor = getFloorService().get(floorId);
            if (floor == null) throw new RuntimeException();
            else{
                if (floor.getHouseId() != house.getHouseId()) throw new RuntimeException();
                else{
                    setFloor(floor);
                    return floor;
                }
            }
        }
        public List<Flat> getByFloorId(){
            return getFlatService().getByFloorId(floor.getFloorId());
        }

        public List<Flat> getByCost(double low, double high) {
            if (low<0 && high<0 && low>high) throw new RuntimeException();
            else return getFlatService().getByCost(low,high, floor.getFloorId());
        }


        // type 2
        public List<Flat> getByNumberOfRooms(int numberOfRooms){ // TODO ОТЛОЖЕНО!!!
        return null;
        }

        public Flat getFlatByFilter(int flatId){
            Flat flat = getFlatService().get(flatId);
            if (flat == null) throw new RuntimeException();
            else{
                if (flat.getFloorId() != floor.getFloorId()) throw new RuntimeException();
                else {
                    setFlat(flat);
                    return flat;
                }
            }
        }

        // createContract
        public void createContract(UserDTO user, String type) {
            for (PayType pay : PayType.values()){
                if (pay.toString().equals(type)){
                    break;
                }
            }
            if (false) throw new RuntimeException();
            else {
                getContractService().create(new Contract(flat.getFlatId(), user.getPersonId(), PayType.valueOf(type), StatusType.create));
            }
        }
    }
}
