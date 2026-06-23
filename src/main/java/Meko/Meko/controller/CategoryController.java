package Meko.Meko.controller;

import Meko.Meko.entities.Category;
import Meko.Meko.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String findAdll(Model model) {
        model.addAttribute("cates", categoryService.findAll());
        return "homepage/category";
    }

    @GetMapping("/create-cate")
       public String create(Model model){
        model.addAttribute("cate", new Category()
        );
        return "dashboard/cate_create";

    }

    @PostMapping("/create")
    public String store(
            @ModelAttribute Category category)
    {
        categoryService.save(category);
        return "redirect:/category";
    }
    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Integer id
    )
    {
       categoryService.delete(id);
       return "redirect:/category";

    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable int id, Model model)
    {
        model.addAttribute(
                "category",
                categoryService.findById(id)
        );
        return "dashboard/cate_edit";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable int id,
            @ModelAttribute Category formCate)
    {
        Category category = categoryService.findById(id);
        category.setCategoryName(formCate.getCategoryName());
        category.setDescription(formCate.getDescription());

        categoryService.save(category);
        return "redirect:/category";
    }



}
