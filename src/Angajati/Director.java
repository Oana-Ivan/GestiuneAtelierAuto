package Angajati;

import java.time.LocalDate;

public class Director extends Angajat{
    final double SALARY_COEFFICIENT = 2;

    public Director(String firstName, String lastName, LocalDate birthday, LocalDate hireDate) {
        super(firstName, lastName, birthday, hireDate);
        this.setSalaryCoefficient(SALARY_COEFFICIENT);
    }

    @Override
    public String toString() {
        return "Director: " + getFirstName() + " " + getLastName().toUpperCase();
    }
}
