package org.example.DAO;

import org.example.Model.Flat.StrategyCost.PriceForM2;

public interface CostM2DAO extends DAO<PriceForM2>{

    PriceForM2 get(int countOfRooms);
    int countRows();
    int maxRooms();
}
