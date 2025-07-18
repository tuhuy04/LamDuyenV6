package com.example.WebAoDai.controller.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.WebAoDai.config.VNPayConfig;
import com.example.WebAoDai.entity.*;
import com.example.WebAoDai.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/client/order")
public class OrderController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    CategorySizeService categorySizeService;

    @Autowired
    Order_ItemService order_ItemService;

    @Autowired
    OrderService orderService;

    @Autowired
    HttpSession session;

    @GetMapping("checkout")
    public String CheckOutView(Model model) {
        User user = (User) session.getAttribute("acc");
        if (user == null) {
            session.setAttribute("AddToCartErr", "Vui lòng đăng nhập trước khi thực hiện thao tác!");
            return "redirect:/login";
        } else {
            List<Cart> Cart = cartService.GetAllCartByUser_id(user.getId());
            if (!Cart.isEmpty()) {
                String a = session.getAttribute("Total").toString();
                int Total = Integer.parseInt(a);
                System.out.println(Total);
                model.addAttribute("Total", a);
                @SuppressWarnings("unchecked")
                List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
                model.addAttribute("listCart", listCart);
                return "checkout";
            } else {
                session.setAttribute("CartIsEmpty", "CartIsEmpty");
                return "redirect:/client/cart";
            }
        }
    }

    @PostMapping("checkout")
    public String CheckOut(@ModelAttribute("fullname") String fullname, @ModelAttribute("country") String country,
                           @ModelAttribute("address") String address, @ModelAttribute("phone") String phone,
                           @ModelAttribute("email") String email, @ModelAttribute("note") String note,
                           @RequestParam(value = "paymentMethod", defaultValue = "false") String paymentMethod, Model model,
                           HttpServletResponse resp) throws Exception {

        long millis = System.currentTimeMillis();
        @SuppressWarnings("unchecked")
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        User user = (User) session.getAttribute("acc");
        String a = session.getAttribute("Total").toString();
        int Total = Integer.parseInt(a);
        String status = "Pending";
        String payment_method = null;
        if (paymentMethod.equals("COD")) {
            payment_method = "Payment on delivery";
        } else {
            payment_method = "Payment with VNPay";
        }
        Order newOrder = new Order();
        newOrder.setTotal(Total);
        newOrder.setAddress(address);
        newOrder.setBooking_Date(LocalDateTime.now());
        newOrder.setCountry(country);
        newOrder.setEmail(email);
        newOrder.setFullname(fullname);
        newOrder.setNote(note);
        newOrder.setPayment_Method(payment_method);
        newOrder.setPhone(phone);
        newOrder.setStatus(status);
        newOrder.setUser(user);
        if (payment_method.equals("Payment with VNPay")) {
            session.setAttribute("newOrder", newOrder);
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";
            String bankCode = "NCB";

            String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
            String vnp_IpAddr = "127.0.0.1";

            String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(Total * 100));
            vnp_Params.put("vnp_CurrCode", "VND");

            vnp_Params.put("vnp_BankCode", bankCode);
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);

            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
            System.out.println("URL: " + paymentUrl);
            return "redirect:" + paymentUrl;
        } else {
            orderService.saveOrder(newOrder);
            List<Order> listOrder = orderService.getAllOrderByUser_Id(user.getId());
            newOrder = listOrder.get(listOrder.size() - 1);
            for (Cart y : listCart) {
                Product product = y.getProduct();
                product.setQuantity(product.getQuantity() - y.getCount());
                product = productService.saveProduct(product);
                Order_Item newOrder_Item = new Order_Item();
                newOrder_Item.setCount(y.getCount());
                newOrder_Item.setProduct(product);
                newOrder_Item.setOrder(newOrder);
                newOrder_Item.setSize(y.getSize());
                order_ItemService.saveOrder_Item(newOrder_Item);
                cartService.deleteById(y.getId());
            }
            cartService.deleteAllByUserId(user.getId());
            session.removeAttribute("listCart");
            session.removeAttribute("Total");

            listOrder = orderService.getAllOrderByUser_Id(user.getId());
            newOrder = listOrder.get(listOrder.size() - 1);
            session.setAttribute("order", newOrder);
            return "redirect:/client/order/invoice";
        }
    }


    @GetMapping("getPaymentInfo")
    public String getPaymentInfo(@RequestParam("vnp_TransactionNo") String transactionNo) {
        if (!Objects.equals(transactionNo, "0")) {
            @SuppressWarnings("unchecked")
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            User user = (User) session.getAttribute("acc");
            Order newOrder = (Order) session.getAttribute("newOrder");
            newOrder.setPayment_Method("Payment with VNPay");
            orderService.saveOrder(newOrder);
            List<Order> listOrder = orderService.getAllOrderByUser_Id(user.getId());
            newOrder = listOrder.get(listOrder.size() - 1);
            for (Cart y : listCart) {
                Product product = y.getProduct();
                product.setQuantity(product.getQuantity() - y.getCount());
                product = productService.saveProduct(product);
                Order_Item newOrder_Item = new Order_Item();
                newOrder_Item.setCount(y.getCount());
                newOrder_Item.setProduct(product);
                newOrder_Item.setOrder(newOrder);
                newOrder_Item.setSize(y.getSize());
                order_ItemService.saveOrder_Item(newOrder_Item);
                cartService.deleteById(y.getId());
            }

            cartService.deleteAllByUserId(user.getId());
            session.removeAttribute("listCart");
            session.removeAttribute("Total");


            listOrder = orderService.getAllOrderByUser_Id(user.getId());
            newOrder = listOrder.get(listOrder.size() - 1);
            session.setAttribute("order", newOrder);
        }
        return "redirect:/client/order/invoice";
    }


    @GetMapping("invoice")
    public String Invoice(Model model) {
        Order order = (Order) session.getAttribute("order");
        String invoiceView = (String) session.getAttribute("invoiceView");
        session.setAttribute("invoiceView", null);
        List<Order_Item> listOrder_Item = order_ItemService.getAllByOrder_Id(order.getId());
        model.addAttribute("invoiceView", invoiceView);
        model.addAttribute("listOrder_Item", listOrder_Item);
        model.addAttribute("order", order);
        return "invoice";
    }

    @GetMapping("/invoice/{id}")
    public String InvoiceView(@PathVariable int id) {
        Order order = orderService.findById(id);
        session.setAttribute("order", order);
        session.setAttribute("invoiceView", "view");
        return "redirect:/client/order/invoice";
    }

    @GetMapping("/myhistory")
    public String Myhistory(Model model) {
        User user = (User) session.getAttribute("acc");
        if (user == null) {
            return "redirect:/login";
        } else {
            List<Order> listOrder = orderService.getAllOrderByUser_Id(user.getId());
            Collections.reverse(listOrder);
            model.addAttribute("listOrder", listOrder);
            System.out.println(listOrder);
            for (Order y : listOrder) {
                System.out.println(y.getOrder_Item());
            }
        }
        return "myhistory";
    }
}
