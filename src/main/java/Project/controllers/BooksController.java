package Project.controllers;

import Project.dao.BookDAO;
import Project.dao.PersonDAO;
import Project.model.Book;
import Project.model.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private PersonDAO personDAO;
    private BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO){
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @ModelAttribute("selectedPerson") Person person, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("person", bookDAO.bookAccess(id));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/makeFree")
    public String makeFree(@PathVariable("id") int id){
        bookDAO.makeFree(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/add")
    public String setAPerson(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookDAO.setAPerson(person.getId(), id);
        return "redirect:/books/{id}";
    }
}
