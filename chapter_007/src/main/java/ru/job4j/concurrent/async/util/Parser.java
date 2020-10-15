package ru.job4j.concurrent.async.util;

import ru.job4j.concurrent.async.model.Country;
import ru.job4j.concurrent.async.model.Member;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Member> parseListMember(List<String> memberList) {
        List<Member> list = new ArrayList<>();
        for (String memberStr : memberList) {
            String[] memberSplit = memberStr.split("  ");
            list.add(
                    new Member(
                            memberSplit[0],
                            memberSplit[1],
                            Integer.parseInt(memberSplit[3]),
                            new Country(memberSplit[2])
                    )
            );
        }
        return list;
    }
}
