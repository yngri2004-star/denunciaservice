package com.moralestarrillo.denunciaservice.controller;

import com.moralestarrillo.denunciaservice.entity.Denuncia;
import com.moralestarrillo.denunciaservice.service.DenunciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/denuncias", "/denuncias/"})
@CrossOrigin(origins = "*")
public class DenunciaController {

    private final DenunciaService denunciaService;

    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Denuncia> registrarDenuncia(@RequestBody Denuncia denuncia) {
        Denuncia nuevaDenuncia = denunciaService.registrarDenuncia(denuncia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDenuncia);
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Denuncia>> listarPaginado(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int limit) {
        List<Denuncia> denuncias = denunciaService.listarPaginado(offset, limit);
        return ResponseEntity.ok(denuncias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> buscarPorId(@PathVariable Long id) {
        return denunciaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ciudadano/{dni}")
    public ResponseEntity<List<Denuncia>> buscarPorDni(@PathVariable String dni) {
        List<Denuncia> denuncias = denunciaService.buscarPorDni(dni);
        return ResponseEntity.ok(denuncias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> atenderDenuncia(@PathVariable Long id) {
        return denunciaService.atenderDenuncia(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
