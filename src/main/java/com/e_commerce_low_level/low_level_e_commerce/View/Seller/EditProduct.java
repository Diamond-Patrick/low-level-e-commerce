package com.e_commerce_low_level.low_level_e_commerce.View.Seller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = "/editproduct")
@MultipartConfig
public class EditProduct extends HttpServlet {

    private ProductRepo productRepo = new ProductRepoImpl();
    private ProductService productService = new ProductServiceImpl(productRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path of = Path.of("src/main/resources/html/Seller/editProduct.html");

        String kodeProduct = req.getParameter("kodeproduct");

        String string = Files.readString(of);

        String forms = """
                 <div class="mt-4">
                    <h4 class="font-semibold mb-1">Nama Barang</h4>
                    <input type="text" placeholder="%s" name="namaBarang" class="w-full border-2 border-cyan-500 shadow-lg
                     pl-3 pb-1 rounded
                    placeholder:text-slate-500">
                </div>
                <div class="mt-4">
                    <h4 class="font-semibold mb-1">Harga per produk</h4>
                    <input type="number" placeholder="%s" name="harga" class="w-full pb-1 border-2 border-cyan-500 shadow-lg pl-3
                   border placeholder:text-slate-500 rounded">
                </div>
                <div class="mt-4">
                    <h4 class="font-semibold mb-1">Stock</h4>
                    <input type="number" placeholder="%s" name="stock" class="w-full border-2 border-cyan-500 shadow-lg
                     pl-3 pb-1 rounded
                    placeholder:text-slate-500">
                </div>
                <div class="mt-4">
                    <h4 class="font-semibold mb-1">Description</h4>
                    <textarea type="text" placeholder="%s" name="description"
                     class="w-full h-auto border-2 border-cyan-500 shadow-lg
                     pl-3 pb-1 rounded
                    placeholder:text-slate-500"
                    oninput="this.style.height='auto';this.style.height=this.scrollHeight+'px'"></textarea>
                </div>
                <div class="mt-4">
                    <h4 class="font-semibold mb-1">Gambar</h4>
                    <img src="data:image/*;base64,%s" class="card-img-top" alt="...">
                    <input type="file" name="gambar" accept="image/*" class="w-full
                     pl-3 pb-1 rounded mt-5
                    placeholder:text-slate-500">
                </div>
                <div class="mt-7">
                    <button class="w-20 h-10 block mx-auto border rounded-full
                     bg-cyan-500 text-white font-semibold hover:bg-cyan-600
                     focus:bg-cyan-700 focus:ring-4 focus:ring-cyan-200
                     hover:cursor-pointer" type="submit">Edit</button>
                     <input type="hidden" name="kodeproduct" value=%s>
                </div>
                """;

        StringBuilder stringBuilder = new StringBuilder();

        productService.findAll().forEach(t -> {
            if (t.getKodeProduct().equals(kodeProduct)) {
                stringBuilder.append(String.format(
                        forms,
                        t.getName(),
                        t.getHarga().toString(),
                        t.getStock().toString(),
                        t.getDescription(),
                        Base64.getEncoder().encodeToString(t.getGambar()),
                        t.getKodeProduct()));
            }
        });

        String replace = string.replace("$formDetail", stringBuilder);

        resp.getWriter().println(replace);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String kodeBarang = req.getParameter("kodeproduct");
        String namaBarang = req.getParameter("namaBarang");
        String harga = req.getParameter("harga");
        String stock = req.getParameter("stock");
        String description = req.getParameter("description");
        Part gambar = req.getPart("gambar");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(namaBarang);
        productEntity.setDescription(description);

        if (!harga.trim().isBlank()) {
            productEntity.setHarga(Double.parseDouble(harga));
        }

        if (!stock.trim().isBlank()) {
            productEntity.setStock(Integer.parseInt(stock));
        }

        // if gambar is not null,
        if (gambar != null && gambar.getSize() > 0) {
            byte[] gambarBytes = gambar.getInputStream().readAllBytes();
            productEntity.setGambar(gambarBytes);
        }

        boolean update = productService.update(kodeBarang, productEntity);

        if (update == true) {
            resp.sendRedirect("/productselling");
        } else {
            resp.sendRedirect(req.getRequestURI());
        }
    }
}
