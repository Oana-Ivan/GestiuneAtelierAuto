package Atelier;

import Angajati.Angajat;
import Angajati.AngajatiService;

public class Atelier {
    private static Atelier instance;
    private AngajatiService angajatiService;
    private WorksOnService worksOnService;

    private Atelier() {
        angajatiService = AngajatiService.getInstance();
        worksOnService = WorksOnService.getInstance();
    }

    public static Atelier getInstance() {
        if (instance == null) {
            instance = new Atelier();
        }
        return instance;
    }

    // Methods for Atelier

    public Boolean isOpen() {
        if (angajatiService.getAngajati().size() != 0) {
            return true;
        }
        return false;
    }

    // Methods for Masini

    public void showRepairSchedules() {
        worksOnService.showRepairSchedules();
    }

    public void showWaitingClients() {
        worksOnService.showWaitingClients();
    }

    public void addMasinaByAngajatID() {
        worksOnService.addMasinaByAngajatID();
    }

    public void addMasinaToAnyAngajat() {
        worksOnService.addMasinaToAnyAngajat();
    }

    public void finishRepairMasina() {
        worksOnService.finishMasinaRepair();
    }

    public void removeFromWaitingClients(Integer masinaId) {
        worksOnService.removeFromWaitingClients(masinaId);
    }

    // Methods for Angajati

    public void getAngajati() {
        angajatiService.getAngajati();
    }

    public void showAngajati() {
        angajatiService.showAngajati();
    }

    public void addAngajat(Angajat angajat) {
        angajatiService.addAngajat(angajat);
    }

    public Boolean addAngajat() {
        return angajatiService.addAngajat();
    }

    public Boolean removeAngajatById(int id) {
        return angajatiService.removeAngajatById(id);
    }

    public Boolean editAngajatById(int id) {
        return angajatiService.editAngajatById(id);
    }

    public double calculSalariu(int id) {
        return angajatiService.calculSalariu(id);
    }

    public void showAngajatIncarcareMaxima() {
        worksOnService.showAngajatIncarcareMaxima();
    }

    public void celMaiSolicitatAngajat() {
        worksOnService.celMaiSolicitatAngajat();
    }

    public void angajatMaxPolite() {
        worksOnService.angajatiMaxPolite();
    }

    public void angajatiAutobuzeNoi() {
        worksOnService.angajatiAutobuzeNoi();
    }

    public void showBacsisAngajati() {
        worksOnService.showBacsisAngajati();
    }

}
