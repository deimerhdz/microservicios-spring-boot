package com.dhernandez.springcloud.msvc.cursos.services;

import com.dhernandez.springcloud.msvc.cursos.entity.Curso;
import com.dhernandez.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class CursoServiceImpl implements CursoService{
    @Autowired
    private CursoRepository cursoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Curso> listar() {
        return (List<Curso>)cursoRepository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Curso> porId(Long id) {
        return cursoRepository.findById(id);
    }
    @Transactional
    @Override
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }
    @Transactional
    @Override
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
}