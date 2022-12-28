package org.example.Model;


public class Floor {
    private  int floorId;

    private final int houseId;

    private final int floor;

    public int getFloor() {
        return floor;
    }
    public int getHouseId() {
        return houseId;
    }

    public int getFloorId() {return floorId;}

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public Floor(int houseId, int floor) {
        this.houseId = houseId;
        this.floor = floor;
    }
}
