package com.rental.services;

import com.rental.bo.TripDetails;
import com.rental.bo.Vehicle;
import com.rental.validators.TripValidator;
import com.rental.validators.Validator;
import com.rental.validators.VehicleValidator;
import com.rental.configs.Constants;
import com.rental.enums.FuelTypes;
import com.rental.exceptions.ExpenseException;

import java.util.logging.Logger;

public class VehicleRentalExpenseCalculator implements ExpenseCalculator {
    private Logger log = Logger.getLogger(VehicleRentalExpenseCalculator.class.getName());

    private Validator vehicleValidator = null;
    private Validator tripValidator = null;

    public VehicleRentalExpenseCalculator() {
        vehicleValidator = new VehicleValidator();
        tripValidator = new TripValidator();
    }

    @Override
    public Long calculateExpense(final Vehicle vehicle, final TripDetails tripDetails) throws ExpenseException {

        if(!vehicleValidator.isValid(vehicle)) throw new ExpenseException("Invalid Vehicle Details");
        if(!tripValidator.isValid(tripDetails)) throw new ExpenseException("Invalid Trip Details");

        log.info("Valid input : "+vehicle.toString() + " & "+tripDetails.toString());

        Long travelExpenseCost = calculateStandardExpense(tripDetails.getDistanceTraveled());

        if(FuelTypes.DIESEL.equals(vehicle.getFuelTypes())){
            travelExpenseCost -= calculateDieselCompensation(tripDetails.getDistanceTraveled());
        }
        if(vehicle.isACVehicle()){
            travelExpenseCost += calculateACCharges(tripDetails.getDistanceTraveled());
        }
        if("BUS".equalsIgnoreCase(vehicle.getVehicleType())){
            travelExpenseCost -= calculateDiscountOnBus(tripDetails.getDistanceTraveled());
        }
        if(tripDetails.getNoOfPassenger()>vehicle.getPassengerCapacity()){
            log.info("Additional Passenger travelled: Passenger traveled ["+tripDetails.getNoOfPassenger()+"] Vehicle Capacity ["+vehicle.getPassengerCapacity()+"]");
            travelExpenseCost += calculateAdditionalPassengerCharges(tripDetails);
        }

        return travelExpenseCost;
    }

    protected Long calculateStandardExpense(final Long distance){

        return Constants.PETROL_VECHILE_CHARGES * distance;
    }
    protected Long calculateACCharges(final Long distance){

        return Constants.AC_CHARGES * distance;
    }
    protected Long calculateDieselCompensation(final Long distance){

        return Constants.DIESEL_COMPENSATION * distance;
    }
    protected Long calculateDiscountOnBus(final Long distance){

        return (long)(((Constants.DISCOUNT_ON_BUS * Constants.PETROL_VECHILE_CHARGES)/100.0) * distance);
    }
    protected Long calculateAdditionalPassengerCharges(TripDetails tripDetails){
        log.info(""+Constants.ADDITIONAL_PASSENGER_CHARGES * tripDetails.getNoOfPassenger() * tripDetails.getDistanceTraveled());

        return Constants.ADDITIONAL_PASSENGER_CHARGES * tripDetails.getNoOfPassenger() * tripDetails.getDistanceTraveled();
    }
}
