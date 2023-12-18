package com.tictac.demo.service;

import com.tictac.demo.entity.Docente;
import com.tictac.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {

    @Autowired
    DocenteRepository docenteRepository;

    public Docente saveDocente(Docente docente){
        return docenteRepository.save(docente);
    }

    public void deleteDocente(String id){
        docenteRepository.deleteById(id);
    }

    public List<Docente> listDocente(){
        return docenteRepository.findAll();
    }
}
