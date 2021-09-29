package com.rental.validators;

import com.rental.bo.TripDetails;

public class TripValidator implements Validator<TripDetails> {
    @Override
    public boolean isValid(TripDetails tripDetails) {
        boolean flag = false;
        if(tripDetails!=null && tripDetails.getDistanceTraveled()>0 && tripDetails.getNoOfPassenger()>0){
            flag = true;
        }
        return flag;
    }
}
