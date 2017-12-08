package pw.yuuh.genuinenews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pw.yuuh.genuinenews.domain.Category;
import pw.yuuh.genuinenews.domain.NewsItem;
import pw.yuuh.genuinenews.repository.AuthorRepository;
import pw.yuuh.genuinenews.repository.CategoryRepository;
import pw.yuuh.genuinenews.repository.NewsItemRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsItemController {
    @Autowired
    private NewsItemRepository newsItemRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String listNews(Model model) {
        model.addAttribute("news", newsItemRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("newnews", new NewsItem());
        return "news";
    }

    @Transactional
    @PostMapping("/")
    //public String addNewsItem(@RequestParam String title, @RequestParam String lead, @RequestParam String image, @RequestParam String content) {
    public String addNewsItem(@Valid NewsItem n, BindingResult bindingResult) {
        System.out.println(bindingResult);
        System.out.println(n);
        n.setPublishedAt(LocalDateTime.now());
        newsItemRepository.save(n);
        return "redirect:/news/";
    }
}
