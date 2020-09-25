package ru.job4j.menu;

import ru.job4j.menu.outer.InputOutput;
import ru.job4j.menu.outer.InputOutputImpl;
import ru.job4j.menu.title.Title;
import ru.job4j.menu.title.Titles;

import java.util.List;

public class StartMenu {

    public static final String WANT_TO_EXIT = "Select \"-1\" if want to exit.";

    public void init(InputOutput inputOutput, Titles title) {
        boolean flag = true;
        while (flag) {
            this.showMenu(inputOutput, title);
            List<Integer> answer = ask(inputOutput);
            if (!isExit(answer)) {
                showContent(inputOutput, title, answer);
            } else {
                flag = false;
            }
        }
    }

    private List<Integer> ask(InputOutput inputOutput) {
        return inputOutput.ask("Select : ");
    }

    private boolean isExit(List<Integer> listId) {
        return listId.get(0) == -1;
    }

    private void showContent(InputOutput inputOutput, Titles title, List<Integer> listId) {
        String content = title.showContent(listId);
        inputOutput.printStr(content);
        inputOutput.printStr(WANT_TO_EXIT);
        List<Integer> answer = ask(inputOutput);
        if (isExit(answer)) {
            showMenu(inputOutput, title);
        }
    }

    private void showMenu(InputOutput inputOutput, Titles title) {
        inputOutput.printStr("Menu.");
        inputOutput.printStr(title.showTitles());
        inputOutput.printStr(WANT_TO_EXIT);
    }

    public static void main(String[] args) {
        Title subTitle111 = new Title("1.1.1", "Task \"1.1.1\"", "Content \"1.1.1\"");
        Title subTitle112 = new Title("1.1.2", "Task \"1.1.2\"", "Content \"1.1.2\"");
        Title subTitle113 = new Title("1.1.3", "Task \"1.1.3\"", "Content \"1.1.3\"");
        Title[] titles11 = {subTitle111, subTitle112, subTitle113};
        Title subTitle11 = new Title("1.1", "Task \"1.1\"", "Content \"1.1\"", titles11);
        Title[] titles1 = {subTitle11};
        Title title1 = new Title("1", "Task \"1\"", "Content \"1\"", titles1);

        new StartMenu().init(new InputOutputImpl(), title1);
    }
}
