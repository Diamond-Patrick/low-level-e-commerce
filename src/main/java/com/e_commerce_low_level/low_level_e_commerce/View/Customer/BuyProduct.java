package com.e_commerce_low_level.low_level_e_commerce.View.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.e_commerce_low_level.low_level_e_commerce.Entities.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entities.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entities.OrderEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entities.PaymentMethod;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Customer.CustomerRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Customer.CustomerRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Customer.CustomerServcieImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Customer.CustomerService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Order.OrderService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Order.OrderServiceImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/purchasedetail")
public class BuyProduct extends HttpServlet {

    private ProductRepo productRepo = new ProductRepoImpl();
    private ProductService productService = new ProductServiceImpl(productRepo);

    private CustomerRepo customerRepo = new CustomerRepoImpl();
    private CustomerService customerService = new CustomerServcieImpl(customerRepo);

    private OrderRepo orderRepo = new OrderRepoImpl();
    private OrderService orderService = new OrderServiceImpl(orderRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/Customer/buyProduct.html");

        String idProduct = req.getParameter("idProduct");

        String string = Files.readString(of);

        String paymentMethodHtml = """
                <div class="max-w-lg h-auto mx-auto mt-10 border-2 border-cyan-300 shadow-2xl shadow-sky-500 p-5">
                <div class="mt-4">
                    <h4 class="font-default mb-1">Nama Produk :</h4>
                    <h3 class="font-semibold mb-1">%s</h3>
                </div>
                <div class="mt-4">
                    <h4 class="font-default mb-1">Harga per produk :</h4>
                    <h3 class="font-semibold mb-1">%s</h3>
                </div>
                <div class="mt-4">
                    <h4 class="font-default mb-1">Alamat :</h4>
                    <h3 class="font-semibold mb-1">%s</h3>
                </div>
                """;

        StringBuilder stringBuilder = new StringBuilder();

        productService.findAll().forEach(t -> {
            for (Cookie cookies : req.getCookies()) {
                if (cookies.getName().equals("id") && t.getKodeProduct().equals(idProduct)) {

                    CustomerEntity customerEntity = customerService.find(cookies.getValue());
                    Address custAddress = customerEntity.getAddress();

                    String fullAddress = custAddress.getNoRumah() + ", " + custAddress.getNamaJalan() + ", " +
                            custAddress.getKelurahan() + ", " + custAddress.getKota() + ", "
                            + custAddress.getProvinsi();

                    stringBuilder.append(String.format(
                            paymentMethodHtml,
                            t.getName(),
                            t.getHarga().toString(),
                            fullAddress));
                }
            }
        });

        String replace = string.replace("$optionPayment", stringBuilder);
        resp.getWriter().println(replace);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quantity = req.getParameter("quantity");
        String category = req.getParameter("category");

        String idProduct = req.getParameter("idProduct");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderQuantities(Integer.valueOf(quantity));
        orderEntity.setPaymentMethod(PaymentMethod.valueOf(category));

        for (Cookie cookies : req.getCookies()) {
            if (cookies.getName().equals("id")) {
                boolean insert = orderService.insert(orderEntity, idProduct, cookies.getValue());

                if (insert) {
                    resp.sendRedirect("/products");
                } else {
                    resp.sendRedirect(req.getRequestURI());
                }
            }
        }

    }
}
