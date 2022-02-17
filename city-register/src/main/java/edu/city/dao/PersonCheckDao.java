package edu.city.dao;

import edu.city.domain.PersonRequest;
import edu.city.domain.PersonResponse;
import edu.city.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDao {

    private ConnectionBuilder connectionBuilder;

    private static final String SQL_REQUEST = "select temporal, upper(p.sur_name), upper('Васильев') from cr_adress_person ap " +
            "inner join cr_person p on p.person_id = ap.person_id " +
            "inner join cr_adress a on a.adress_id = ap.adress_id " +
            "where " +
            "current_date >= ap.start_date and (current_date <= ap.end_date or " +
            "ap.end_date is null) " +
            "and upper(p.sur_name) = upper(?) " +
            "and upper(p.given_name) = upper(?) " +
            "and upper(p.patronymic) = upper(?) " +
            "and p.date_of_birth = ? " +
            "and a.street_code = ? " +
            "and upper(a.building) = upper(?) ";

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException
    {
        PersonResponse response = new PersonResponse();

        String sql = SQL_REQUEST;
        sql = checkSqlRequest(request, sql);
        try (Connection con = getConnection();
             PreparedStatement stm = con.prepareStatement(sql))
        {
            int count = 1;
            stm.setString(count++, request.getSurName());
            stm.setString(count++, request.getGivenName());
            stm.setString(count++, request.getPatronymic());
            stm.setDate(count++, java.sql.Date.valueOf(request.getDateOfBirth()));
            stm.setInt(count++, request.getStreetCode());
            stm.setString(count++, request.getBuilding());

            if (request.getExtension() != null)
            {
                stm.setString(count++, request.getExtension());
            }
            if (request.getAppartment() != null)
            {
                stm.setString(count++, request.getAppartment());
            }



            ResultSet rs = stm.executeQuery();
            if (rs.next())
            {
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }
        } catch (SQLException ex)
        {
            throw new PersonCheckException(ex);
        }

        return response;
    }

    private String checkSqlRequest(PersonRequest request, String sql) {
        if (request.getExtension() != null)
        {
            sql+="and upper(a.extension) = upper(?) ";
        } else {
            sql += "and extension is null ";
        }
        if (request.getAppartment() != null)
        {
            sql+= "and upper(a.appartment) = upper(?) ";
        } else {
            sql += "and appartment  is null ";
        }
        return sql;
    }


}
