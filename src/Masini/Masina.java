package Masini;

import java.time.LocalDate;

public abstract class Masina {
    private static Integer noOfMasini = 0;
    private Integer ID;
    private Double noOfKm;
    private Integer manufactureYear;
    private Boolean Diesel;

    public Masina(Double noOfKm, Integer manufactureYear, Boolean diesel) {
        noOfMasini++;
        this.ID = noOfMasini;
        this.noOfKm = noOfKm;
        this.manufactureYear = manufactureYear;
        this.Diesel = diesel;
    }

    public abstract Double getInsurancePolicyValue();

    public abstract Double getInsurancePolicyValueDiscount();

    public Integer getVechime() {
        return (LocalDate.now().getYear() - manufactureYear);
    }

    public Integer getID() {
        return ID;
    }

    public Double getNoOfKm() {
        return noOfKm;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public Boolean getDiesel() {
        return Diesel;
    }

    public void setNoOfKm(Double noOfKm) {
        this.noOfKm = noOfKm;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public void setDiesel(Boolean diesel) {
        Diesel = diesel;
    }
}
