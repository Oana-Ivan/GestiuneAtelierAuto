package Masini;

public class Camion extends Masina {
    private final static Integer INSURANCE_POLICY_BASE = 300;
    private final static Integer DIESEL_VALUE = 1000;
    private final static Integer KM_VALUE= 700;
    private final static Integer KM_LIMIT = 800000;
    private final static Double DISCOUNT = 0.15;
    private Double tonnage;

    public Camion(Double noOfKm, Integer manufactureYear, Boolean diesel, Double tonnage) {
        super(noOfKm, manufactureYear, diesel);
        this.tonnage = tonnage;
    }

    @Override
    public Double getInsurancePolicyValue() {
        Double insurancePolicyValue = Double.valueOf(getVechime() * INSURANCE_POLICY_BASE);
        if (getDiesel()) {
            insurancePolicyValue += DIESEL_VALUE;
        }
        if (getNoOfKm() > KM_LIMIT) {
            insurancePolicyValue += KM_VALUE;
        }
        return insurancePolicyValue;
    }

    @Override
    public Double getInsurancePolicyValueDiscount() {
        return getInsurancePolicyValue() * (1 - DISCOUNT);
    }

    public Double getTonnage() {
        return tonnage;
    }

    public void setTonnage(Double tonnage) {
        this.tonnage = tonnage;
    }
}
