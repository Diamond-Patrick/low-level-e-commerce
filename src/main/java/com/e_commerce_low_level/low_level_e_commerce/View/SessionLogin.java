package com.e_commerce_low_level.low_level_e_commerce.View;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/login")
public class SessionLogin extends HttpServlet {

    private CustomerRepo customerRepo = new CustomerRepoImpl();
    private CustomerService customerService = new CustomerServcieImpl(customerRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/login.html");

        String string = Files.readString(of);

        resp.getWriter().println(string);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        CustomerEntity result = customerService.findByEmailAndPassword(email, password);

        if (result != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("id", result.getIdCustomer());

            Cookie cookie = new Cookie("id", result.getIdCustomer());
            resp.addCookie(cookie);

            resp.sendRedirect("/customerpage");
        } else if (result == null) {
            resp.sendRedirect(req.getRequestURI()); // same page
        }
    }
}
