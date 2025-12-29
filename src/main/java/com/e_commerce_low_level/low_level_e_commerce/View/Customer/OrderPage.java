package com.e_commerce_low_level.low_level_e_commerce.View.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

@WebServlet(urlPatterns = "/order")
public class OrderPage extends HttpServlet {

    private OrderRepo orderRepo = new OrderRepoImpl();
    private OrderService orderService = new OrderServiceImpl(orderRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/Customer/orderPage.html");

        String string = Files.readString(of);

        String cardProduct = """
                <div class="p-6 max-w-sm mx-auto my-8 bg-white rounded-xl shadow-lg shadow-slate-500 flex items-center space-x-4">
                    <div>
                        <div class="text-xl font-medium text-black">%s</div>
                        <p class="text-slate-500"><a class="font-semibold text-black">Price:</a> %s</p>
                        <p class="text-slate-500"><a class="font-semibold text-black">Purchase On:</a> %s</p>
                        <div>
                        <form action="/order" method="post"">
                            <input type="hidden" name="idOrder" value="%s">
                            <button class="w-auto h-10 block mx-auto mt-1 border rounded-full
                                    bg-sky-500 text-white font-semibold hover:bg-sky-600
                                    focus:bg-sky-700 focus:ring-4 focus:ring-sky-200
                                    hover:cursor-pointer" type="submit"><a class="mx-3">Product have been received</a></button>
                        </form>
                        </div>
                    </div>
                </div>
                """;

        Cookie[] cookies = req.getCookies();

        StringBuilder stringBuilder = new StringBuilder();

        orderService.findAll().forEach(t -> {
            for (Cookie cookie : cookies) {
                if (t.getIdCustomer().getIdCustomer().equals(cookie.getValue())) {

                    stringBuilder.append(String.format(
                            cardProduct,
                            t.getKodeProduct().getName(),
                            t.getKodeProduct().getHarga().toString(),
                            t.getPurchaceDate().toString(),
                            t.getId().toString()));

                }
            }
        });

        String replace = string.replace("$cardProduct", stringBuilder);

        resp.getWriter().println(replace);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idOrder = req.getParameter("idOrder");

        boolean remove = orderService.remove(Integer.parseInt(idOrder));

        if (remove) {
            resp.sendRedirect("/products");
        } else if (!remove) {
            String popup = """
                    <script>alert("Fail to proccess the product");</script>
                    """;

            resp.getWriter().println(popup);
        }

    }

}
