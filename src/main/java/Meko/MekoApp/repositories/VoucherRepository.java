package Meko.MekoApp.repositories;

import Meko.MekoApp.entities.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
}
