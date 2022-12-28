package org.example.Model.Flat.StrategyCost;


import org.example.Model.Flat.PriceType;

public record Price(PriceType type, double multiplier) implements Strategy {

    @Override
    public double setCostAuto(double square, int price) {
        return square * price * multiplier();
    }
}
