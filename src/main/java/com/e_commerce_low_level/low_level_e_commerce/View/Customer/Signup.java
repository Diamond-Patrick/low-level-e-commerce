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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/signup")
public class Signup extends HttpServlet {

    private CustomerRepo customerRepo = new CustomerRepoImpl();
    private CustomerService customerService = new CustomerServcieImpl(customerRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/Customer/signup.html");

        String string = Files.readString(of);

        resp.getWriter().println(string);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String noRumah = req.getParameter("noRumah");
        String jalan = req.getParameter("jalan");
        String kelurahan = req.getParameter("kelurahan");
        String kota = req.getParameter("city");
        String province = req.getParameter("province");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(name);
        customerEntity.setEmail(email);
        customerEntity.setPassword(password);
        customerEntity.setAddress(new Address(noRumah, jalan, kelurahan, kota, province));

        boolean insert = customerService.insert(customerEntity);

        if (insert) {

            resp.sendRedirect("/login"); // if success create new account, redirect to login page
        } else {
            resp.sendRedirect(req.getRequestURI());
        }

    }

}
