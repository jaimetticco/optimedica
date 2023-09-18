package com.optimedica.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimedica.model.Producto;
import com.optimedica.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/producto")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> prod = service.listarProducto();
        return new ResponseEntity<List<Producto>>(prod, HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Producto> agragarProducto(@RequestParam("producto")  String producto,
                                                    @RequestParam("imagen")  MultipartFile imagen) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Producto productoObj = objectMapper.readValue(producto, Producto.class);

        Producto prod = service.nuevo(productoObj,imagen);
        return new ResponseEntity<Producto>(prod, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Producto> actualizarProducto(@RequestParam("producto")  String producto,
                                                       @RequestParam("imagen")  MultipartFile imagen)throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Producto productoObj = objectMapper.readValue(producto, Producto.class);
        Producto prod = service.actualizar(productoObj,imagen);
        return new ResponseEntity<Producto>(prod, HttpStatus.OK);
    }

    @GetMapping("/listarId/{id_producto}")
    public ResponseEntity<Producto> listarPorId(@Validated @RequestBody @PathVariable("id_producto")Integer id_producto)throws Exception{
        Producto prod = service.listaPorId(id_producto);
        if (prod == null){
            throw new Exception("No existe ID");
        }
        return new ResponseEntity<Producto>(prod, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id_producto}")
    public ResponseEntity<Void> aliminarProducto(@Validated @RequestBody @PathVariable("id_producto")Integer id_producto)throws Exception{
        Producto prod = service.listaPorId(id_producto);;
        if (prod == null){
            throw new Exception("No existe ID");
        }
        service.eliminar(id_producto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
