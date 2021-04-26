package Atelier;

import Angajati.Angajat;
import Masini.Masina;

public class WaitingClient {
    private Masina masina;
    private Angajat wantedAngajat;
    private Integer timeEstimateHours;

    public WaitingClient(Masina masina, Angajat wantedAngajat, Integer timeEstimateHours) {
        this.masina = masina;
        this.wantedAngajat = wantedAngajat;
        this.timeEstimateHours = timeEstimateHours;
    }

    @Override
    public String toString() {
        return "ID masina: " + masina.getID() + ". Estimare timp: " + timeEstimateHours +
                ". Angajat: " + ((wantedAngajat != null) ? wantedAngajat.getId() : "oricare");
    }

    public Masina getMasina() {
        return masina;
    }

    public Angajat getWantedAngajat() {
        return wantedAngajat;
    }

    public Integer getTimeEstimateHours() {
        return timeEstimateHours;
    }

    public void setMasina(Masina masina) {
        this.masina = masina;
    }

    public void setWantedAngajat(Angajat wantedAngajat) {
        this.wantedAngajat = wantedAngajat;
    }

    public void setTimeEstimateHours(Integer timeEstimateHours) {
        this.timeEstimateHours = timeEstimateHours;
    }
}
