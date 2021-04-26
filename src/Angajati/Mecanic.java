package Angajati;

import java.time.LocalDate;

public class Mecanic extends Angajat{
    final double SALARY_COEFFICIENT = 1.5;

    public Mecanic(String firstName, String lastName, LocalDate birthday, LocalDate hireDate) {
        super(firstName, lastName, birthday, hireDate);
        this.setSalaryCoefficient(SALARY_COEFFICIENT);
    }

    @Override
    public String toString() {
        return "Mecanic: " + getFirstName() + " " + getLastName().toUpperCase();
    }
}
