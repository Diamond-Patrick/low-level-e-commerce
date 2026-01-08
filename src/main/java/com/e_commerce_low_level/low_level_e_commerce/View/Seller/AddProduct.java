package com.e_commerce_low_level.low_level_e_commerce.View.Seller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = "/addproduct")
@MultipartConfig
public class AddProduct extends HttpServlet {

    private ProductRepo productRepo = new ProductRepoImpl();
    private ProductService productService = new ProductServiceImpl(productRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/Seller/addProduct.html");

        String string = Files.readString(of);

        resp.getWriter().println(string);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String kodeBarang = req.getParameter("kodeBarang");
        String namaBarang = req.getParameter("namaBarang");
        String harga = req.getParameter("harga");
        String stock = req.getParameter("stock");
        String description = req.getParameter("description");
        Part gambar = req.getPart("gambar");

        byte[] gambarByte = gambar.getInputStream().readAllBytes();

        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct(kodeBarang);
        productEntity.setName(namaBarang);
        productEntity.setHarga(Double.parseDouble(harga));
        productEntity.setStock(Integer.parseInt(stock));
        productEntity.setDescription(description);
        productEntity.setGambar(gambarByte);

        boolean insert = productService.insert(productEntity);

        if (insert) {

            Cookie[] cookies = req.getCookies();

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {

                    productService.insertOmset(kodeBarang, cookie.getValue());
                }
            }
            resp.sendRedirect("/productselling");

        } else {
            resp.sendRedirect(req.getRequestURI());
        }

    }
}
