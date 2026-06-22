package Meko.Meko.services;

import Meko.Meko.entities.Voucher;
import Meko.Meko.repositories.VoucherRepository;
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

    public Voucher findById(Integer id) {
        return voucherRepository.findById(id)
                .orElse(null);
    }

    public Voucher save(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    public void delete(Integer id) {
        voucherRepository.deleteById(id);
    }
}
