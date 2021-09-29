package com.rental.validators;

import com.rental.bo.Vehicle;

public class VehicleValidator implements Validator<Vehicle> {
    @Override
    public boolean isValid(Vehicle vehicle) {
        boolean flag = false;
        if(vehicle != null){
            if(vehicle.getVehicleType() != null || vehicle.getFuelTypes() !=null | vehicle.getPassengerCapacity()>0){
                flag = true;
            }
        }


            return flag;
    }
}
