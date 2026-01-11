package com.e_commerce_low_level.low_level_e_commerce.View.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

import com.e_commerce_low_level.low_level_e_commerce.Entities.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/products")
public class MainPage extends HttpServlet {

    private ProductRepo productRepo = new ProductRepoImpl();
    private ProductService productService = new ProductServiceImpl(productRepo);

    private String renderingCardProduct(List<ProductEntity> products) throws IOException {

        Path of = Path.of("src/main/resources/html/Customer/mainPage.html");

        String html = Files.readString(of);

        String card = """
                <div class="col-md-4">
                    <div class="card" style="width: 18rem;">
                        <img src="data:image/png;base64,%s" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">%s</h5>
                            <p class="card-text">%s</p>
                            <a href="/purchasedetail?idProduct=%s" class="btn btn-primary">$%s</a>
                        </div>
                    </div>
                </div>
                """;

        StringBuilder stringBuilder = new StringBuilder();

        products.forEach(t -> {
            stringBuilder.append(String.format(card,
                    Base64.getEncoder().encodeToString(t.getGambar()),
                    t.getName(),
                    t.getDescription(),
                    t.getKodeProduct(),
                    t.getHarga()));
        });

        return html.replace("$test", stringBuilder.toString());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String renderingCardProduct = renderingCardProduct(productService.findAll());

        resp.getWriter().println(renderingCardProduct);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String findProduct = req.getParameter("findProduct");

        resp.getWriter().println(renderingCardProduct(productService.findByName(findProduct)));

    }
}
