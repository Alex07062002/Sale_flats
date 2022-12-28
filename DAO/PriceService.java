package org.example.DAO;

import org.example.Model.Flat.PriceType;
import org.example.Model.Flat.StrategyCost.Price;

import java.util.ArrayList;
import java.util.List;

public class PriceService implements PriceDAO{

    SQLQuery requester = new SQLQuery();

    private final ExecuteAround<Price> extractor = rs -> {
        List<Price> list = new ArrayList<>();
        while (rs.next()) {
            final Price price = new Price(PriceType.valueOf(rs.getString("type")),rs.getDouble("multiplier"));
            list.add(price);
        }
        return list;
    };

    @Override
    public List<Price> getAll() {
        String query = "SELECT * FROM multiplier_for_cost";
        return requester.executeQuery(query,null,extractor);
    }

    @Override
    public void create(Price price) {
        String query = "INSERT INTO multiplier_for_cost (type,multiplier) values (?::type_price,?)";
        StatementConsumer consumer = statement -> {
            statement.setString(1,price.type().toString());
            statement.setDouble(2,price.multiplier());
        };
        requester.executeUpdate(query,consumer);
    }

    @Override
    public void delete(PriceType type) {
        String query = "DELETE FROM multiplier_for_cost WHERE type = ?::type_price";
        StatementConsumer consumer = statement -> statement.setString(1,type.toString());
        requester.executeUpdate(query,consumer);
    }

    @Override
    public void update(PriceType type, List<?> params) {
        String query = "UPDATE multiplier_for_cost SET multiplier = ? WHERE  type = ?::type_price";
        StatementConsumer consumer = statement -> {
            requester.updateFromList(statement,params);
            statement.setString(params.size()+1,type.toString());
        };
        requester.executeUpdate(query,consumer);
    }

    @Override
    public Price get(PriceType type) {
        String query = "SELECT * FROM multiplier_for_cost WHERE type = ?::type_price";
        StatementConsumer consumer = statement -> statement.setString(1,type.toString());
        List<Price> prices = requester.executeQuery(query,consumer,extractor);
        return prices.size() == 1 ? prices.get(0) : null;
    }

    public int countRows(){
        String query = "SELECT COUNT(*) FROM multiplier_for_cost";
        return requester.executeCount(query,null);
    }

}
