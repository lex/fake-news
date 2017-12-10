package pw.yuuh.genuinenews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pw.yuuh.genuinenews.domain.Category;
import pw.yuuh.genuinenews.repository.CategoryRepository;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    private String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }


    @GetMapping("/{id}")
    public String showCategory(Model model, @PathVariable Long id) {
        final Category c = categoryRepository.getOne(id);
        model.addAttribute("category", c);
        return "category";
    }

    @Transactional
    @PostMapping("/")
    private String addCategory(@RequestParam String name) {
        final Category c = new Category();
        c.setName(name);
        categoryRepository.save(c);
        return "redirect:/categories/";
    }
}
