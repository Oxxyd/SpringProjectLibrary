package Project.model;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class Person {
    static LocalDate localDate = LocalDate.now();
    static int current_year = localDate.getYear();

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Pattern(regexp = ".+ .+ .+", message = "ФИО должно быть вида: Иванов Иван Иванович") //Как составлять регулярные выражения на кириллице я не нашел(
    private String fullName;
    private int id;
    @Min(value = 1900, message = "Год рождения должен быть корректным")
    @Max(value = 2023, message = "Год рождения должен быть корректным")
    private int yearBorn;

    public Person() {

    }

    public Person(int id, String fullName, int yearBorn) {
        this.id = id;
        this.fullName = fullName;
        this.yearBorn = yearBorn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(int age) {
        this.yearBorn = age;
    }

}