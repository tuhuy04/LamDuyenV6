package com.example.WebAoDai.controller.admin;

import com.example.WebAoDai.entity.Category;
import com.example.WebAoDai.entity.CategorySize;
import com.example.WebAoDai.entity.Size;
import com.example.WebAoDai.repository.CategoryRepository;
import com.example.WebAoDai.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("")
    public String categoryPage(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin/category/list";
    }

    @GetMapping("/insertCategoryPage")
    public String insertCategoryPage(Model model) {
        Category categoryEntity = new Category();
        model.addAttribute("category", categoryEntity);
        return "admin/category/insert";
    }

    @Autowired
    private SizeRepository sizeRepository;

    @PostMapping("/save")
    public String save(@ModelAttribute(name = "category") Category categoryEntity, Model model) {
        Optional<Category> existingCategory = categoryRepository.findByName(categoryEntity.getName());
        if (existingCategory.isPresent()) {
            model.addAttribute("error", "Danh mục này đã tồn tại.");
            return "admin/category/insert";
        }

        categoryEntity.setStatus(1);

        List<Integer> defaultSizeIds = List.of(1, 2, 3);
        List<Size> defaultSizes = sizeRepository.findAllById(defaultSizeIds);

        List<CategorySize> categorySizeList = defaultSizes.stream().map(size -> {
            CategorySize cs = new CategorySize();
            cs.setCategory(categoryEntity);
            cs.setSize(size);
            return cs;
        }).toList();

        categoryEntity.setCategorySizes(categorySizeList);

        categoryRepository.save(categoryEntity);

        return "redirect:/admin/category";
    }


    @GetMapping("/updateCategory/{id}")
    public String getFormUpdateCategory(@PathVariable("id") Integer id, Model model) {
        Category categoryEntity = categoryRepository.findById(id).get();
        model.addAttribute("category", categoryEntity);
        return "admin/category/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "category") Category categoryEntity, Model model) {
        Optional<Category> existingCategory = categoryRepository.findByName(categoryEntity.getName());
        if (existingCategory.isPresent() && (existingCategory.get().getId() != categoryEntity.getId())) {
            model.addAttribute("error", "Danh mục này đã tồn tại.");
            model.addAttribute("category", categoryEntity);
            return "admin/category/update";
        }

        categoryRepository.save(categoryEntity);
        return "redirect:/admin/category/updateCategory/" + categoryEntity.getId();
    }

    @GetMapping("/deleteCategory/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Category category = categoryRepository.findById(id).get();
        category.setStatus(0);
        categoryRepository.save(category);
        return "redirect:/admin/category";
    }
}
