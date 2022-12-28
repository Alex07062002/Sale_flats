package org.example.DAO;

import org.example.Model.Flat.Flat;
import org.example.Model.Flat.OwnerType;
import org.example.Model.Flat.PriceType;

import java.util.*;

public class FlatService implements FlatDAO{

    private final SQLQuery requester = new SQLQuery();

    private final ExecuteAround<Flat> extractor = rs -> {
        List<Flat> list = new ArrayList<>();
        while (rs.next()) {
            final Flat flat = new Flat(rs.getInt("floor_id"),
                    rs.getInt("number_of_rooms"),rs.getInt("entrance"),
                    rs.getDouble("square"));
            flat.setFlatId(rs.getInt("flat_id"));
            flat.setCost(rs.getDouble("cost"));
            flat.setOwnerType(OwnerType.valueOf(rs.getString("type_owner_of_flat")));
            flat.setPriceType(PriceType.valueOf(rs.getString("type_price")));
            list.add(flat);
        }
        return list;
    };

    @Override
    public List<Flat> getAll() {
        String query = "SELECT * FROM flat";
        return requester.executeQuery(query,null,extractor);
    }

    @Override
    public void create(Flat flat) {
        String query = "INSERT INTO flat (floor_id,number_of_rooms,entrance,cost,square,type_owner_of_flat,type_price) values (?,?,?,?,?,?::type_owner_of_flat,?::type_price)";
        StatementConsumer consumer = statement -> {
            statement.setInt(1,flat.getFloorId());
            statement.setInt(2,flat.getNumberRooms());
            statement.setInt(3,flat.getEntrance());
            statement.setDouble(4,flat.getCost());
            statement.setDouble(5,flat.getSquare());
            statement.setString(6,flat.getOwnerType().toString());
            statement.setString(7,flat.getPriceType().toString());
        };
            requester.executeUpdate(query,consumer);
    }

    @Override
    public void update(int id, List<?> params) {
        String query = "UPDATE flat SET number_of_rooms = ?, entrance = ?, type_owner_of_flat = ?::type_owner_of_flat WHERE flat_id = ?";
        StatementConsumer consumer = statement -> {
           requester.updateFromList(statement,params);
           statement.setInt(params.size()+1,id);
        };
        requester.executeUpdate(query, consumer);
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM flat WHERE flat_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1,id);
        requester.executeUpdate(query,consumer);
    }

    @Override
    public Flat get(int flatId) {
        String query = "SELECT * FROM flat WHERE flat_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1,flatId);
        List<Flat> flats = requester.executeQuery(query,consumer,extractor);
        return flats.size()>0 ? flats.get(0) : null;
    }

    @Override
    public void updateCost(Flat flat) {
        String query = "UPDATE flat SET cost = ?, type_price = ?::type_price WHERE flat_id = ?";
        StatementConsumer consumer = statement -> {
          statement.setDouble(1,flat.getCost());
          statement.setString(2,flat.getPriceType().toString());
          statement.setInt(3,flat.getFlatId());
        };
        requester.executeUpdate(query,consumer);
    }

    @Override
    public void updateStatusFlat(Flat flat) {
        String query = "UPDATE flat SET type_owner_of_flat = ?::type_owner_of_flat WHERE flat_id = ?";
        StatementConsumer consumer = statement -> {
            statement.setString(1,flat.getOwnerType().toString());
            statement.setInt(2,flat.getFlatId());
        };
        requester.executeUpdate(query,consumer);
    }


    @Override
    public List<Flat> getByFloorId(int floor_id) {
        String query = "SELECT * FROM flat WHERE floor_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1, floor_id);
        return requester.executeQuery(query,consumer,extractor);
    }


    @Override
    public List<Flat> getByOwner(OwnerType type) {
        String query = "SELECT * FROM flat WHERE type_owner_of_flat = ?::type_owner_of_flat";
        StatementConsumer consumer = statement -> statement.setString(1,type.toString());
        return requester.executeQuery(query,consumer,extractor);
    }

    @Override
    public List<Flat> getTypePrice(PriceType type) {
        String query = "SELECT * FROM flat WHERE type_price = ?::type_price";
        StatementConsumer consumer = statement -> statement.setString(1,type.toString());
        return requester.executeQuery(query,consumer,extractor);
    }

    @Override
    public List<Flat> getByCost(double low, double high, int floorId) {
        String query = "SELECT * FROM flat WHERE cost>= ? AND cost<= ? AND  type_owner_of_flat = свободна AND floor_id = ?";
        StatementConsumer consumer = statement -> {
            statement.setDouble(1,low);
            statement.setDouble(2,high);
            statement.setInt(3,floorId);
        };
       return requester.executeQuery(query,consumer,extractor);
    }
}
