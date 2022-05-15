package mk.ukim.finki.databases.petshop.web.controller;

import com.lowagie.text.DocumentException;
import mk.ukim.finki.databases.petshop.model.Article;
import mk.ukim.finki.databases.petshop.model.ArticlePDFExporter;
import mk.ukim.finki.databases.petshop.service.ArticleService;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.descriptor.sql.DecimalTypeDescriptor;
import org.hibernate.type.descriptor.sql.NumericTypeDescriptor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String getArticlePage(@RequestParam(required = false) String error, Model model,
                                 @RequestParam(required = false) String searchText) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Article> articles = this.articleService.findAll();
        if (searchText != null) {
            articles = this.articleService.fullTextSearch(searchText);
        } else {
            model.addAttribute("articles", articles);
            model.addAttribute("bodyContent", "articles");
        }
        model.addAttribute("articles", articles);
        model.addAttribute("bodyContent", "articles");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteArticle(@PathVariable Long id) {
        this.articleService.deleteById(id);
        return "redirect:/articles";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addArticlePage(Model model) {
        model.addAttribute("bodyContent", "add-article");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editArticlePage(@PathVariable Long id, Model model) {
        if(this.articleService.findById(id).isPresent()){
            Article article = this.articleService.findById(id).get();
            model.addAttribute("article",article);
            model.addAttribute("bodyContent", "add-article");
            return "master-template";
        }
        return "redirect:/articles?error=ArticleNotFound";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveProduct(@RequestParam String name,
                              @RequestParam double price,
                              @RequestParam double quantity,
                              @RequestParam String description,
                              @RequestParam double serialNumber) {
        this.articleService.save(name, description, quantity, price, serialNumber);
        return "redirect:/articles";
    }

    @GetMapping("/articles/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=articles" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Article> listArticles = articleService.findAll();

        ArticlePDFExporter exporter = new ArticlePDFExporter(listArticles);
        exporter.export(response);

    }
}
