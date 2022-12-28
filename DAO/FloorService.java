package org.example.DAO;

import org.apache.commons.lang3.tuple.Pair;
import org.example.Model.Floor;

import java.util.*;

public class FloorService implements FloorDAO {

    private final SQLQuery requester = new SQLQuery();

    private final ExecuteAround<Floor> extractor = rs -> {
        List<Floor> list = new ArrayList<>();
        while (rs.next()) {
            final Floor floor = new Floor(rs.getInt("house_id"), rs.getInt("floor"));
            floor.setFloorId(rs.getInt("floor_id"));
            list.add(floor);
        }
        return list;
    };


    @Override
    public List<Floor> getAll() {
        String query = "SELECT * FROM floor";
        return requester.executeQuery(query, null, extractor);
    }

    @Override
    public void create(Floor floor) {
        String query = "INSERT INTO floor (house_id,floor) values (?,?)";
        StatementConsumer consumer = statement -> {
            statement.setInt(1, floor.getHouseId());
            statement.setInt(2, floor.getFloor());
        };
        requester.executeUpdate(query, consumer);
    }

    @Override
    public void update(int id, List<?> params) {
        String query = "UPDATE floor SET house_id = ? WHERE  floor_id = ?";
        StatementConsumer consumer = statement -> {
            requester.updateFromList(statement, params);
            statement.setInt(params.size() + 1, id);
        };
        requester.executeUpdate(query, consumer);
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM floor WHERE floor_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, id);
        requester.executeUpdate(query, consumer);
    }

    @Override
    public Floor get(int floorId) {
        String query = "SELECT * FROM floor WHERE floor_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, floorId);
        List<Floor> floors = requester.executeQuery(query,consumer,extractor);
        return floors.size() == 1 ? floors.get(0) : null;
    }

    @Override
    public List<Floor> getByHouseId(int houseId) {
        String query = "SELECT * FROM floor WHERE house_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1,houseId);
        return requester.executeQuery(query,consumer,extractor);
    }

    @Override
    public int getNumOfFloors(int houseId) {
        String query = "SELECT COUNT(*) FROM floor WHERE house_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, houseId);
        return requester.executeCount(query, consumer);
    }

    @Override
    public int houseIdByFloorId(int floorId) {
        String query = "SELECT house_id FROM floor WHERE floor_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, floorId);
        return requester.executeCount(query, consumer);
    }

    @Override
    public int maxFloorInHouse(int houseId){
        String query = "SELECT COALESCE(MAX(floor), 0) FROM floor WHERE house_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, houseId);
        return requester.executeCount(query, consumer);
    }


    @Override
    public List<Pair<String, Integer>> floorWithLowCostFlats(int houseId) {
        List<Pair<String, Integer>> list = new ArrayList<>();
        String query = "SELECT * FROM floor ORDER BY floor DESC WHERE house_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, houseId);
        List<Floor> floors = requester.executeQuery(query, consumer, extractor);
        if (floors.size()>= 3) {
            list.add(Pair.of("Lower", floors.get(0).getFloorId()));
            list.add(Pair.of("Upper", floors.get(1).getFloorId()));
            list.add(Pair.of("Lower", floors.get(floors.size() - 1).getFloorId()));
        }
        else{
            list.add(Pair.of("Lower", floors.get(0).getFloorId()));
            list.add(Pair.of("Lower", floors.get(floors.size() - 1).getFloorId()));
        }
        return list;
    }
}
