package com.codegym.maccenter.model;

import java.util.ArrayList;
import java.util.List;

public class CartItem {
    //tao bien toan cuc
    public static List<Product> cart;

    static {
        cart = new ArrayList<>();
    }

}
