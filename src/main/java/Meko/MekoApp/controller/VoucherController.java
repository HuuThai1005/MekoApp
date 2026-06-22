package Meko.MekoApp.controller;

import Meko.MekoApp.entities.Voucher;
import Meko.MekoApp.services.VoucherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    private VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }
// lấy danh sách voucher
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("vouchers",voucherService.findAll());
        return "dashboard/voucher_list";
    }
    // gọi đến trang create voucher
    @GetMapping("/create-voucher")
    public String create(Model model) {

        model.addAttribute(
                "voucher",
                new Voucher());

        return "dashboard/voucher_create";
    }
    // hứng path voucher từ form
    @PostMapping("/create")
    public String store(
            @ModelAttribute Voucher voucher) {

        voucherService.save(voucher);

        return "redirect:/voucher";
    }
    // gọi đến trang edit của voucher có id đã chọn
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Integer id,
            Model model) {

        model.addAttribute(
                "voucher",
                voucherService.findById(id));

        return "dashboard/voucher_edit";
    }
    // update lại voucher đã chọn
    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Integer id,
            @ModelAttribute Voucher formVoucher) {

        Voucher voucher =
                voucherService.findById(id);

        voucher.setVoucherCode(
                formVoucher.getVoucherCode());

        voucher.setVoucherName(
                formVoucher.getVoucherName());

        voucher.setDiscountType(
                formVoucher.getDiscountType());

        voucher.setValue(
                formVoucher.getValue());

        voucher.setAmount(
                formVoucher.getAmount());

        voucher.setStartDate(
                formVoucher.getStartDate());

        voucher.setEndDate(
                formVoucher.getEndDate());

        voucher.setStatus(
                formVoucher.getStatus());

        voucherService.save(voucher);

        return "redirect:/voucher";
    }
    // xóa voucher đã chọn
    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Integer id) {

        voucherService.delete(id);

        return "redirect:/voucher";
    }
}
