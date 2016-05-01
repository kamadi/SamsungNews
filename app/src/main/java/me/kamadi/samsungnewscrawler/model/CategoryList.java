package me.kamadi.samsungnewscrawler.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class CategoryList {
    private static List<Category> categories;

    static {
        categories = new ArrayList<>();
        categories.add(new Category("News", ""));
        categories.add(new Category("Infographics", "infographics"));
        categories.add(new Category("Interview", "view"));
        categories.add(new Category("Video", "video"));
    }

    public static List<Category> getCategories() {
        return categories;
    }
}
