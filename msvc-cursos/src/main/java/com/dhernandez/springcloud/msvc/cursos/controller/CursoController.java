package com.dhernandez.springcloud.msvc.cursos.controller;

import com.dhernandez.springcloud.msvc.cursos.entity.Curso;
import com.dhernandez.springcloud.msvc.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;


    @GetMapping("/listar")
    public ResponseEntity<List<Curso>> listar(){
        return new ResponseEntity<>(cursoService.listar(), HttpStatus.OK);
    }


    @GetMapping("/detalle/{id}")
    public ResponseEntity<Curso> detalle(@PathVariable("id") Long id){
        Optional<Curso> cursoOptional = cursoService.porId(id);
        if(cursoOptional.isPresent()){
            return new ResponseEntity<>(cursoOptional.get(),HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/crear")
    public ResponseEntity<Curso> crear(@RequestBody Curso curso){
        return new ResponseEntity<>(cursoService.guardar(curso),HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Curso> editar(@PathVariable("id") Long id,@RequestBody Curso curso){
        Optional<Curso> cursoOptional = cursoService.porId(id);
        if(cursoOptional.isPresent()){
            Curso cursoDB= cursoOptional.get();
            cursoDB.setNombre(curso.getNombre());

            return new ResponseEntity<>(cursoService.guardar(cursoDB),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id){
        Optional<Curso> cursoOptional = cursoService.porId(id);
        if(cursoOptional.isPresent()){
            cursoService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
}
