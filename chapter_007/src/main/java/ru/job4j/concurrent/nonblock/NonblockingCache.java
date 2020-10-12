package ru.job4j.concurrent.nonblock;

import java.util.concurrent.ConcurrentHashMap;

public class NonblockingCache {

    private final ConcurrentHashMap<Integer, Base> bases = new ConcurrentHashMap<>();
//    private final HashMap<Integer, Base> bases = new HashMap<>();

    public Base add(Base base) {
        return bases.putIfAbsent(base.getId(), base);
    }

    public Base update(Base newBase) {
        return bases.computeIfPresent(newBase.getId(),
                (key, oldBase) -> {
                    int newVersion = newBase.getVersion();
                    if (newVersion != oldBase.getVersion()) {
                        throw new OptimisticException();
                    } else {
                        newBase.setVersion(newVersion + 1);
                    }
                    return newBase;
                });
    }

    public Base delete(Base base) {
        return bases.remove(base.getId());
    }
}
