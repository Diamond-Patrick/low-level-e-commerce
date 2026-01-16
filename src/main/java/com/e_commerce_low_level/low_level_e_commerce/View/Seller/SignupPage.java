package com.e_commerce_low_level.low_level_e_commerce.View.Seller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.e_commerce_low_level.low_level_e_commerce.Entities.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entities.SellerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Seller.SellerRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Seller.SellerRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Seller.SellerService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Seller.SellerServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/signupseller")
public class SignupPage extends HttpServlet {

    private SellerRepo sellerRepo = new SellerRepoImpl();
    private SellerService sellerService = new SellerServiceImpl(sellerRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/Seller/signupPage.html");

        String string = Files.readString(of);

        resp.getWriter().println(string);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String shopName = req.getParameter("shopName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String noToko = req.getParameter("noToko");
        String jalan = req.getParameter("jalan");
        String kelurahan = req.getParameter("kelurahan");
        String kota = req.getParameter("city");
        String province = req.getParameter("province");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setOwnerName(name);
        sellerEntity.setShopName(shopName);
        sellerEntity.setEmail(email);
        sellerEntity.setPassword(password);
        sellerEntity.setAddress(new Address(noToko, jalan, kelurahan, kota, province));

        boolean insert = sellerService.insert(sellerEntity);

        if (insert) {
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect(req.getRequestURI());
        }
    }
}
