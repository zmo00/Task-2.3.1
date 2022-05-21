package task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task.models.User;
import task.services.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("userList", userService.getUsers());
        return "index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("newUser") User user) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("newUser") User user) {
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("userShow", userService.read(id));
        return "showUser";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("userShow", userService.read(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("userShow") User user, @PathVariable("id") long id) {
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(userService.read(id));
        return "redirect:/users";
    }
}
