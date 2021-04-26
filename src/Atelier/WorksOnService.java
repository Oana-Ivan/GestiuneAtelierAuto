package Atelier;

import Angajati.AngajatiService;
import Masini.Autobuz;
import Masini.Camion;
import Masini.Masina;
import Masini.MasinaStandard;

import java.util.*;

public class WorksOnService {
    private static WorksOnService instance;
    private AngajatiService angajatiService;
    Scanner input;

    private List<WorksOn> repairSchedules;
    private List<WaitingClient> waitingClients;

    private WorksOnService() {
        repairSchedules = new ArrayList<WorksOn>();
        waitingClients = new ArrayList<WaitingClient>();

        angajatiService = AngajatiService.getInstance();
        input = new Scanner(System.in);
    }

    public static WorksOnService getInstance() {
        if (instance == null) {
            instance = new WorksOnService();
        }
        return instance;
    }

    // Getters

    public List<WorksOn> getRepairSchedules() {
        return repairSchedules;
    }

    public List<WaitingClient> getWaitingClients() {
        return waitingClients;
    }

    // Show activity

    public void showRepairSchedules() {
        if (repairSchedules.size() == 0) {
            System.out.println("Nu se efectueaza nicio reparatie in acest moment.");
            return;
        }

        for (int i = 0; i < repairSchedules.size(); i++) {
            System.out.println((i + 1) + ". " + repairSchedules.get(i).toString());
        }
    }

    public void showWaitingClients() {
        if (waitingClients.size() == 0) {
            System.out.println("Nu exista masini in coada de asteptare.");
            return;
        }

        for (int i = 0; i < waitingClients.size(); i++) {
            System.out.println((i + 1) + ". " + waitingClients.get(i).toString());
        }
    }

    public void showAngajatIncarcareMaxima() {
        Integer max = 0;
        Integer angajatID = 0;
        for (int i = 0; i < angajatiService.getAngajati().size(); i++) {
            Integer angID = angajatiService.getAngajati().get(i).getId();
            Integer currentA = countReparatiiByAngajatID(angID);
            if (currentA > max) {
                max = currentA;
                angajatID = angID;
            }
        }
        if (angajatID != 0) {
            System.out.println("Angajatul cu id-ul " + angajatID + " efectueaza in prezent cele mai multe reparatii(" + max + ").");
        }
        else {
            System.out.println("Nu se efectueaza reparatii in acest moment.");
        }
    }

    public void celMaiSolicitatAngajat() {
        Integer max = 0;
        Integer angajatID = 0;
        for (int i = 0; i < angajatiService.getAngajati().size(); i++) {
            Integer angID = angajatiService.getAngajati().get(i).getId();
            Integer nrSolicitari = angajatiService.getAngajati().get(i).getNrSolicitari();
            if (nrSolicitari > max) {
                max = nrSolicitari;
                angajatID = angID;
            }
        }
        if (angajatID != 0) {
            System.out.println("Angajatul cu id-ul " + angajatID + " a avut cele mai multe solicitari pana in prezent (" + max + ").");
        }
        else {
            System.out.println("Nu s-au efectuat solicitari pana in prezent.");
        }
    }

    public void angajatiMaxPolite() {
        TreeMap<Double, Integer> maxs = new TreeMap<Double, Integer>();
        for (int i = 0; i < angajatiService.getAngajati().size(); i++) {
            Integer angID = angajatiService.getAngajati().get(i).getId();
            Double valPolite = 0.0;
            for (int j = 0; j < repairSchedules.size(); j++) {
                WorksOn worksOn = repairSchedules.get(j);
                if (worksOn.getAngajatID() == angID) {
                    valPolite += worksOn.getMasina().getInsurancePolicyValue();
                }
            }
            maxs.put(valPolite, angID);
        }

        Integer i = 1, n = 3;
        if (!maxs.isEmpty()) {
            System.out.println("Top 3 angajati dupa valoarea politei de asigurare: ");
            for (Map.Entry mapElement : maxs.descendingMap().entrySet()) {
                if (i <= n) {
                    Double max = (Double) mapElement.getKey();
                    Integer angajatID = (Integer) mapElement.getValue();
                    System.out.println("Locul " + i + ". Angajatul cu id-ul " + angajatID + " (" + max + ").");
                    i++;
                }
            }
        }
        else {
            System.out.println("Nu se efectueaza reparatii in prezent.");
        }
    }

