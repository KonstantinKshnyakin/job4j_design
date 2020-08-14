package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class UserStoreTest {

    private UserStore userStore;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setup() {
        userStore = new UserStore();
        user1 = new User("user1");
        user2 = new User("user2");
        user3 = new User("user3");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
    }

    @Test
    public void whenFindByIdExist() {
        User result = userStore.findById("user1");
        assertThat(result.equals(user1), is(true));
    }

    @Test
    public void whenFindByIdNonExist() {
        User result = userStore.findById("user4");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenReplaceExist() {
        assertThat(userStore.replace("user1", user3), is(true));
    }

    @Test
    public void whenReplaceNonExist() {
        assertThat(userStore.replace("user4", user2), is(false));
    }

    @Test
    public void whenDeleteExist() {
        assertThat(userStore.delete("user1"), is(true));
        assertThat(userStore.findById("user1"), is(nullValue()));
    }

    @Test
    public void whenDeleteNonExist() {
        assertThat(userStore.delete("user4"), is(false));
    }
}
