package ru.job4j.menu.title;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Title implements Titles {

    private final String id;
    private final String headline;
    private final String content;
    private Title[] child;
    public static final char DASH = '-';
    public static final String SEP = System.lineSeparator();

    public Title(String id, String headline, String content) {
        this.id = id;
        this.headline = headline;
        this.content = content;
    }

    public Title(String id, String headline, String content, Title[] child) {
        this.id = id;
        this.headline = headline;
        this.content = content;
        this.child = child;
    }

    public Title[] getChild() {
        return child;
    }

    public String getId() {
        return id;
    }

    @Override
    public String showContent(List<Integer> listId) {
        if (listId.size() == 1) {
            return this.content;
        } else {
            listId.remove(0);
            return getTreeElement(this.child, listId);
        }
    }

    private String getTreeElement(Title[] child, List<Integer> listId) {
        Title title = child[listId.get(0) - 1];
        listId.remove(0);
        if (listId.size() != 0) {
            return getTreeElement(title.child, listId);
        }
        return title.content;
    }

    @Override
    public String showTitles() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.id).append(" ").append(this.headline).append(SEP);
        if (this.child != null) {
            String tree = showTree(this.child, 1);
            builder.append(tree);
        }
        return builder.toString();
    }

    private String showTree(Title[] childs, int treeLvl) {
        StringBuilder builder = new StringBuilder();
        for (Title child : childs) {
            String repeat = StringUtils.repeat(DASH, treeLvl);
            builder.append(repeat).append(child.id).append(" ").append(child.headline).append(SEP);
            if (child.child != null) {
                treeLvl++;
                String tree = showTree(child.child, treeLvl);
                builder.append(tree);
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Title{"
                + "name='" + id + '\''
                + ", child=" + Arrays.toString(child)
                + '}';
    }
}