    public void angajatiAutobuzeNoi() {
        TreeMap<Integer, Integer> maxs = new TreeMap<Integer, Integer>();
        for (int i = 0; i < angajatiService.getAngajati().size(); i++) {
            Integer angID = angajatiService.getAngajati().get(i).getId();
            Integer countAutobuzeNoi = 0;
            for (int j = 0; j < repairSchedules.size(); j++) {
                WorksOn worksOn = repairSchedules.get(j);
                if ((worksOn.getAngajatID() == angID) && (worksOn.getMasina() instanceof Autobuz) && (worksOn.getMasina().getVechime() < 5)) {
                    countAutobuzeNoi++;
                }
            }
            maxs.put(countAutobuzeNoi, angID);
        }


        Integer i = 1, n = 3;
        if (!maxs.isEmpty()) {
            System.out.println("Top 3 angajati dupa valoarea politei de asigurare: ");
            for (Map.Entry mapElement : maxs.descendingMap().entrySet()) {
                if (i <= n) {
                    Integer max = (Integer) mapElement.getKey();
                    Integer angajatID = (Integer) mapElement.getValue();
                    System.out.println("Locul " + i + ". Angajatul cu id-ul " + angajatID + " (" + max + " autobuze).");
                    i++;
                }
            }
        }
        else {
            System.out.println("Nu se efectueaza reparatii pentru autobuze.");
        }
    }

    public void showBacsisAngajati() {
        for (int i = 0; i < angajatiService.getAngajati().size(); i++) {
            Integer angID = angajatiService.getAngajati().get(i).getId();
            Double bacsis = 0.0;
            for (int j = 0; j < repairSchedules.size(); j++) {
                WorksOn worksOn = repairSchedules.get(j);
                if (worksOn.getAngajatID() == angID) {
                    bacsis += (worksOn.getMasina().getInsurancePolicyValueDiscount() * 0.01);
                }
            }
            System.out.println((i + 1) + ". Bacsis angajat " + angID + ": " + bacsis);
        }
    }

    // Assign cars to repair to employees

    public void addMasinaByAngajatID() {
        Integer id;
        String enter;
        System.out.println("Introduceti id-ul angajatului dorit");
        id = input.nextInt();
        enter = input.nextLine();
        angajatiService.getAngajatById(id).incrementNrSolicitari();

        Integer masinaType;
        System.out.println("Introduceti tipul masinii (0 - masina standard, 1 - autobuz, 2 - camion)");
        masinaType = input.nextInt();
        enter = input.nextLine();

        if ((masinaType == 0) && !verifyAvailabilityStdByAngID(id)) {
            System.out.println("Angajatul o sa fie disponibil dupa " + getEstimateByAngajatID(id) + " ore");
            System.out.println("Adaugati masina in coada de asteptare pentru angajatul cu id-ul " + id + "? (y/n)");
            String answer = input.nextLine();
            if(answer.toLowerCase().equals("y")){
                addMasinaToWaitingClients(masinaType, id);
            }
            return;
        }
        else if ((masinaType != 0) && !verifyAvailabilityOthersByAngID(id)) {
            System.out.println("Angajatul o sa fie disponibil dupa " + getEstimateByAngajatID(id) + " ore");
            System.out.println("Adaugati masina in coada de asteptare pentru angajatul cu id-ul " + id + "? (y/n)");
            String answer = input.nextLine();
            if(answer.toLowerCase().equals("y")){
                addMasinaToWaitingClients(masinaType, id);
            }
            return;
        }
        System.out.println("Angajatul este disponibil sa repare " + ((masinaType == 0)? "o masina standard": ((masinaType == 1)? "un autobuz":"un camion")));

        addMasinaToRepair(masinaType, id);
    }

    public void addMasinaToAnyAngajat() {
        Integer masinaType;
        System.out.println("Introduceti tipul masinii (0 - masina standard, 1 - autobuz, 2 - camion)");
        masinaType = input.nextInt();
        String enter = input.nextLine();

        Integer angajatId = 0;
        if (masinaType == 0){
            for (int i = 0; i < angajatiService.getAngajati().size(); i++) {
                Integer currentAngajatID = angajatiService.getAngajati().get(i).getId();
                if (verifyAvailabilityStdByAngID(currentAngajatID)) {
                    angajatId = currentAngajatID;
                    break;
                }
            }
        }
        else {
            for (int i = 0; i < angajatiService.getAngajati().size(); i++) {
                Integer currentAngajatID = angajatiService.getAngajati().get(i).getId();
                if (verifyAvailabilityOthersByAngID(currentAngajatID)) {
                    angajatId = currentAngajatID;
                    break;
                }
            }
        }

        if (angajatId == 0) {
            System.out.println("Niciun angajat disponibil. Adaugati masina in coada de asteptare? (y/n)");
            String answer = input.nextLine();
            if(answer.toLowerCase().equals("y")){
                addMasinaToWaitingClients(masinaType, 0);
            }
        }
        else {
            addMasinaToRepair(masinaType, angajatId);
        }

    }

