import Atelier.Atelier;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Atelier atelier;

    public static void main(String[] args) {
        atelier = Atelier.getInstance();

        Scanner input = new Scanner(System.in);
        String enter;

        int optiuneAleasa = 1;
        while (optiuneAleasa != 0) {
            showMenu();
            try {
                optiuneAleasa = input.nextInt();
                enter = input.nextLine();
                switch (optiuneAleasa) {
                    case 0:
                        break;
                    case 1: {
                        optiuniAngajat();
                        break;
                    }
                    case 2: {
                        optiuniMasini();
                        break;
                    }
                    case 3: {
                        optiuniAtelier();
                        break;
                    }

                    default:
                        System.out.println("Optiunea aleasa nu exista.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Optiunea trebuie sa fie numerica.  Incearcati din nou");
                enter = input.nextLine();
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n\nMeniu Principal - Alegeti intre:");
        System.out.println("0. Iesire");
        System.out.println("1. Optiuni angajati");
        System.out.println("2. Optiuni masini");
        System.out.println("3. Optiuni atelier");
    }

    public static void showMenuAngajati() {
        System.out.println("\n\nOptiuni angajati - Alegeti intre:");
        System.out.println("0. Inapoi la Meniul Principal");
        System.out.println("1. Afisare angajati");
        System.out.println("2. Adaugare angajat");
        System.out.println("3. Stergere angajat");
        System.out.println("4. Editare angajat");
        System.out.println("5. Afisare salariu angajat");
    }

    public static void showMenuMasini() {
        System.out.println("\n\nOptiuni masini - Alegeti intre:");
        System.out.println("0. Inapoi la Meniul Principal");
        System.out.println("1. Afisare masini in curs de reparare");
        System.out.println("2. Afisare masini din coada de asteptare");
        System.out.println("3. Adaugare masina");
        System.out.println("4. Adaugare finalizare reparatie");
        System.out.println("5. Stergere din coada de asteptare");
    }

    public static void showSubMenuMasini() {
        System.out.println("\n\nAdaugare masina - Alegeti intre:");
        System.out.println("0. Inapoi la Optiuni Masini");
        System.out.println("1. Adaugare masina");
        System.out.println("2. Adaugare masina la un anumit angajat");
    }

    public static void showMenuAtelier() {
        System.out.println("\n\nOptiuni atelier - Alegeti intre:");
        System.out.println("0. Inapoi la Meniul Principal");
        System.out.println("1. Afisarea angajatului care repara cele mai multe masini in prezent");
        System.out.println("2. Top 3 angajati - polite de asigurare");
        System.out.println("3. Top 3 angajati - autobuze noi");
        System.out.println("4. Cel mai solicitat angajat");
        System.out.println("5. Bacsis angajati");
    }

    public static void optiuniAngajat() {
        Scanner input = new Scanner(System.in);
        String enter;
        int optiuneAleasaAngajati = 1;
        while (optiuneAleasaAngajati != 0) {
            showMenuAngajati();
            try {
                optiuneAleasaAngajati = input.nextInt();
                switch (optiuneAleasaAngajati) {
                    case 0:
                        break;
                    case 1: {
                        atelier.showAngajati();
                        break;
                    }
                    case 2: {
                        if (atelier.addAngajat()) {
                            System.out.println("Angajat adaugat cu succes.");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("Id angajat: ");
                        int id = input.nextInt();
                        enter = input.nextLine();
                        if (atelier.removeAngajatById(id)) {
                            System.out.println("Angajat sters cu succes.");
                        }
                        else {
                            System.out.println("Nu exita angajat cu id-ul " + id);
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("Id angajat: ");
                        int id = input.nextInt();
                        enter = input.nextLine();
                        if (atelier.editAngajatById(id)) {
                            System.out.println("Date angajat editate cu succes.");
                        }
                        else {
                            System.out.println("Angajatul cu id-ul "+ id + " nu exista.");
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("Id angajat: ");
                        int id = input.nextInt();
                        enter = input.nextLine();
                        double salary = atelier.calculSalariu(id);
                        if (salary == 0) {
                            System.out.println("Nu exita angajat cu id-ul " + id);
                        }
                        else {
                            System.out.println("Salariu: " + salary);
                        }
                        break;
                    }
                    default:
                        System.out.println("Optiunea aleasa nu exista.");
                }
                System.out.println("Apasati enter pentru a reveni la meniu.");
                enter = input.nextLine();
                enter = input.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println("Optiunea trebuie sa fie numerica.");
            }
        }
    }

    public static void optiuniMasini() {
        Scanner input = new Scanner(System.in);
        String enter;

        if (atelier.isOpen()) {
            int optiuneAleasaMasini = 1;
            while (optiuneAleasaMasini != 0) {
                showMenuMasini();
                try {
                    optiuneAleasaMasini = input.nextInt();
                    switch (optiuneAleasaMasini) {
                        case 0:
                            break;
                        case 1: {
                            atelier.showRepairSchedules();
                            System.out.println("Apasati enter pentru a reveni la meniu.");
                            enter = input.nextLine();
                            break;
                        }
                        case 2: {
                            atelier.showWaitingClients();
                            System.out.println("Apasati enter pentru a reveni la meniu.");
                            enter = input.nextLine();
                            break;
                        }
                        case 3: {
                            optiuniSubMeniuMasini();
                            break;
                        }
                        case 4: {
                            atelier.finishRepairMasina();
                            System.out.println("Apasati enter pentru a reveni la meniu.");
                            enter = input.nextLine();
                            break;
                        }
                        case 5: {
                            System.out.println("Id: ");
                            Integer masinaId = input.nextInt();
                            enter = input.nextLine();
                            atelier.removeFromWaitingClients(masinaId);
                            System.out.println("Apasati enter pentru a reveni la meniu.");
                            enter = input.nextLine();
                            break;
                        }

                        default:
                            System.out.println("Optiunea aleasa nu exista.");
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Optiunea trebuie sa fie numerica.");
                }
                enter = input.nextLine();
            }
        }
        else {
            System.out.println("Atelierul nu este deschis deoarece nu exista niciun angajat inregistrat. Apasati enter pentru a reveni la meniul principal");
            enter = input.nextLine();
        }
    }

    public static void optiuniSubMeniuMasini() {
        Scanner input = new Scanner(System.in);
        String enter;

        int optiuneAleasaMasiniS = 1;
        while (optiuneAleasaMasiniS != 0) {
            showSubMenuMasini();
            try {
                optiuneAleasaMasiniS = input.nextInt();

                switch (optiuneAleasaMasiniS) {
                    case 0:
                        break;
                    case 1: {
                        atelier.addMasinaToAnyAngajat();
                        System.out.println("Apasati enter pentru a reveni la meniu.");
                        enter = input.nextLine();
                        break;
                    }
                    case 2: {
                        atelier.addMasinaByAngajatID();
                        System.out.println("Apasati enter pentru a reveni la meniu.");
                        enter = input.nextLine();
                        break;
                    }
                    default:
                        System.out.println("Optiunea aleasa nu exista.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Optiunea trebuie sa fie numerica.");
            }
            enter = input.nextLine();
        }
    }

    public static void optiuniAtelier() {
        Scanner input = new Scanner(System.in);
        String enter;
        if (atelier.isOpen()) {
            int optiuneAleasaA = 1;
            while (optiuneAleasaA != 0) {
                showMenuAtelier();
                try {
                    optiuneAleasaA = input.nextInt();
                    switch (optiuneAleasaA) {
                        case 0:
                            break;
                        case 1: {
                            atelier.showAngajatIncarcareMaxima();
                            break;
                        }
                        case 2: {
                            atelier.angajatMaxPolite();
                            break;
                        }
                        case 3: {
                            atelier.angajatiAutobuzeNoi();
                            break;
                        }
                        case 4: {
                            atelier.celMaiSolicitatAngajat();
                            break;
                        }
                        case 5: {
                            atelier.showBacsisAngajati();
                            break;
                        }
                        default:
                            System.out.println("Optiunea aleasa nu exista.");
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Optiunea trebuie sa fie numerica.");
                }
                enter = input.nextLine();
            }
        }
        else {
            System.out.println("Atelierul nu este deschis deoarece nu exista niciun angajat inregistrat. Apasati enter pentru a reveni la meniul principal");
            enter = input.nextLine();
        }
    }

}
