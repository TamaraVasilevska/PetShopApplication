package mk.ukim.finki.databases.petshop.web.controller;

import mk.ukim.finki.databases.petshop.model.Customer;
import mk.ukim.finki.databases.petshop.model.Role;
import mk.ukim.finki.databases.petshop.model.Vendor;
import mk.ukim.finki.databases.petshop.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.databases.petshop.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.databases.petshop.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.databases.petshop.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @PostMapping()
    public String login(HttpServletRequest request, Model model, @RequestParam(required = true) Role role) {

        if (role.toString().equals("ROLE_ADMIN")) {
            try{
                Vendor vendor = this.authService.loginVendor(request.getParameter("username"),
                        request.getParameter("password"));
                request.getSession().setAttribute("vendor", vendor);
                model.addAttribute("bodyContent","home");
                return "master-template";
            }
            catch (InvalidUserCredentialsException exception) {
                model.addAttribute("hasError", true);
                model.addAttribute("error", exception.getMessage());
                return "redirect:/login?error=" + exception.getMessage();
            }
        } else if (role.toString().equals("ROLE_USER")) {
            try{
                Customer customer = this.authService.loginCustomer(request.getParameter("username"),
                        request.getParameter("password"));
                request.getSession().setAttribute("customer", customer);
                model.addAttribute("bodyContent","home");
                return "master-template";
            }
            catch (InvalidUserCredentialsException exception) {
                model.addAttribute("hasError", true);
                model.addAttribute("error", exception.getMessage());
                return "redirect:/login?error=" + exception.getMessage();
            }
        }
        model.addAttribute("bodyContent","login");
        return "master-template";



        //Customer customer = null;
       //Vendor vendor = null;
       /* try{
            Customer customer = this.authService.loginCustomer(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", customer);
            return "home";
        }
        catch (InvalidUserCredentialsException exception) {
            try{
                Vendor vendor = this.authService.loginVendor(request.getParameter("username"),
                        request.getParameter("password"));
                request.getSession().setAttribute("user", vendor);
                return "home";
            }
            catch (InvalidUserCredentialsException exception2){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception2.getMessage());
            return "login";
            }
        }*/
    }
}
