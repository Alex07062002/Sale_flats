package org.example.DAO;

import org.example.Model.Flat.PriceType;
import org.example.Model.Flat.StrategyCost.Price;

import java.util.List;

public interface PriceDAO{
    Price get(PriceType type);

    void update(PriceType type, List<?> params);

    List<Price> getAll();

    void create(Price price);

    void delete(PriceType type);
}
