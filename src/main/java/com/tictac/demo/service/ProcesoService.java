package com.tictac.demo.service;

import com.tictac.demo.entity.Proceso;
import com.tictac.demo.repository.ProcesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcesoService {

    @Autowired
    ProcesoRepository procesoRepository;

    public Optional<Proceso> getProceso(Integer id){
        return  procesoRepository.findById(id);
    }

    public Proceso createProceso(Proceso proceso){
        return procesoRepository.save(proceso);
    }

    public void deleteProceso(Integer id){
        procesoRepository.deleteById(id);
    }

    public List<Proceso> listProceso(){
        return procesoRepository.findAll();
    }
}
