package mk.ukim.finki.databases.petshop.web.controller;

import mk.ukim.finki.databases.petshop.model.Customer;
import mk.ukim.finki.databases.petshop.model.Order;
import mk.ukim.finki.databases.petshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrderPage(@RequestParam(required = false) String error,
                               HttpServletRequest req,
                               Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        Order o = this.orderService.getActiveOrder(username);
        model.addAttribute("articles", this.orderService.listAllArticlesInOrder(o.getIdOrder()));
        model.addAttribute("bodyContent", "order");
        return "master-template";
    }

    @PostMapping("add-article/{id}")
    public String addArticleToOrder(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try{
            Customer customer = (Customer) authentication.getPrincipal();
            this.orderService.addArticleToOrder(customer.getUsername(), id);
            return "redirect:/articles";
        }catch (RuntimeException exception) {
            return "redirect:/order?error=" + exception.getMessage();
        }
    }


    @PostMapping("/remove/{articleId}")
    public String removeArticleFromOrder(@PathVariable Long articleId, HttpServletRequest req, Authentication authentication) {
        Customer customer = (Customer) authentication.getPrincipal();
        this.orderService.removeFromOrder(customer.getUsername(), articleId);
        return "redirect:/order";
    }

    @PostMapping("/sum")
    public String sumOfItems()
    {
        this.orderService.getFullPriceOfOrder(1L);
        return "redirect:/order";
    }

    @RequestMapping(value="/total", method=GET)
    @ResponseBody
    public String foo() {
        return Double.toString(orderService.getFullPriceOfOrder(1L));
    }

    @GetMapping("/buy")
    public String getBuyPage(HttpServletRequest req, Authentication authentication,Model model)
    {
        Customer customer = (Customer) authentication.getPrincipal();
        model.addAttribute("bodyContent", "orderSuccess");
        this.orderService.removeAllFromOrder(customer.getUsername());
        return "master-template";
    }

}
