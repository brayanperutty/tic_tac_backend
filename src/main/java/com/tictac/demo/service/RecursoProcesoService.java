package com.tictac.demo.service;

import com.tictac.demo.entity.RecursoProceso;
import com.tictac.demo.repository.RecursoProcesoRepository;
import org.apache.poi.sl.draw.geom.GuideIf;
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
        if(recursoProceso.getIdProceso() == null || recursoProceso.getIdProceso().toString().trim().isEmpty()){
            return null;
        }else{
            return recursoProcesoRepository.save(recursoProceso);
        }
    }

    public String updateRecursoProceso(RecursoProceso recursoProceso){
        if(recursoProcesoRepository.existsById(recursoProceso.getIdRecurso())){
            Optional<RecursoProceso> rp = recursoProcesoRepository.findById(recursoProceso.getIdRecurso());

            rp.get().setIdProceso(recursoProceso.getIdProceso());
            recursoProcesoRepository.save(rp.get());
            return "Recurso del proceso actualizado con éxito";
        }else{
            return null;
        }
    }

    public String deleteRecursoProceso(Integer id){
        if(recursoProcesoRepository.existsById(id)){
            recursoProcesoRepository.deleteById(id);
            return "Recurso del proceso eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<RecursoProceso> listRecursoProceso(){
        return recursoProcesoRepository.findAll();
    }
}
