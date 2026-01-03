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

@WebServlet(urlPatterns = "/editprofile")
public class EditPage extends HttpServlet {

    private CustomerRepo customerRepo = new CustomerRepoImpl();
    private CustomerService customerService = new CustomerServcieImpl(customerRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Path of = Path.of("src/main/resources/html/Customer/editPage.html");

        String string = Files.readString(of);

        String custDetail = """
                 <div class="max-w-lg h-auto mx-auto mt-10 border-2 border-cyan-300 shadow-2xl shadow-sky-500 p-5">
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">Nama</h4>
                       <input type="text" placeholder="%s" name="name" class="w-full border-2 border-sky-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">Email</h4>
                       <input type="text" placeholder="%s" name="email" class="w-full border-2 border-sky-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">Password</h4>
                       <input type="password" name="password" class="w-full pb-1 border-2 border-sky-200 shadow-lg pl-3
                      border placeholder:text-slate-500 rounded">
                   </div>
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">No. Rumah</h4>
                       <input type="text" placeholder="%s" name="noRumah" class="w-full border-2 border-sky-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">Jalan</h4>
                       <input type="text" placeholder="%s" name="jalan" class="w-full border-2 border-sky-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">Kelurahan</h4>
                       <input type="text" placeholder="%s" name="kelurahan" class="w-full border-2 border-sky-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                    <div class="mt-4">
                       <h4 class="font-semibold mb-1">City</h4>
                       <input type="text" placeholder="%s" name="city" class="w-full border-2 border-sky-200
                        shadow-lg pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                    <div class="mt-4">
                       <h4 class="font-semibold mb-1">Province</h4>
                       <input type="text" placeholder="%s" name="province" class="w-full border-2 border-sky-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                """;

        StringBuilder stringBuilder = new StringBuilder();
        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                CustomerEntity customerEntity = customerService.find(cookie.getValue());
                Address custAddress = customerEntity.getAddress();

                stringBuilder.append(String.format(
                        custDetail,
                        customerEntity.getName(),
                        customerEntity.getEmail(),
                        custAddress.getNoRumah(),
                        custAddress.getNamaJalan(),
                        custAddress.getKelurahan(),
                        custAddress.getKota(),
                        custAddress.getProvinsi()));
            }
        }

        String replace = string.replace("$detailCustomer", stringBuilder);

        resp.getWriter().println(replace);

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

        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                boolean update = customerService.update(cookie.getValue(), customerEntity);

                if (update) {
                    resp.sendRedirect("/profile");
                } else {
                    resp.sendRedirect(req.getRequestURI());
                }
            }
        }

    }
}
