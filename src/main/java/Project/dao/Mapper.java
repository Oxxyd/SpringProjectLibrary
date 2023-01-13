package Project.dao;

import Project.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt("id"));
        person.setFullName(rs.getString("full_name"));
        person.setYearBorn(rs.getInt("year_born"));

        return person;
    }
}
