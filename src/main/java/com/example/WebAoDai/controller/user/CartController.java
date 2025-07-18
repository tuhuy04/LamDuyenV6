package com.example.WebAoDai.controller.user;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.example.WebAoDai.entity.*;
import com.example.WebAoDai.service.CategorySizeService;
import com.example.WebAoDai.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.WebAoDai.service.CartService;
import com.example.WebAoDai.service.ProductService;

@Controller
@RequestMapping("/client/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    SizeService sizeService;

    @Autowired
    ProductService productService;

    @Autowired
    CategorySizeService categorySizeService;

    @Autowired
    HttpSession session;

    @GetMapping("")
    public String CartView(Model model) throws Exception {
        User user = (User) session.getAttribute("acc");
        if (user == null) {
            session.setAttribute("NoSignIn", "Vui lòng đăng nhập trước khi thực hiện thao tác");
            return "redirect:/login";
        } else {
            List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
            int Total = 0;
            for (Cart y : listCart) {
                Total = Total + y.getCount() * y.getProduct().getPrice();
            }
            if (listCart != null) {
                model.addAttribute("Total", Total);
                model.addAttribute("listCart", listCart);
                session.setAttribute("listCart", listCart);
                session.setAttribute("Total", Total);
            }
            return "cart";
        }
    }

    @GetMapping("/deleteCart/{id}")
    public String GetDeleteCart(@PathVariable int id, Model model, HttpServletRequest request) throws Exception {
        User user = (User) session.getAttribute("acc");
        if (user == null) {
            return "redirect:/login";
        } else {
            System.out.println(id);
            cartService.deleteById(id);
            session.setAttribute("CartDelete", id);
            List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
            session.setAttribute("countCart", listCart.size());
            return "redirect:/client/cart";
        }
    }

    @PostMapping("/updateCart")
    public String UpdateCart(HttpServletRequest request, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        int i = 0;
        for (Cart o : listCart) {
            String countParameter = request.getParameter("count" + i);
            int count = Integer.parseInt(countParameter);
            String sizeParameter = request.getParameter("size" + i);
            int sizeId = Integer.parseInt(sizeParameter);
            o.setCount(count);
            Size size = sizeService.findById(sizeId);
            o.setSize(size);
            i++;
        }

        for (Cart o : listCart) {
            cartService.saveCart(o);
        }
        return "redirect:/client/cart";
    }

    @GetMapping("/addToCart/{id}/{sizeId}")
    public String addToCart(@PathVariable int id, @PathVariable int sizeId, Model model, HttpServletRequest request) {
        User user = (User) session.getAttribute("acc");
        if (user == null) {
            session.setAttribute("AddToCartErr", "Please signin before proceeding to cart");
            return "redirect:/login";
        } else {
            List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
            Product product = productService.getProductById(id);
            int cartCount = 0;
            for (Cart cart : listCart) {
                if (cart.getProduct().getId() == id && cart.getSize().getId() == sizeId) {
                    cartCount++;
                    break;
                }
            }
            if (cartCount > 0) {
                for (Cart cart : listCart) {
                    if (cart.getProduct().getId() == id && cart.getSize().getId() == sizeId) {
                        cart.setCount(cart.getCount() + 1);
                        cartService.saveCart(cart);
                        break;
                    }
                }
            } else {
                Cart newCart = new Cart();
                newCart.setCount(1);
                Size size = sizeService.findById(sizeId);
                if (size != null) {
                    newCart.setSize(size);
                }
                if (product != null) {
                    newCart.setProduct(product);
                }
                newCart.setUser(user);
                cartService.saveCart(newCart);
            }
            listCart = cartService.GetAllCartByUser_id(user.getId());
            session.setAttribute("countCart", listCart.size());
            return "redirect:/client/cart";
        }
    }


    @PostMapping("/addToCart")
    public String AddToCartPost(@ModelAttribute("product_id") int product_id, @ModelAttribute("size_id") int size_id, @ModelAttribute("count") String cou,
                                HttpServletRequest request) throws Exception {
        int count = Integer.parseInt(cou);
        User user = (User) session.getAttribute("acc");
        if (user == null) {
            session.setAttribute("AddToCartErr", "Please signin before proceeding to cart");
            return "redirect:/login";
        } else {
            List<Cart> listCart = cartService.GetAllCartByUser_id(user.getId());
            Product product = productService.getProductById(product_id);
            int cart = 0;
            for (Cart y : listCart) {
                if (y.getProduct().getId() == product_id && y.getSize().getId() == size_id) {
                    y.setCount(y.getCount() + count);
                    cartService.saveCart(y);
                    cart++;
                }
            }
            if (cart == 0) {
                Cart newCart = new Cart();
                newCart.setCount(count);
                Size size = sizeService.findById(size_id);
                newCart.setSize(size);
                newCart.setProduct(product);
                newCart.setUser(user);
                cartService.saveCart(newCart);
            }
            listCart = cartService.GetAllCartByUser_id(user.getId());
            session.setAttribute("countCart", listCart.size());
            return "redirect:/client/cart";

        }

    }

}