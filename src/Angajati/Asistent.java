package Angajati;

import java.time.LocalDate;

public class Asistent extends Angajat{
    final int SALARY_COEFFICIENT = 1;

    public Asistent(String firstName, String lastName, LocalDate birthday, LocalDate hireDate) {
        super(firstName, lastName, birthday, hireDate);
        this.setSalaryCoefficient(SALARY_COEFFICIENT);
    }

    @Override
    public String toString() {
        return "Asistent: " + getFirstName() + " " + getLastName().toUpperCase();
    }
}
