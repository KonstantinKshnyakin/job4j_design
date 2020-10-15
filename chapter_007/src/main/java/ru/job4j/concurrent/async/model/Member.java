package ru.job4j.concurrent.async.model;

import java.util.Objects;

public class Member {

    private int id;
    private String firstName;
    private String lastName;
    private int rating;
    private Country country;

    public Member(String firstName, String lastName, int rating, Country country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return id != member.id
                && rating != member.rating
                && Objects.equals(country, member.country)
                && Objects.equals(firstName, member.firstName)
                && Objects.equals(lastName, member.lastName);
    }

    @Override
    public int hashCode() {
        int result = 17 + id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Member{"
                + "id=" + id
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", rating=" + rating
                + ", country=" + country
                + '}';
    }
}
