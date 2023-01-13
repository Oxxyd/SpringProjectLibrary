package Project.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Длина названия книги должна составлять от 2 до 100 символов")
    private String name;
    @NotEmpty(message = "Имя автора не должно быть пустым")
    private String authorName;
    private int year;

    public Book(){}

    public Book(String name, String authorName, int year, int id) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
