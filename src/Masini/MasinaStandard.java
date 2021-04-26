package Masini;

public class MasinaStandard extends Masina{
    public final static String MANUAL_MODE = "Manual";
    public final static String AUTOMAT_MODE = "Automat";
    private final static Integer INSURANCE_POLICY_BASE = 100;
    private final static Integer DIESEL_VALUE = 500;
    private final static Integer KM_VALUE = 500;
    private final static Integer KM_LIMIT = 200000;
    private final static Double DISCOUNT = 0.05;
    private String transmissionMode;

    public MasinaStandard(Double noOfKm, Integer manufactureYear, Boolean diesel, String transmissionMode) {
        super(noOfKm, manufactureYear, diesel);
        this.transmissionMode = transmissionMode;
    }

    @Override
    public Double getInsurancePolicyValue() {
        Double insurancePolicyValue = Double.valueOf(getVechime() * INSURANCE_POLICY_BASE);
        if (getDiesel()) {
            insurancePolicyValue += DIESEL_VALUE;
        }
        if (getNoOfKm() > KM_LIMIT){
            insurancePolicyValue += KM_VALUE;
        }
        return insurancePolicyValue;
    }

    @Override
    public Double getInsurancePolicyValueDiscount() {
        return getInsurancePolicyValue() * (1 - DISCOUNT);
    }

    public String getTransmissionMode() {
        return transmissionMode;
    }

    public void setTransmissionMode(String transmissionMode) {
        this.transmissionMode = transmissionMode;
    }
}
