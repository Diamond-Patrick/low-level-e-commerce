package com.e_commerce_low_level.low_level_e_commerce.View.Seller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/productselling")
public class MainPage extends HttpServlet {

    private ProductRepo productRepo = new ProductRepoImpl();
    private ProductService productService = new ProductServiceImpl(productRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Path of = Path.of("src/main/resources/html/Seller/mainPage.html");

        String string = Files.readString(of);

        String productTable = """
                <tbody>
                    <tr>
                    <th scope="row">%s</th>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>
                        <a href="/editproduct?kodeproduct=%s"
                            class="btn btn-primary px-4 py-1">
                            Edit
                        </a>
                    </td>
                    </tr>
                </tbody>
                """;

        Cookie[] cookies = req.getCookies();

        StringBuilder stringBuilder = new StringBuilder();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {

                productService.findAll().forEach(t -> {
                    t.getSeller().forEach(s -> {
                        if (s.getId().equals(cookie.getValue())) {
                            stringBuilder.append(String.format(
                                    productTable,
                                    t.getKodeProduct(),
                                    t.getName(),
                                    t.getHarga().toString(),
                                    t.getStock().toString(),
                                    t.getKodeProduct()));
                        }
                    });
                });
            }
        }

        String replace = string.replace("$product", stringBuilder);

        resp.getWriter().println(replace);

    }

}
