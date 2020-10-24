package ru.job4j.bd.services;

import ru.job4j.bd.dao.CategoryDao;
import ru.job4j.model.Category;

import java.util.List;

public class CategoryService {

    private static CategoryDao categoryDao;

    public CategoryService() {
        categoryDao = new CategoryDao();
    }

    public void save(Category category) {
        categoryDao.save(category);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }

    public Category findById(Integer id) {
        return categoryDao.findById(id);
    }

    public void delete(Integer id) {
        Category category = categoryDao.findById(id);
        categoryDao.delete(category);
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public void deleteAll() {
        categoryDao.deleteAll();
    }
}
