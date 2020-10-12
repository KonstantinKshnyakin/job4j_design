package ru.job4j.concurrent.nonblock;

import java.util.concurrent.ConcurrentHashMap;

public class NonblockingCache {

    private final ConcurrentHashMap<Integer, Base> bases = new ConcurrentHashMap<>();
//    private final HashMap<Integer, Base> bases = new HashMap<>();

    public Base add(Base base) {
        return bases.putIfAbsent(base.getId(), base);
    }

    public Base update(Base base) {
        return bases.computeIfPresent(base.getId(),
                (key, oldBase) -> {
                    if (base.getVersion() != oldBase.getVersion()) {
                        throw new OptimisticException();
                    } else {
                        base.incrementVersion();
                    }
                    return base;
                });
    }

    public Base delete(Base base) {
        return bases.remove(base.getId());
    }
}
