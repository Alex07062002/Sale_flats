package org.example.DAO;

import org.example.Model.City;

import java.util.ArrayList;
import java.util.List;

public class CityService implements CityDAO{

    private final SQLQuery requester = new SQLQuery();

    private final ExecuteAround<City> extractor = rs -> {
        List<City> list = new ArrayList<>();
        while (rs.next()) {
            final City city = new City(
                    rs.getString("name")
            );
            city.setCityId(rs.getInt("city_id"));
            list.add(city);
        }
        return list;
    };


    @Override
    public List<City> getAll() {
        String query = "SELECT * FROM city";
        return requester.executeQuery(query, null,extractor);
    }


    @Override
    public void create(City city){
        String query = "INSERT INTO city (name) values (?)";
        StatementConsumer consumer = statement -> statement.setString(1, city.getName());
        requester.executeUpdate(query,consumer);
    }


    @Override
    public void update(int id, List<?> params){
        String query = "UPDATE city SET name = ? WHERE  city_id = ?";
        StatementConsumer consumer = statement -> {
            requester.updateFromList(statement,params);
            statement.setInt(2,id);
        };
        requester.executeUpdate(query,consumer);
    }

    @Override
    public void delete(int id){
        String query = "DELETE FROM city WHERE city_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, id);
        requester.executeUpdate(query,consumer);
    }

    @Override
    public City get(int id) {
        String query = "SELECT * FROM city WHERE city_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, id);
        List<City> cities = requester.executeQuery(query,consumer,extractor);
        return cities.size() == 1 ? cities.get(0) : null;
    }

    /**
     * В PostgreSQL слова с заглавными буквами дополнительно экранируются
     * https://translated.turbopages.org/proxy_u/en-ru.ru.5271a39f-63857834-7b9c9c35-74722d776562/https/stackoverflow.com/questions/15275971/error-column-does-not-exist
     *
     */

    @Override
    public City getByName(String name){
        String query = "SELECT * FROM city WHERE name = ?";
        StatementConsumer consumer = statement -> statement.setString(1,name);
        List<City> cities = requester.executeQuery(query,consumer,extractor);
        return cities.size()>0 ? cities.get(0) : null;
    }
}

