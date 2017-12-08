package pw.yuuh.genuinenews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pw.yuuh.genuinenews.domain.Author;
import pw.yuuh.genuinenews.repository.AuthorRepository;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/")
    private String listAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }

    @Transactional
    @PostMapping("/")
    private String addAuthor(@RequestParam String name) {
        final Author a = new Author();
        a.setName(name);
        authorRepository.save(a);
        return "redirect:/authors/";
    }
}
