package org.example.DAO;

import org.example.Model.House;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class HouseService implements HouseDAO {

    private final SQLQuery requester = new SQLQuery();

    private final ExecuteAround<House> extractor = rs -> {
        List<House> list = new ArrayList<>();
        while (rs.next()) {
            final House house = new House(rs.getInt("city_id"), rs.getString("address"),
                    rs.getString("name"), rs.getDate("constructionstartdate"),
                    rs.getDate("constructioncompletiondate"),
                    rs.getDate("commissioning"));
            house.setHouseId(rs.getInt("house_id"));
            list.add(house);
        }
        return list;
    };

    @Override
    public List<House> getAll() {
        String query = "SELECT * FROM house";
        return requester.executeQuery(query, null, extractor);
    }

    @Override
    public void create(House house) {
        String query = "INSERT INTO house (city_id,address,name,constructionstartdate,constructioncompletiondate," +
                "commissioning) values (?,?,?,?,?,?)";
        StatementConsumer consumer = statement -> {
        statement.setInt(1,house.getHouseId());
        statement.setString(2,house.getAddress());
        statement.setString(3,house.getName());
        statement.setDate(4, new Date(house.getConstructionStartDate().getTime()));
        statement.setDate(5, new Date(house.getConstructionCompletionDate().getTime()));
        if (house.getCommissioning() != null) statement.setDate(6, new Date(house.getCommissioning().getTime()));
        else statement.setNull(6, Types.DATE);
        };
           requester.executeUpdate(query,consumer);
    }

    @Override
    public void update(int id, List<?> params) {
        String query = "UPDATE house SET city_id = ?, address = ?, name = ?," +
                " constructionstartdate = ?, constructioncompletiondate = ?,commissioning = ? WHERE house_id = ?";
        StatementConsumer consumer = statement -> {
          requester.updateFromList(statement,params);
          statement.setInt(params.size()+1, id);
        };
        requester.executeUpdate(query,consumer);
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM house WHERE house_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1,id);
        requester.executeUpdate(query,consumer);
    }

    @Override
    public House get(int houseId) {
        String query = "SELECT * FROM house WHERE house_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, houseId);
        List<House> houses = requester.executeQuery(query,consumer,extractor);
        return houses.size() == 1 ? houses.get(0) : null;
    }

    @Override
    public List<House> getByCityId(int id) {
        String query = "SELECT * FROM house WHERE city_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, id);
        return requester.executeQuery(query,consumer,extractor);
    }
}
