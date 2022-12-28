package org.example.Model.Facade;


import org.example.DAO.*;
import org.example.Model.City;
import org.example.Model.User.User;
import org.example.Model.User.UserDTO;

import java.util.List;


public abstract class Facade {
        private final CostM2Service costM2Service = new CostM2Service();
        private final CityService cityService = new CityService();
        private final ContractService contractService = new ContractService();
        private final FlatService flatService = new FlatService();
        private final FloorService floorService = new FloorService();
        private final HouseService houseService = new HouseService();
        private final UserService userService = new UserService();

        public CostM2Service getCostM2Service() {return costM2Service;}

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

        public UserService getUserService() {
                return userService;
        }

        public UserDTO authorization(String login, String password){return userService.authorization_form(login,password);}

        public UserDTO registration(User user){
                return userService.create(user);
        }

        public void updateUser(UserDTO user, List<?> params){
                if (params.size() != 2) throw new RuntimeException();
                else {
                        if ((!(params.get(0) instanceof String) || (!(params.get(1) instanceof String))))
                                throw new IllegalArgumentException();
                        else userService.update(user,params);
                }
        }
        public List<City> getAllCities(){
                return cityService.getAll();
        }

}
