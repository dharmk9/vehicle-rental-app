package com.rental.services;

import com.rental.bo.TripDetails;
import com.rental.bo.Vehicle;
import com.rental.exceptions.ExpenseException;

public interface ExpenseCalculator {

    public Long calculateExpense(final Vehicle vehicle, final TripDetails tripDetails) throws ExpenseException;
}
