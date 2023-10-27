package com.banana.banana_invoices.persistence;

import com.banana.banana_invoices.models.ReciboInvalido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReciboInvalidoRepository extends JpaRepository<ReciboInvalido, Long> {
}
