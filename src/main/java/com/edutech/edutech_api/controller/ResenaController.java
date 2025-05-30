//Anais Llancapan - peitou1
package com.edutech.edutech_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;   
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.edutech_api.dto.ResenaDTO;
import com.edutech.edutech_api.model.Resena;
import com.edutech.edutech_api.service.ResenaService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/resenas") 
public class ResenaController {

    @Autowired 
    private ResenaService resenaService;

    //Crear reseña
    @PostMapping 
    public ResponseEntity<Resena> crearResena(@Valid @RequestBody ResenaDTO dto) {
        try {
            Resena nuevaResena = resenaService.crearResena(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaResena);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Listar reseñas
    @GetMapping 
    public ResponseEntity<List<Resena>> listarResenas() {
        List<Resena> resenas = resenaService.listarResenas();
        return ResponseEntity.ok(resenas);
    }

    //Eliminar reseña
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarResena(@PathVariable Long id) {
        return resenaService.eliminarResena(id)
            ? ResponseEntity.ok("Reseña eliminada correctamente")
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reseña no encontrada");
    }
}
