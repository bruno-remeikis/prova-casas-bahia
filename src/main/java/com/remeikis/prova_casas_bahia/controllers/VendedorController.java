package com.remeikis.prova_casas_bahia.controllers;

import com.remeikis.prova_casas_bahia.models.Vendedor;
import com.remeikis.prova_casas_bahia.models.dto.CreateVendedorDto;
import com.remeikis.prova_casas_bahia.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<List<Vendedor>> findAll() {
        List<Vendedor> vendedores = vendedorService.findAll();
        return ResponseEntity.ok(vendedores);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Vendedor> findByMatricula(@PathVariable String matricula) {
        Vendedor v = vendedorService.findByMatricula(matricula);
        return ResponseEntity.ok(v);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateVendedorDto vendedorDto) {
        try {
            Vendedor v = vendedorService.create(vendedorDto);
            return ResponseEntity.ok(v);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Vendedor vendedor) {
        try {
            Vendedor v = vendedorService.update(vendedor);
            return ResponseEntity.ok(v);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
        }
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity delete(@PathVariable String matricula) {
        try {
            Vendedor v = vendedorService.delete(matricula);
            return ResponseEntity.ok(v);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
        }
    }
}
