package ru.job4j.conrol_task;

import java.util.List;

public class Analysis {

    public static Info diff(List<User> previous, List<User> current) {
        int oldCount = 0;
        int delCount = 0;
        int changCount = 0;
        boolean isEqualsId = false;
        for (User prevUser : previous) {
            for (User curUser : current) {
                isEqualsId = prevUser.getId() == curUser.getId();
                if (isEqualsId) {
                    oldCount++;
                    if (!prevUser.getName().equals(curUser.getName())) {
                        changCount++;
                    }
                    break;
                }
            }
            if (!isEqualsId) {
                delCount++;
            }
        }
        return new Info(current.size() - oldCount, changCount, delCount);
    }
}
