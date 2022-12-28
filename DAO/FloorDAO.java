package org.example.DAO;

import org.apache.commons.lang3.tuple.Pair;
import org.example.Model.Floor;

import java.beans.Introspector;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FloorDAO extends DAO<Floor>{

    Floor get(int floorId);
    List<Floor> getByHouseId(int houseId);
    int getNumOfFloors (int houseId);

    int houseIdByFloorId (int floorId);

    int maxFloorInHouse(int houseId);

    List<Pair<String,Integer>> floorWithLowCostFlats(int houseId);

}
