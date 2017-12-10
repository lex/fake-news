package pw.yuuh.genuinenews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pw.yuuh.genuinenews.domain.Author;
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
        Pageable pageable = PageRequest.of(0, 100, Sort.Direction.DESC, "timesRead");
        model.addAttribute("news", newsItemRepository.findAll(pageable));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("newnews", new NewsItem());
        return "news";
    }

    @GetMapping("/{id}")
    public String showNewsItem(Model model, @PathVariable Long id) {
        NewsItem n = newsItemRepository.getOne(id);
        n.setTimesRead(n.getTimesRead() + 1);
        newsItemRepository.save(n);
        model.addAttribute("newsItem", n);
        return "newsitem";
    }

    @Transactional
    @PostMapping("/")
    public String addNewsItem(@Valid NewsItem n, BindingResult bindingResult) {
        n.setPublishedAt(LocalDateTime.now());
        n = newsItemRepository.save(n);

        for (Author a : n.getAuthors()) {
            a.getNewsItems().add(n);
        }

        for (Category c : n.getCategories()) {
            c.getNewsItems().add(n);
        }

        return "redirect:/news/" + n.getId();
    }
}
