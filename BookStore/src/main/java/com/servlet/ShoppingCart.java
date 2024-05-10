package com.servlet;

import java.util.HashMap;

import jakarta.servlet.http.HttpSession;

public class ShoppingCart {

	 // Method to add an item to the cart
    public static void addToCart(HttpSession session, String b_name,int quantity) {
        // Retrieve the cart from the session, or create a new one if it doesn't exist
        HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        
        // Add the item to the cart or increment its quantity if it already exists
        if (cart.containsKey(b_name)) {
            cart.put(b_name, cart.get(b_name) + 1); // Increment quantity
        } else {
            cart.put(b_name, 1); // Add new item to cart with quantity 1
        }
    }
}
