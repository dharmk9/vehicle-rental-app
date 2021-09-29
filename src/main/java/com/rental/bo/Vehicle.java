package com.rental.bo;

import com.rental.enums.FuelTypes;

public class Vehicle {

    private String vehicleType;
    private FuelTypes fuelTypes;
    private Boolean isACVehicle;
    private Integer passengerCapacity;

    public Vehicle(String vehicleType, FuelTypes fuelTypes, Boolean isACVehicle, Integer passengerCapacity) {
        this.vehicleType = vehicleType;
        this.fuelTypes = fuelTypes;
        this.isACVehicle = isACVehicle;
        this.passengerCapacity = passengerCapacity;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public FuelTypes getFuelTypes() {
        return fuelTypes;
    }

    public Boolean isACVehicle() {
        return isACVehicle;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleType='" + vehicleType + '\'' +
                ", fuelTypes=" + fuelTypes +
                ", isACVehicle=" + isACVehicle +
                ", passengerCapacity=" + passengerCapacity +
                '}';
    }
}
