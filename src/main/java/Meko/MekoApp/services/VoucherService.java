package Meko.MekoApp.services;

import Meko.MekoApp.entities.Voucher;
import Meko.MekoApp.repositories.VoucherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherService {
    private VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public List<Voucher> findAll() {
        List<Voucher> vouchers = new ArrayList<>();
        vouchers = voucherRepository.findAll();
        return vouchers;
    }
}
