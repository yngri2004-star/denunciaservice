package com.moralestarrillo.denunciaservice.repository;

import com.moralestarrillo.denunciaservice.entity.Denuncia;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

    List<Denuncia> findByDni(String dni);

    List<Denuncia> findAllBy(Pageable pageable);
}