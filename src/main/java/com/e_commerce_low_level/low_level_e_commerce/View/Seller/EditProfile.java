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

@WebServlet(urlPatterns = "/editseller")
public class EditProfile extends HttpServlet {

    private SellerRepo sellerRepo = new SellerRepoImpl();
    private SellerService sellerService = new SellerServiceImpl(sellerRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/Seller/editProfile.html");

        String string = Files.readString(of);

        String sellerData = """
                <div class="max-w-lg h-auto mx-auto mt-10 border-2 border-cyan-300 shadow-2xl shadow-cyan-500 p-5">
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">Owner Name</h4>
                       <input type="text" placeholder="%s" name="name" class="w-full border-2 border-cyan-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                    <div class="mt-4">
                       <h4 class="font-semibold mb-1">Shop Name</h4>
                       <input type="text" placeholder="%s" name="shopName" class="w-full border-2 border-cyan-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">Email</h4>
                       <input type="text" placeholder="%s" name="email" class="w-full border-2 border-cyan-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">Password</h4>
                       <input type="password" name="password" class="w-full pb-1 border-2 border-cyan-200 shadow-lg pl-3
                      border placeholder:text-slate-500 rounded">
                   </div>
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">No. Rumah</h4>
                       <input type="text" placeholder="%s" name="noRumah" class="w-full border-2 border-cyan-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">Jalan</h4>
                       <input type="text" placeholder="%s" name="jalan" class="w-full border-2 border-cyan-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                   <div class="mt-4">
                       <h4 class="font-semibold mb-1">Kelurahan</h4>
                       <input type="text" placeholder="%s" name="kelurahan" class="w-full border-2 border-cyan-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                    <div class="mt-4">
                       <h4 class="font-semibold mb-1">City</h4>
                       <input type="text" placeholder="%s" name="city" class="w-full border-2 border-cyan-200
                        shadow-lg pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                    <div class="mt-4">
                       <h4 class="font-semibold mb-1">Province</h4>
                       <input type="text" placeholder="%s" name="province" class="w-full border-2 border-cyan-200 shadow-lg
                        pl-3 pb-1 rounded
                       placeholder:text-slate-500">
                   </div>
                """;

        Cookie[] cookies = req.getCookies();
        StringBuilder stringBuilder = new StringBuilder();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {

                SellerEntity sellerEntity = sellerService.find(cookie.getValue());
                Address sellerAddress = sellerEntity.getAddress();

                stringBuilder.append(String.format(
                        sellerData,
                        sellerEntity.getOwnerName(),
                        sellerEntity.getShopName(),
                        sellerEntity.getEmail(),
                        sellerAddress.getNoRumah(),
                        sellerAddress.getNamaJalan(),
                        sellerAddress.getKelurahan(),
                        sellerAddress.getKota(),
                        sellerAddress.getProvinsi()));

            }
        }

        String replace = string.replace("$dataSeller", stringBuilder);

        resp.getWriter().println(replace);
    }
}
