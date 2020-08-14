package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class RoleStoreTest {

    private RoleStore roleStore;
    private Role role1;
    private Role role2;
    private Role role3;

    @Before
    public void setup() {
        roleStore = new RoleStore();
        role1 = new Role("Role1");
        role2 = new Role("Role2");
        role3 = new Role("Role3");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
    }

    @Test
    public void whenFindByIdExist() {
        Role result = roleStore.findById("Role1");
        assertThat(result.equals(role1), is(true));
    }

    @Test
    public void whenFindByIdNonExist() {
        Role result = roleStore.findById("Role4");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenReplaceExist() {
        assertThat(roleStore.replace("Role1", role3), is(true));
    }

    @Test
    public void whenReplaceNonExist() {
        assertThat(roleStore.replace("Role4", role2), is(false));
    }

    @Test
    public void whenDeleteExist() {
        assertThat(roleStore.delete("Role1"), is(true));
        assertThat(roleStore.findById("Role1"), is(nullValue()));
    }

    @Test
    public void whenDeleteNonExist() {
        assertThat(roleStore.delete("Role4"), is(false));
    }
}
