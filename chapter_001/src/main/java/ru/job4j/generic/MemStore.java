package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mems = new ArrayList<>();

    @Override
    public void add(T model) {
        mems.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        for (int i = 0; i < mems.size(); i++) {
            if (mems.get(i).getId().equals(id)) {
                mems.set(i, model);
                return true;
            } else {
                System.out.println("id не найден");
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (T mem : mems) {
            if (mem.getId().equals(id)) {
                mems.remove(mem);
                return true;
            } else {
                System.out.println("id не найден");
            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T mem : mems) {
            if (mem.getId().equals(id)) {
                return mem;
            } else {
                System.out.println("id не найден");
            }
        }
        return null;
    }
}
