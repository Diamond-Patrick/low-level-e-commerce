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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profilecust")
public class PorfilePage extends HttpServlet {

    private SellerRepo sellerRepo = new SellerRepoImpl();
    private SellerService sellerService = new SellerServiceImpl(sellerRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/Seller/profilePage.html");

        String string = Files.readString(of);

        String dataCustomer = """
                <div class="mt-4">
                    <h4 class="font-default mb-1">Id Seller :</h4>
                    <h3 class="font-semibold mb-1">%s</h3>
                </div>
                <div class="mt-4">
                    <h4 class="font-default mb-1">Owner Name :</h4>
                    <h3 class="font-semibold mb-1">%s</h3>
                </div>
                <div class="mt-4">
                    <h4 class="font-default mb-1">Shop Name :</h4>
                    <h3 class="font-semibold mb-1">%s</h3>
                </div>
                <div class="mt-4">
                    <h4 class="font-default mb-1">Email :</h4>
                    <h3 class="font-semibold mb-1">%s</h3>
                </div>
                <div class="mt-4">
                    <h4 class="font-default mb-1">Password :</h4>
                    <h3 class="font-semibold mb-1">%s</h3>
                </div>
                <div class="mt-4">
                    <h4 class="font-default mb-1">Alamat :</h4>
                   <h3 class="font-semibold mb-1">%s</h3>
                </div>
                """;

        Cookie[] cookies = req.getCookies();
        StringBuilder stringBuilder = new StringBuilder();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {

                SellerEntity sellerEntity = sellerService.find(cookie.getValue());

                Address address = sellerEntity.getAddress();
                String customerAdd = address.getNoRumah() + ", " + address.getNamaJalan() + ", "
                        + address.getKelurahan() + ", "
                        + address.getKota() + ", " + address.getProvinsi();

                stringBuilder.append(String.format(
                        dataCustomer,
                        sellerEntity.getId(),
                        sellerEntity.getOwnerName(),
                        sellerEntity.getShopName(),
                        sellerEntity.getEmail(),
                        sellerEntity.getPassword(),
                        customerAdd));

            }
        }

        String replace = string.replace("$dataCust", stringBuilder);

        resp.getWriter().println(replace);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {

                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setValue("");
                resp.addCookie(cookie);

                resp.sendRedirect("/login");
            }
        }

    }
}
