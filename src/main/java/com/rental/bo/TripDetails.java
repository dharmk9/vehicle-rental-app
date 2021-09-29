package com.rental.bo;

public class TripDetails {

    private Integer noOfPassenger;
    private Long distanceTraveled;

    public TripDetails(Integer noOfPassenger, Long distanceTraveled) {
        this.noOfPassenger = noOfPassenger;
        this.distanceTraveled = distanceTraveled;
    }

    public Integer getNoOfPassenger() {
        return noOfPassenger;
    }

    public Long getDistanceTraveled() {
        return distanceTraveled;
    }

    @Override
    public String toString() {
        return "TripDetails{" +
                "noOfPassenger=" + noOfPassenger +
                ", distanceTraveled=" + distanceTraveled +
                '}';
    }
}
