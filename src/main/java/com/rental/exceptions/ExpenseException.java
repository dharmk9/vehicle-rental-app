package com.rental.exceptions;

public class ExpenseException extends Exception {

    public ExpenseException(){
        super("Invalid Expense Exception");
    }
    public ExpenseException(String message){
        super(message);
    }
}
