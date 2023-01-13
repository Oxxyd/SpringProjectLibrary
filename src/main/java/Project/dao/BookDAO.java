package Project.dao;

import Project.model.Book;
import Project.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Books WHERE id = ?", new BeanPropertyRowMapper<>(Book.class),
                                        id).stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Books(name, author_name, year) VALUES(?, ?, ?)", book.getName(), book.getAuthorName(), book.getYear());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE Books SET name=?, author_name=?, year=? WHERE id = ? ", updatedBook.getName(), updatedBook.getAuthorName(),
                                                                                                updatedBook.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Books WHERE id=?", id);
    }

    public Person bookAccess(int id){ //доступностьКниги
        return jdbcTemplate.query("SELECT * FROM Person JOIN Books ON Person.id = Books.person_id WHERE Books.id = ?",
                                    new Mapper(), id).stream().findAny().orElse(null);
    }

    public void makeFree(int id){
        jdbcTemplate.update("UPDATE Books SET person_id = NULL WHERE id = ?", id);
    }

    public void setAPerson(int personId, int id){
        jdbcTemplate.update("UPDATE Books SET person_id = ? WHERE id = ?", personId, id);
    }
}