    public void finishMasinaRepair() {
        System.out.println("Introduceti id-ul masinii");
        Integer idMasina = input.nextInt();
        String enter = input.nextLine();

        WorksOn lookupWorksOn = worksOnFindByIdMasina(idMasina);
        if (lookupWorksOn == null) {
            System.out.println("Nu se repara nicio masina cu id-ul " + idMasina + ".");
            return;
        }

        repairSchedules.remove(lookupWorksOn);
        Integer angajatID = lookupWorksOn.getAngajatID();
        System.out.println("Masina cu id-ul " + idMasina + " a fost marcata ca reparata de catre angajatul cu id-ul " + angajatID + ".");

        WaitingClient waitingForAngajat = getWaitingCarByAngajatID(angajatID);
        if (waitingForAngajat != null) {
            if (waitingForAngajat.getMasina() instanceof MasinaStandard &&
                            verifyAvailabilityStdByAngID(angajatID)) {
                waitingClients.remove(waitingForAngajat);
                repairSchedules.add(new WorksOn(waitingForAngajat.getWantedAngajat(),
                                waitingForAngajat.getMasina(), waitingForAngajat.getTimeEstimateHours()));
                System.out.println("Angajatul " + angajatID + " a preluat masina cu id-ul " + waitingForAngajat.getMasina().getID());
            }
            else if (!(waitingForAngajat.getMasina() instanceof MasinaStandard) &&
                            verifyAvailabilityOthersByAngID(angajatID)) {
                waitingClients.remove(waitingForAngajat);
                repairSchedules.add(new WorksOn(waitingForAngajat.getWantedAngajat(),
                        waitingForAngajat.getMasina(), waitingForAngajat.getTimeEstimateHours()));
                System.out.println("Angajatul " + angajatID + " a preluat masina cu id-ul " + waitingForAngajat.getMasina().getID());
            }
        }
        else {
            WaitingClient waitingClient = getWaitingClient();
            if (waitingClient != null) {
                if (waitingClient.getMasina() instanceof MasinaStandard &&
                        verifyAvailabilityStdByAngID(angajatID)) {
                    waitingClients.remove(waitingClient);
                    repairSchedules.add(new WorksOn(angajatiService.getAngajatById(angajatID),
                            waitingClient.getMasina(), waitingClient.getTimeEstimateHours()));
                    System.out.println("Angajatul " + angajatID + " a preluat masina cu id-ul " + waitingClient.getMasina().getID());
                }
                else if (!(waitingClient.getMasina() instanceof MasinaStandard) &&
                        verifyAvailabilityOthersByAngID(angajatID)) {
                    waitingClients.remove(waitingClient);
                    repairSchedules.add(new WorksOn(angajatiService.getAngajatById(angajatID),
                            waitingClient.getMasina(), waitingClient.getTimeEstimateHours()));
                    System.out.println("Angajatul " + angajatID + " a preluat masina cu id-ul " + waitingClient.getMasina().getID());
                }
            }
            else {
                System.out.println("Angajatul " + angajatID + " nu poate prelua o alta masina din coada de asteptare");
            }
        }
    }

    // Waiting List

    public void addMasinaToWaitingClients(Integer masinaType, Integer idAngajat) {
        Masina masina = initializeMasina(masinaType);
        System.out.println("Timp estimat: ");

        Integer estimateTime = input.nextInt();
        String enter = input.nextLine();

        waitingClients.add(new WaitingClient(masina, (idAngajat == 0 ? null : angajatiService.getAngajatById(idAngajat)), estimateTime));
        System.out.println("Masina " + masina.getID() + " a fost adaugata in coada de asteptare.");
    }

    public void removeFromWaitingClients(Integer masinaID) {
        for (int i = 0; i < waitingClients.size(); i++) {
            if (waitingClients.get(i).getMasina().getID().equals(masinaID)) {
                waitingClients.remove(waitingClients.get(i));
                System.out.println("Masina cu id-ul " + masinaID + " a fost stearsa din coada de asteptare.");
                return;
            }
        }
        System.out.println("Nu s-a gasit masina cu id-ul " + masinaID + " in coada de asteptare.");
    }

