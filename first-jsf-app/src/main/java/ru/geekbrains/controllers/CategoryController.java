package ru.geekbrains.controllers;

import lombok.Getter;
import lombok.Setter;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {
    @Inject
    private CategoryRepository categoryRepository;

    @Getter
    @Setter
    private Category category;

    public String createCategory() {
        category = new Category();
        return "/category_form.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category_form.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteById(category.getId());
    }

    public String saveCategory() {
        categoryRepository.saveOrUpdate(category);
        return "/category.xhtml?faces-redirect=true";
    }
}
