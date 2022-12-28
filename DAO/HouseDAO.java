package org.example.DAO;

import org.example.Model.House;

import java.util.List;

public interface HouseDAO extends DAO<House>{

    House get(int houseId);
    List<House> getByCityId(int id);

}
