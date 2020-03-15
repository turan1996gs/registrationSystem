package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "register";
    }

    @PostMapping("/users")
    public @ResponseBody RedirectView registerSubmit(
            @RequestParam String name, @RequestParam String email, @RequestParam String surname, @RequestParam String student_no) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setSurname(surname);
        u.setStudent_no(student_no);
        userRepository.save(u);

        return new RedirectView("/users");
    }

    @GetMapping("/users/{id}/delete")
    public @ResponseBody RedirectView deleteUser(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
        return new RedirectView("/users");
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") Integer id,Model m){
        User u = userRepository.findById(id).get();
        m.addAttribute("user",u);
        return "edit";
    }


    @PostMapping("/users/{id}/update")
    public @ResponseBody RedirectView updateUser(@PathVariable("id") Integer id, @RequestParam String student_no,@RequestParam String name,@RequestParam String surname,@RequestParam String email){
        User u = userRepository.findById(id).get();
        u.setStudent_no(student_no);
        u.setName(name);
        u.setSurname(surname);
        u.setEmail(email);
        userRepository.save(u);

        return new RedirectView("/users");
    }

}