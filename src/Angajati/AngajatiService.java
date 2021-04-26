package Angajati;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AngajatiService {
    private static AngajatiService instance;
    private List<Angajat> angajati;
    private final int MAX_LENGTH_NAME = 30;
    private final int MIN_AGE = 18;

    private AngajatiService() {
        angajati = new ArrayList<>();
    }

    public static AngajatiService getInstance() {
        if (instance == null) {
            instance = new AngajatiService();
        }
        return instance;
    }

    // Methods for CRUD Operations

    // Create

    public void addAngajat(Angajat angajat) {
        angajati.add(angajat);
    }

    public Boolean addAngajat() {
        Scanner input = new Scanner(System.in);
        System.out.println("Functie angajat (0 - Director, 1 - Mecanic, 2 - Asistent): ");
        int type = input.nextInt();
        String enter = input.nextLine();
        System.out.println("Prenume: ");
        String firstName = input.nextLine();
        System.out.println("Nume: ");
        String lastName = input.nextLine();
        System.out.println("An nastere: ");
        int yearB = input.nextInt();
        enter = input.nextLine();
        System.out.println("Luna nastere: ");
        int monthB = input.nextInt();
        enter = input.nextLine();
        System.out.println("Zi nastere: ");
        int  dayB = input.nextInt();
        enter = input.nextLine();
        System.out.println("An angajare: ");
        int  yearH = input.nextInt();
        enter = input.nextLine();
        System.out.println("Luna angajare: ");
        int  monthH = input.nextInt();
        enter = input.nextLine();
        System.out.println("Zi angajare: ");
        int  dayH = input.nextInt();
        enter = input.nextLine();

        LocalDate birthday = LocalDate.of(yearB, monthB, dayB);
        LocalDate hireDate = LocalDate.of(yearH, monthH, dayH);

        if (validateName(lastName) && validateName(firstName) && validateHireDate(hireDate) && validateBirthday(birthday)) {
            Angajat angajat;
            if (type == 0) {
                angajat = new Director(firstName, lastName, birthday, hireDate);
            }
            else if (type == 1) {
                angajat = new Mecanic(firstName, lastName, birthday, hireDate);
            }
            else {
                angajat = new Asistent(firstName, lastName, birthday, hireDate);
            }
            angajati.add(angajat);
            return true;
        }
        return false;
    }

    // Read

    public List<Angajat> getAngajati() {
        return angajati;
    }

    public Angajat getAngajatById(int id) {
        for (int i = 0; i < angajati.size(); i++) {
            if (angajati.get(i).getId() == id) {
                return angajati.get(i);
            }
        }
        return null;
    }

    public int getIndexAngajatById(int id) {
        for (int i = 0; i < angajati.size(); i++) {
            if (angajati.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void showAngajati() {
        if (angajati.size() != 0) {
            for (int i = 0; i < angajati.size(); i++) {
                System.out.println(angajati.get(i).toString());
            }
        }
        else {
            System.out.println("Nu exista angajati inregistrati.");
        }

    }

    // Update

    public Boolean editAngajatById(int id) {
        int indexAngajat = getIndexAngajatById(id);
        if (indexAngajat != -1) {
            Scanner input = new Scanner(System.in);
            String answer, enter;

            System.out.println("Modificare nume?(y/n)");
            answer = input.nextLine();
            if (answer.toLowerCase().equals("y")) {
                String newName = input.nextLine();
                angajati.get(indexAngajat).setLastName(newName);
            }

            System.out.println("Modificare prenume?(y/n)");
            answer = input.nextLine();
            if (answer.toLowerCase().equals("y")) {
                String newName = input.nextLine();
                angajati.get(indexAngajat).setFirstName(newName);
            }

            System.out.println("Modificare data angajare?(y/n)");
            answer = input.nextLine();
            if (answer.toLowerCase().equals("y")) {
                System.out.println("An angajare: ");
                int  yearH = input.nextInt();
                enter = input.nextLine();
                System.out.println("Luna angajare: ");
                int  monthH = input.nextInt();
                enter = input.nextLine();
                System.out.println("Zi angajare: ");
                int  dayH = input.nextInt();
                enter = input.nextLine();
                LocalDate hireDate = LocalDate.of(yearH, monthH, dayH);
                angajati.get(indexAngajat).setHireDate(hireDate);
            }

            System.out.println("Modificare data nastere?(y/n)");
            answer = input.nextLine();
            if (answer.toLowerCase().equals("y")) {
                System.out.println("An nastere: ");
                int yearB = input.nextInt();
                enter = input.nextLine();
                System.out.println("Luna nastere: ");
                int monthB = input.nextInt();
                enter = input.nextLine();
                System.out.println("Zi nastere: ");
                int dayB = input.nextInt();
                enter = input.nextLine();
                LocalDate birthday = LocalDate.of(yearB, monthB, dayB);
                angajati.get(indexAngajat).setBirthday(birthday);
            }
            System.out.println("S-au inregistrat modificarile pentru angajatul "+ angajati.get(indexAngajat).toString());
            return true;
        }
        return false;
    }

    // Delete

    public Boolean removeAngajatById(int id) {
        Angajat angajat = getAngajatById(id);
        if (angajat != null) {
            angajati.remove(angajat);
            return true;
        }
        return false;
    }

    // Auxiliary methods

    public double calculSalariu(int id) {
        Angajat angajat = getAngajatById(id);
        if (angajat != null) {
            return angajat.getSalary();
        }
        return 0;
    }
    public Boolean validateName(String name) {
        Boolean test = (name.length() <= MAX_LENGTH_NAME);
        if(!test)
            System.out.println("Nume sau prenume nevalid (depaseste " + MAX_LENGTH_NAME + " caractere), incercati din nou.");
        return test;
    }
    public Boolean validateBirthday(LocalDate birthday) {
        Boolean test = (Period.between(birthday, LocalDate.now()).getYears() >= MIN_AGE);
        if(!test)
            System.out.println("Angajatul trebuie sa fie major, incercati din nou.");
        return test;
    }
    public Boolean validateHireDate(LocalDate hireDate) {
        Boolean test = (Period.between(hireDate, LocalDate.now()).getYears() >= 0);
        if(!test)
            System.out.println("Nu se poate pune o data din viitor, incercati din nou.");
        return test;
    }

}
