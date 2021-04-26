package Masini;

public class Autobuz extends Masina {
    private final static Integer INSURANCE_POLICY_BASE = 200;
    private final static Integer DIESEL_VALUE = 1000;
    private final static Integer KM_VALUE_MIN = 500;
    private final static Integer KM_VALUE_MAX = 1000;
    private final static Integer KM_LIMIT_MIN = 100000;
    private final static Integer KM_LIMIT_MAX = 200000;
    private final static Double DISCOUNT = 0.1;
    private Integer noOfSeats;

    public Autobuz(Double noOfKm, Integer manufactureYear, Boolean diesel, Integer noOfSeats) {
        super(noOfKm, manufactureYear, diesel);
        this.noOfSeats = noOfSeats;
    }

    @Override
    public Double getInsurancePolicyValue() {
        Double insurancePolicyValue = Double.valueOf(getVechime() * INSURANCE_POLICY_BASE);
        if (getDiesel()) {
            insurancePolicyValue += DIESEL_VALUE;
        }
        if (getNoOfKm() > KM_LIMIT_MAX){
            insurancePolicyValue += KM_VALUE_MAX;
        }
        else if (getNoOfKm() > KM_LIMIT_MIN){
            insurancePolicyValue += KM_VALUE_MIN;
        }
        return insurancePolicyValue;
    }

    @Override
    public Double getInsurancePolicyValueDiscount() {
        return getInsurancePolicyValue() * (1 - DISCOUNT);
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }
}
