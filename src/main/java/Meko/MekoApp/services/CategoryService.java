package Meko.MekoApp.services;

import Meko.MekoApp.entities.Category;
import Meko.MekoApp.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        List<Category> cates = categoryRepository.findAll();
        return cates;
    }
}
