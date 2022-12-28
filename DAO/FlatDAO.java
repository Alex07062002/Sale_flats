package org.example.DAO;

import org.example.Model.Flat.Flat;
import org.example.Model.Flat.OwnerType;
import org.example.Model.Flat.PriceType;
import org.example.Model.Flat.StrategyCost.PriceForM2;

import java.sql.SQLException;
import java.util.List;

public interface FlatDAO extends DAO<Flat> {

    Flat get(int flatId);
    void updateCost(Flat flat);

    void updateStatusFlat(Flat flat);

    List<Flat> getByFloorId(int floor_id);

    List<Flat> getByOwner(OwnerType type);

    List<Flat> getTypePrice(PriceType type);

    List<Flat> getByCost(double low, double high, int floorId);
}
