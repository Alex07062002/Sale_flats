package org.example.Model.Flat;

public class Flat{
    private int flatId;
    private final int floorId;
    private final int numberRooms;
    private final int entrance;
    private double cost;

    private final double square;
    private OwnerType ownerType;
    private PriceType priceType;

    public Flat(int floorId, int numberRooms, int entrance, double square) {
        this.floorId = floorId;
        this.numberRooms = numberRooms;
        this.entrance = entrance;
        this.square = square;
        this.cost = 0;
        this.ownerType = OwnerType.free;
        this.priceType = PriceType.full;
    }

    public int getFloorId() {
        return floorId;
    }

    public int getNumberRooms() {
        return numberRooms;
    }

    public int getEntrance() {
        return entrance;
    }

    public double getCost() {
        return cost;
    }

    public double getSquare() {
        return square;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    public int getFlatId() {return flatId;}

    public PriceType getPriceType() {
        return priceType;
    }

    public void setFlatId(int flatId) {this.flatId = flatId;}

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }
}
