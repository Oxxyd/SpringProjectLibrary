package Project.dao;

import Project.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import Project.model.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new Mapper());
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Mapper(), id)
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(full_name, year_born) VALUES(?, ?)", person.getFullName(), person.getYearBorn());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE Person SET full_name=?, year_born=? WHERE id=?", updatedPerson.getFullName(), updatedPerson.getYearBorn(),
                                                                                     id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public List<Book> booksAvailability(int id){     //наличиеКниг
        return jdbcTemplate.query("SELECT * FROM Books JOIN Person ON Person.id = Books.person_id WHERE Person.id = ?",
                                        new BeanPropertyRowMapper<>(Book.class), id);
    }
}
