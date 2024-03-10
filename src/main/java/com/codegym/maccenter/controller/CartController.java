package com.codegym.maccenter.controller;

import com.codegym.maccenter.model.CartItem;
import com.codegym.maccenter.model.Product;
import com.codegym.maccenter.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.DecimalFormat;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    DecimalFormat totalFormat = new DecimalFormat("#,###");

    @GetMapping("/cart")
    public String getCar(Model model){
        model.addAttribute("cartCount", CartItem.cart.size());
//        model.addAttribute("total", CartItem.cart.stream().mapToLong(Product::getPrice).sum());
        model.addAttribute("total", totalFormat.format(CartItem.cart.stream().mapToLong(Product::getPrice).sum()));
        model.addAttribute("cart", CartItem.cart);
        return "customer_cart_template";
    }//page cart


    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        CartItem.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        CartItem.cart.remove(index);
        return "redirect:/cart";
    } // delete 1 product

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("cartCount", CartItem.cart.size());
        model.addAttribute("total", totalFormat.format(CartItem.cart.stream().mapToLong(Product::getPrice).sum()));
        //model.addAttribute("cart", CartItem.cart);
        return "customer_detailAddressBuy_template";
    } // checkout totalPrice
}
