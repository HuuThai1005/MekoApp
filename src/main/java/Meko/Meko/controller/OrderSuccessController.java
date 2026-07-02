package Meko.Meko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class OrderSuccessController {
    @GetMapping("/order-success")
    public String orderSuccess(){

        return "homepage/order-success";
    }
}
