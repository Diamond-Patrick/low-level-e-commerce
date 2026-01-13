package com.e_commerce_low_level.low_level_e_commerce.View.Seller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.e_commerce_low_level.low_level_e_commerce.Entities.Address;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Order.OrderService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Order.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/delivery")
public class Deliverypage extends HttpServlet {

    private OrderRepo orderRepo = new OrderRepoImpl();
    private OrderService orderService = new OrderServiceImpl(orderRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/Seller/deliveryPage.html");

        String string = Files.readString(of);

        String deliveryCard = """
                <div class="col-md-4">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h5 class="card-title"><a>%s</a></h5>
                            <p class="card-text">Buyer : <b>%s</b></p>
                            <p class="card-text">Purchase Quantity :<b>%s</b></p>
                            <p class="card-text">Payment Method : <b>%s</b></p>
                            <p class="card-text">Address : <b>%s</b></p>
                        </div>
                    </div>
                </div>
                """;

        Cookie[] cookies = req.getCookies();
        StringBuilder stringBuilder = new StringBuilder();

        // get cookie named "id"
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {

                // find all data in order table
                orderService.findAll().forEach(t -> {

                    // focus on product column and find the seller
                    t.getKodeProduct().getSeller().forEach(seller -> {

                        // if the id seller equals to cookie value
                        if (seller.getId().equals(cookie.getValue())) {

                            Address address = t.getIdCustomer().getAddress();

                            String custAddress = address.getNoRumah() + ", " + address.getNamaJalan() +
                                    ", "
                                    + address.getKelurahan() + ", " +
                                    address.getKota() + ", " + address.getProvinsi();

                            stringBuilder.append(String.format(
                                    deliveryCard,
                                    t.getKodeProduct().getName(),
                                    t.getIdCustomer().getName(),
                                    t.getOrderQuantities().toString(),
                                    t.getPaymentMethod().toString(),
                                    custAddress));
                        }

                    });

                });
            }
        }

        String replace = string.replace("$cardDelivery", stringBuilder.toString());

        resp.getWriter().println(replace);
    }

}
