package Angajati;

import java.time.LocalDate;
import java.time.Year;

public abstract class Angajat {
    final private int BASE_SALARY = 1000;

    private static int noOfAngatati;
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private LocalDate hireDate;
    private Integer nrSolicitari;
    private double salaryCoefficient;

    public Angajat(String firstName, String lastName, LocalDate birthday, LocalDate hireDate) {
        noOfAngatati++;
        this.id = noOfAngatati;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hireDate = hireDate;
        this.nrSolicitari = 0;
    }

    public void incrementNrSolicitari() {
        nrSolicitari++;
    }

    public double getSalary() {
        return getVechime() * salaryCoefficient * BASE_SALARY;
    }

    public int getVechime() {
        int currentYear = Year.now().getValue();
        int hireYear = hireDate.getYear();
        return (currentYear - hireYear);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public double getSalaryCoefficient() {
        return salaryCoefficient;
    }

    public Integer getNrSolicitari() {
        return nrSolicitari;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setSalaryCoefficient(double salaryCoefficient) {
        this.salaryCoefficient = salaryCoefficient;
    }

    public void setNrSolicitari(Integer nrSolicitari) {
        this.nrSolicitari = nrSolicitari;
    }
}
