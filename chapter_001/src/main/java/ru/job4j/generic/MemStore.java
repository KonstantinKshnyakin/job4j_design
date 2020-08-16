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
        int index = privateFindById(id);
        if (checkIndex(index)) {
            return false;
        }
        mems.set(index, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        int index = privateFindById(id);
        if (checkIndex(index)) {
            return false;
        }
        mems.remove(index);
        return true;
    }

    @Override
    public T findById(String id) {
        int index = privateFindById(id);
        if (checkIndex(index)) {
            return null;
        }
        return mems.get(index);
    }

    private int privateFindById(String id) {
        for (int i = 0; i < mems.size(); i++) {
            if (mems.get(i).getId().equals(id)) {
                return i;
            }
        }
        System.out.println("id не найден");
        return -1;
    }

    private boolean checkIndex(int index) {
        return index == -1;
    }
}
