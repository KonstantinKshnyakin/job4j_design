package ru.job4j.concurrent.async.out.bd;

import ru.job4j.concurrent.async.in.SimpleProperties;
import ru.job4j.concurrent.async.model.Country;
import ru.job4j.concurrent.async.model.Member;

import java.sql.*;
import java.util.List;

public class DataBase {

    private final SimpleProperties properties;
    private Connection connection;

    public static final String INSERT_COUNTRY = "insert into country (country_name) values (?)" +
            " on conflict (country_name) do update set country_name = excluded.country_name returning id";
    public static final String INSERT_MEMBERS = "insert into member (first_name, last_name, rating, country_id) values (?, ?, ?, ?)";


    public DataBase(SimpleProperties properties) {
        this.properties = properties;
    }

    public void init() {
        try {
            Class.forName(properties.getProperty("jdbc.driver"));
            connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"),
                    properties.getProperty("jdbc.password")
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Member> saveListMembers(List<Member> listMembers) {
        ResultSet rs = null;
        for (Member member : listMembers) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_MEMBERS, PreparedStatement.RETURN_GENERATED_KEYS)) {
                saveCountry(member.getCountry());
                ps.setString(1, member.getFirstName());
                ps.setString(2, member.getLastName());
                ps.setInt(3, member.getRating());
                ps.setInt(4, member.getCountry().getId());
                ps.execute();

                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    member.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return listMembers;
    }

    public Country saveCountry(Country country) {
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_COUNTRY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, country.getName());
            ps.execute();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt("id");
                country.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return country;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
