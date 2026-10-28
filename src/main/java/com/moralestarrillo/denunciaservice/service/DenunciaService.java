package com.moralestarrillo.denunciaservice.service;

import com.moralestarrillo.denunciaservice.entity.Denuncia;
import com.moralestarrillo.denunciaservice.repository.DenunciaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;

    public DenunciaService(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    @Transactional
    public Denuncia registrarDenuncia(Denuncia denuncia) {
        if (denuncia.getEstado() == null) {
            denuncia.setEstado(1);
        }
        return denunciaRepository.save(denuncia);
    }

    @Transactional(readOnly = true)
    public List<Denuncia> listarPaginado(int offset, int limit) {
        int pageSize = limit > 0 ? limit : 5;
        int safeOffset = Math.max(0, offset);
        return denunciaRepository.findAllBy(PageRequest.of(safeOffset / pageSize, pageSize));
    }

    @Transactional(readOnly = true)
    public Optional<Denuncia> buscarPorId(Long id) {
        return denunciaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Denuncia> buscarPorDni(String dni) {
        return denunciaRepository.findByDni(dni);
    }

    @Transactional
    public Optional<Denuncia> atenderDenuncia(Long id) {
        return denunciaRepository.findById(id).map(denuncia -> {
            denuncia.setEstado(0);
            return denunciaRepository.save(denuncia);
        });
    }
}
