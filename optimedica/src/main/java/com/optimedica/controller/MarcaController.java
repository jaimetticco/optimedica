package com.optimedica.controller;

import com.optimedica.model.Marca;
import com.optimedica.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/marca")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping("/lista")
    public ResponseEntity<List<Marca>> listarMarcas(){
        List<Marca> marc = service.listarMarca();
        return new ResponseEntity<List<Marca>>(marc, HttpStatus.OK);

    }

}
