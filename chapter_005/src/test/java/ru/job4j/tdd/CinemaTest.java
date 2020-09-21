package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import ru.job4j.tdd.impl.Account;
import ru.job4j.tdd.impl.Cinema;
import ru.job4j.tdd.impl.Session;
import ru.job4j.tdd.impl.Ticket;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

//    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

//    @Test(expected = IllegalArgumentException.class)
    public void whenBayOccupiedPlaceThenThrow() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        cinema.buy(account, 1, 1, date);
        cinema.buy(account, 1, 1, date);
    }

//    @Test(expected = IllegalArgumentException.class)
    public void whenBayTicketWithPastDateThenThrow() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 01, 10, 23, 00);
        cinema.buy(account, 1, 1, date);
    }

//    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(s -> true);
        assertThat(sessions, is(Arrays.asList(session)));
    }

//    @Test
    public void whenFindSpecificSessionThenFind() {
        Cinema cinema = new Cinema3D();
        Session session1 = new Session3D();
        Session session2 = new Session3D();
        cinema.add(session1);
        cinema.add(session2);
        List<Session> sessions = cinema.find(s -> s.equals(session2));
        assertThat(sessions, is(Arrays.asList(session2)));
    }
}
