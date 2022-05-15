package mk.ukim.finki.databases.petshop.web.controller;

import mk.ukim.finki.databases.petshop.model.Role;
import mk.ukim.finki.databases.petshop.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.databases.petshop.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.databases.petshop.service.AuthService;
import mk.ukim.finki.databases.petshop.service.CustomerService;
import mk.ukim.finki.databases.petshop.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final CustomerService customerService;
    private final VendorService vendorService;

    public RegisterController(AuthService authService, CustomerService customerService, VendorService vendorService) {
        this.authService = authService;
        this.customerService = customerService;
        this.vendorService = vendorService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role,
                           Model model) {
        if (role.toString().equals("ROLE_ADMIN")) {
            try {
                this.vendorService.register(username, password, repeatedPassword, name, surname, role);
                return "redirect:/login";
            } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
                return "redirect:/register?error=" + exception.getMessage();
            }
        } else if (role.toString().equals("ROLE_USER")) {
            try {
                this.customerService.register(username, password, repeatedPassword, name, surname, role);
                return "redirect:/login";
            } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
                return "redirect:/register?error=" + exception.getMessage();
            }
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }
}


/*@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AdminService adminService;

    public RegisterController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String email,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role) {
        try {
            this.adminService.register(email, name, surname, role);
            return "redirect:/login";
        }
        catch (InvalidArgumentsException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}*/
