package Meko.Meko.controller;

import Meko.Meko.entities.Voucher;
import Meko.Meko.services.VoucherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard/voucher")
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    // 1. Lấy danh sách voucher
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("vouchers", voucherService.findAll());
        return "dashboard/voucher_list";
    }

    // 2. Gọi đến trang create voucher
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("voucher", new Voucher());
        return "dashboard/voucher_create";
    }

    // 3. Hứng data voucher từ form tạo mới
    @PostMapping("/create")
    public String store(@ModelAttribute Voucher voucher) {
        voucherService.save(voucher);
        return "redirect:/dashboard/voucher";
    }

    // 4. Gọi đến trang edit của voucher
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("voucher", voucherService.findById(id));
        return "dashboard/voucher_edit";
    }

    // 5. Update lại voucher đã chọn
    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Voucher formVoucher) {
        Voucher voucher = voucherService.findById(id);

        voucher.setVoucherCode(formVoucher.getVoucherCode());
        voucher.setVoucherName(formVoucher.getVoucherName());
        voucher.setDiscountType(formVoucher.getDiscountType());
        voucher.setValue(formVoucher.getValue());
        voucher.setAmount(formVoucher.getAmount());
        voucher.setStartDate(formVoucher.getStartDate());
        voucher.setEndDate(formVoucher.getEndDate());
        voucher.setStatus(formVoucher.getStatus());

        voucherService.save(voucher);
        return "redirect:/dashboard/voucher";
    }

    // 6. Xóa voucher đã chọn
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        voucherService.delete(id);
        return "redirect:/dashboard/voucher";
    }
}