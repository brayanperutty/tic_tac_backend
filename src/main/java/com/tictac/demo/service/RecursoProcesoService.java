package com.tictac.demo.service;

import com.tictac.demo.entity.RecursoProceso;
import com.tictac.demo.repository.RecursoProcesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoProcesoService {

    @Autowired
    RecursoProcesoRepository recursoProcesoRepository;

    public Optional<RecursoProceso> getRecursoProceso(Integer id){
        return recursoProcesoRepository.findById(id);
    }

    public RecursoProceso createRecursoProceso(RecursoProceso recursoProceso){
        return recursoProcesoRepository.save(recursoProceso);
    }

    public void deleteRecursoProceso(Integer id){
        recursoProcesoRepository.deleteById(id);
    }

    public List<RecursoProceso> listRecursoProceso(){
        return recursoProcesoRepository.findAll();
    }
}
