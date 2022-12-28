package org.example.DAO;

import org.example.Model.Flat.StrategyCost.PriceForM2;

import java.util.ArrayList;
import java.util.List;

public class CostM2Service implements CostM2DAO{

    SQLQuery requester = new SQLQuery();

    private final ExecuteAround<PriceForM2> extractor = rs -> {
        List<PriceForM2> list = new ArrayList<>();
        while (rs.next()) {
           final PriceForM2 price = new PriceForM2(rs.getInt("count_of_rooms"), rs.getInt("cost"));
            list.add(price);
        }
        return list;
    };

    @Override
    public PriceForM2 get(int countOfRooms) {
        String query = "SELECT * FROM cost_for_m2 WHERE count_of_rooms = ?";
        StatementConsumer consumer = statement -> statement.setInt(1,countOfRooms);
        List<PriceForM2> list = requester.executeQuery(query,consumer,extractor);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public List<PriceForM2> getAll() {
        String query = "SELECT * FROM cost_for_m2";
        return requester.executeQuery(query,null, extractor);
    }

    @Override
    public void create(PriceForM2 priceForM2) {
        String query = "INSERT INTO contract (count_of_rooms, cost) values (?,?)";
        StatementConsumer consumer = statement -> {
            statement.setInt(1, priceForM2.numberOfRooms());
            statement.setInt(2, priceForM2.price());
        };
        requester.executeUpdate(query,consumer);
    }

    @Override
    public void update(int countOfRooms, List<?> params) {
        String query = "UPDATE cost_for_m2 SET cost = ? WHERE count_of_rooms = ?";
        StatementConsumer consumer = statement -> {
            requester.updateFromList(statement,params);
            statement.setInt(params.size()+1,countOfRooms);
        };
        requester.executeUpdate(query,consumer);
    }

    @Override
    public void delete(int countOfRooms) {
        String query = "DELETE * FROM cost_for_m2 WHERE count_of_rooms = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, countOfRooms);
        requester.executeUpdate(query,consumer);
    }
    @Override
    public int countRows(){
        String query = "SELECT COUNT(*) FROM cost_for_m2";
        return requester.executeCount(query,null);
    }
    @Override
    public int maxRooms(){
        String query = "SELECT COALESCE(MAX(count_of_rooms),0) FROM cost_for_m2";
        return requester.executeCount(query,null);
    }
}
