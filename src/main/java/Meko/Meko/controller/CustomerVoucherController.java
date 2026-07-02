package Meko.Meko.controller;

import Meko.Meko.services.VoucherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/voucher")
public class CustomerVoucherController {
    private final VoucherService voucherService;

    public CustomerVoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public String showVoucherStore(Model model) {
        model.addAttribute("vouchers", voucherService.findAll());
        return "homepage/voucher";
    }
}
