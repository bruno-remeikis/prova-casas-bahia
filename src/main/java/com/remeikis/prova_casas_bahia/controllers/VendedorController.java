package com.remeikis.prova_casas_bahia.controllers;

import com.remeikis.prova_casas_bahia.models.Vendedor;
import com.remeikis.prova_casas_bahia.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(
            vendedorService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> findByMatricula(@PathVariable String matricula) {
        return ResponseEntity.ok(
            vendedorService.findByMatricula(matricula)
        );
    }

    @PostMapping
    public ResponseEntity<Vendedor> create(@RequestBody Vendedor vendedor) {
        return ResponseEntity.ok(
            vendedorService.create(vendedor)
        );
    }

    @PutMapping
    public ResponseEntity<Vendedor> update(@RequestBody Vendedor vendedor) {
        return ResponseEntity.ok(
            vendedorService.update(vendedor)
        );
    }
}
