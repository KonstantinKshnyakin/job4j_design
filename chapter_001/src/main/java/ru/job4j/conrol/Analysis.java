package ru.job4j.conrol;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analysis {

    public static Info diff(List<User> previous, List<User> current) {
        int changCount = 0;
        int newCount = 0;
        Map<Integer, User> prevMap = previous.stream().collect(Collectors.toMap(User::getId, user -> user));
        for (User user1 : current) {
            int userId = user1.getId();
            User user2 = prevMap.get(userId);
            if (user2 == null) {
                newCount++;
            } else if (!user1.equals(user2)) {
                changCount++;
            }
        }
        int deleted = previous.size() + newCount - current.size();
        return new Info(newCount, changCount, deleted);
    }
}
