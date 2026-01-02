package com.e_commerce_low_level.low_level_e_commerce.View.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.e_commerce_low_level.low_level_e_commerce.Entity.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Customer.CustomerRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Customer.CustomerRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Customer.CustomerServcieImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Customer.CustomerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profile")
public class ProfilePage extends HttpServlet {

    private CustomerRepo customerRepo = new CustomerRepoImpl();
    private CustomerService customerService = new CustomerServcieImpl(customerRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/Customer/profilePage.html");

        String string = Files.readString(of);

        String customerData = """
                <div class="mt-4">
                    <h4 class="font-default mb-1">Id Customer :</h4>
                    <h3 class="font-semibold mb-1">%s</h3>
                </div>
                <div class="mt-4">
                    <h4 class="font-default mb-1">Nama :</h4>
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

        StringBuilder stringBuilder = new StringBuilder();

        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                CustomerEntity customerEntity = customerService.find(cookie.getValue());

                Address custAddress = customerEntity.getAddress();
                String fullAddress = custAddress.getNoRumah() + ", " + custAddress.getNamaJalan() + ", "
                        + custAddress.getKelurahan() + ", " + custAddress.getKota() + ", " + custAddress.getProvinsi();

                stringBuilder.append(String.format(
                        customerData,
                        customerEntity.getIdCustomer(),
                        customerEntity.getName(),
                        customerEntity.getEmail(),
                        customerEntity.getPassword(),
                        fullAddress));
            }
        }

        String replace = string.replace("$custDetail", stringBuilder);

        resp.getWriter().println(replace);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                boolean remove = customerService.remove(cookie.getValue());

                if (remove) {
                    resp.sendRedirect("/login");

                } else {
                    resp.sendRedirect(req.getRequestURI());
                }
            }
        }

    }
}
