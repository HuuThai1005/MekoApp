package Meko.MekoApp.controller;

import Meko.MekoApp.entities.Voucher;
import Meko.MekoApp.services.VoucherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    private VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("vouchers",voucherService.findAll());
        return "homepage/voucher";
    }
}
