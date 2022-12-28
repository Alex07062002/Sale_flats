package org.example.DAO;

import org.example.Model.City;


public interface CityDAO extends DAO<City> {

    City get(int id);
    City getByName(String param);
}
