package Atelier;

import Angajati.Angajat;
import Masini.Masina;

public class WorksOn {
    private Angajat angajat;
    private Masina masina;
    private Integer timeEstimateHours;

    public WorksOn(Angajat angajat, Masina masina, Integer timeEstimateHours) {
        this.angajat = angajat;
        this.masina = masina;
        this.timeEstimateHours = timeEstimateHours;
    }

    @Override
    public String toString() {
        return "ID angajat: " + angajat.getId() + " (" + angajat.toString() + "). ID masina: " + masina.getID() + ". Timp estimat: " + timeEstimateHours;
    }

    public Integer getAngajatID() {
        return angajat.getId();
    }

    public Masina getMasina() {
        return masina;
    }

    public Angajat getAngajat() {
        return angajat;
    }

    public Integer getTimeEstimateHours() {
        return timeEstimateHours;
    }

    public void setAngajat(Angajat angajat) {
        this.angajat = angajat;
    }

    public void setMasina(Masina masina) {
        this.masina = masina;
    }

    public void setTimeEstimateHours(Integer timeEstimateHours) {
        this.timeEstimateHours = timeEstimateHours;
    }
}