    // Auxiliary methods

    public WaitingClient getWaitingCarByAngajatID(Integer idAngajat) {
        for (int i = 0; i < waitingClients.size(); i++) {
            WaitingClient currentClient = waitingClients.get(i);
            if (currentClient.getWantedAngajat() != null && currentClient.getWantedAngajat().getId() == idAngajat) {
                return currentClient;
            }
        }
        return null;
    }

    public WaitingClient getWaitingClient() {
        for (int i = 0; i < waitingClients.size(); i++) {
            WaitingClient currentClient = waitingClients.get(i);
            if (currentClient.getWantedAngajat() == null) {
                return currentClient;
            }
        }
        return null;
    }

    public String getEstimateByAngajatID (Integer id) {
        Integer estimate = 0;
        for (int i = 0; i < repairSchedules.size(); i++) {
            estimate += repairSchedules.get(i).getTimeEstimateHours();
        }
        return estimate.toString();
    }

    public Boolean verifyAvailabilityStdByAngID(Integer id) {
        Integer countStandard = 0;

        for (int i = 0; i < repairSchedules.size(); i++) {
            if (repairSchedules.get(i).getAngajatID() == id) {
                if (repairSchedules.get(i).getMasina() instanceof MasinaStandard) {
                    countStandard++;
                }
            }
        }
        if (countStandard >= 3) {
            return false;
        }
        return true;
    }

    public Boolean verifyAvailabilityOthersByAngID(Integer id) {
        for (int i = 0; i < repairSchedules.size(); i++) {
            if (repairSchedules.get(i).getAngajatID() == id) {
                if (!(repairSchedules.get(i).getMasina() instanceof MasinaStandard)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addMasinaToRepair(Integer masinaType, Integer id) {
        Masina masina = initializeMasina(masinaType);

        System.out.println("Timp estimat: ");
        Integer estimateTime = input.nextInt();
        String enter = input.nextLine();

        repairSchedules.add(new WorksOn(angajatiService.getAngajatById(id), masina, estimateTime));
        System.out.println("Angajatul " + id + " a preluat masina");
    }

    public Masina initializeMasina(Integer masinaType) {
        System.out.println("Introduceti datele vechiculului");
        System.out.println("Nr de km: ");
        Double noOfKm = input.nextDouble();
        String enter = input.nextLine();

        System.out.println("Anul fabricatiei: ");
        Integer manufactureYear = input.nextInt();
        enter = input.nextLine();

        Boolean diesel;
        System.out.println("Diesel? (y/n): ");
        String answer = input.nextLine();
        if (answer.toLowerCase().equals("y")) {
            diesel = true;
        }
        else {
            diesel = false;
        }

        Masina masina;
        if (masinaType == 0) {
            String transmissionMode;
            System.out.println("Transmisie automata? (y/n): ");
            answer = input.nextLine();
            if (answer.toLowerCase().equals("y")) {
                transmissionMode = MasinaStandard.AUTOMAT_MODE;
            }
            else {
                transmissionMode = MasinaStandard.MANUAL_MODE;
            }
            masina = new MasinaStandard(noOfKm, manufactureYear, diesel, transmissionMode);
        }
        else if (masinaType == 1) {
            System.out.println("Numarul de locuri: ");
            Integer noOfSeats = input.nextInt();
            enter = input.nextLine();
            masina = new Autobuz(noOfKm, manufactureYear, diesel, noOfSeats);
        }
        else {
            System.out.println("Tonaj: ");
            Double tonnage = input.nextDouble();
            enter = input.nextLine();
            masina = new Camion(noOfKm, manufactureYear, diesel, tonnage);
        }
        return masina;
    }

    public WorksOn worksOnFindByIdMasina(Integer idMasina) {
        for (int i = 0; i < repairSchedules.size(); i++) {
            Masina currentMasina = repairSchedules.get(i).getMasina();
            if (currentMasina.getID().equals(idMasina)) {
                return repairSchedules.get(i);
            }
        }
        return null;
    }

    public Integer countReparatiiByAngajatID(Integer angajatID) {
        Integer count = 0;
        for (int i = 0; i < repairSchedules.size(); i++) {
            if (repairSchedules.get(i).getAngajatID() == angajatID) {
                count++;
            }
        }
        return count;
    }
}
