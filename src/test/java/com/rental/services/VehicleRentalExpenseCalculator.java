package com.rental.services;


import com.rental.bo.TripDetails;
import com.rental.bo.Vehicle;
import com.rental.enums.FuelTypes;
import com.rental.exceptions.ExpenseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehicleRentalExpenseCalculatorTest {

   private ExpenseCalculator expenseCalculator = new VehicleRentalExpenseCalculator();

    @Test
    @DisplayName("Invalid Vehicle Test")
    public void test_invalidVehicle_WithError(){

        assertThrows(ExpenseException.class, () -> {expenseCalculator.calculateExpense(null, null);});
    }
    @Test
    @DisplayName("Invalid TripDetails Test")
    public void test_invalidTrip_WithError(){
        Vehicle car = new Vehicle("CAR", FuelTypes.PETROL, false, 5);
        assertThrows(ExpenseException.class, () -> {expenseCalculator.calculateExpense(car, null);});
    }
    @Test
    @DisplayName("Invalid TripDetails Passengers Test")
    public void test_invalidTripPassengers_WithError(){
        Vehicle car = new Vehicle("CAR", FuelTypes.PETROL, false, 5);
        TripDetails tripDetails = new TripDetails(0, 0L);
        assertThrows(ExpenseException.class, () -> {expenseCalculator.calculateExpense(car, tripDetails);});
    }
    @Test
    @DisplayName("Invalid TripDetails Distance Test")
    public void test_invalidTripDistance_WithError(){
        Vehicle car = new Vehicle("CAR", FuelTypes.PETROL, false, 5);
        TripDetails tripDetails = new TripDetails(5, 0L);
        assertThrows(ExpenseException.class, () -> {expenseCalculator.calculateExpense(car, tripDetails);});
    }
    @Test
    @DisplayName("Petrol NON_AC Car Test")
    public void test_PetrolCar() throws ExpenseException {
        Vehicle car = new Vehicle("CAR", FuelTypes.PETROL, false, 5);
        TripDetails tripDetails = new TripDetails(4, 100L);
        Long expenses = expenseCalculator.calculateExpense(car, tripDetails);
        assertThat(expenses, is(1500L));
    }
    @Test
    @DisplayName("Diesel NON_AC Car Test")
    public void test_DieselCar() throws ExpenseException {
        Vehicle car = new Vehicle("CAR", FuelTypes.DIESEL, false, 5);
        TripDetails tripDetails = new TripDetails(4, 100L);
        Long expenses = expenseCalculator.calculateExpense(car, tripDetails);
        assertThat(expenses, is(1400L));
    }
    @Test
    @DisplayName("Petrol AC Car Test")
    public void test_PetrolACCar() throws ExpenseException {
        Vehicle car = new Vehicle("CAR", FuelTypes.PETROL, true, 5);
        TripDetails tripDetails = new TripDetails(4, 100L);
        Long expenses = expenseCalculator.calculateExpense(car, tripDetails);
        assertThat(expenses, is(1700L));
    }
    @Test
    @DisplayName("Diesel AC Car Test")
    public void test_DieselACCar() throws ExpenseException {
        Vehicle car = new Vehicle("CAR", FuelTypes.DIESEL, true, 5);
        TripDetails tripDetails = new TripDetails(4, 100L);
        Long expenses = expenseCalculator.calculateExpense(car, tripDetails);
        assertThat(expenses, is(1600L));
    }
    @Test
    @DisplayName("Petrol NON_AC Bus Test")
    public void test_PetrolNonACBus() throws ExpenseException {
        Vehicle car = new Vehicle("BUS", FuelTypes.PETROL, false, 30);
        TripDetails tripDetails = new TripDetails(24, 100L);
        Long expenses = expenseCalculator.calculateExpense(car, tripDetails);
        assertThat(expenses, is(1470L));
    }
    @Test
    @DisplayName("Petrol AC Bus Test")
    public void test_PetrolACBus() throws ExpenseException {
        Vehicle car = new Vehicle("BUS", FuelTypes.PETROL, true, 30);
        TripDetails tripDetails = new TripDetails(24, 100L);
        Long expenses = expenseCalculator.calculateExpense(car, tripDetails);
        assertThat(expenses, is(1670L));
    }
    @Test
    @DisplayName("Diesel NON_AC Bus Test")
    public void test_DieselNonACBus() throws ExpenseException {
        Vehicle car = new Vehicle("BUS", FuelTypes.DIESEL, false, 30);
        TripDetails tripDetails = new TripDetails(24, 100L);
        Long expenses = expenseCalculator.calculateExpense(car, tripDetails);
        assertThat(expenses, is(1370L));
    }
    @Test
    @DisplayName("Diesel AC Bus Test")
    public void test_DieselACBus() throws ExpenseException {
        Vehicle car = new Vehicle("BUS", FuelTypes.DIESEL, true, 30);
        TripDetails tripDetails = new TripDetails(24, 100L);
        Long expenses = expenseCalculator.calculateExpense(car, tripDetails);
        assertThat(expenses, is(1570L));
    }
    @Test
    @DisplayName("Diesel AC Bus With Additional Passenger Test")
    public void test_DieselACBusAdditionalPassengers() throws ExpenseException {
        Vehicle car = new Vehicle("CAR", FuelTypes.DIESEL, true, 30);
        TripDetails tripDetails = new TripDetails(40, 100L);
        Long expenses = expenseCalculator.calculateExpense(car, tripDetails);
        assertThat(expenses, is(5600L));
    }
}
