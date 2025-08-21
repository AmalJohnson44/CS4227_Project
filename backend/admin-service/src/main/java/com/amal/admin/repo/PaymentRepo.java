
package com.amal.admin.repo;
import com.amal.admin.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepo extends JpaRepository<Payment,Long>{}
