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
        if(proceso.getIdMomento() == null || proceso.getIdMomento().toString().isEmpty() ||
            proceso.getDescripcion() == null || proceso.getDescripcion().trim().isEmpty() ||
            proceso.getTiempo() == null || proceso.getTiempo().trim().isEmpty()){
            return null;
        }else{
            return procesoRepository.save(proceso);
        }
    }

    public String updateProceso(Proceso proceso){
        if(procesoRepository.existsById(proceso.getIdProceso())){
            Optional<Proceso> p = procesoRepository.findById(proceso.getIdProceso());

            p.get().setIdMomento(proceso.getIdMomento());
            p.get().setDescripcion(proceso.getDescripcion());
            p.get().setTiempo(proceso.getTiempo());
            procesoRepository.save(p.get());
            return "Proceso actualizado con éxito";
        }else{
            return null;
        }
    }

    public String deleteProceso(Integer id){
        if(procesoRepository.existsById(id)){
            procesoRepository.deleteById(id);
            return "Proceso eliminado con éxito";
        }else{
          return null;
        }
    }

    public List<Proceso> listProceso(){
        return procesoRepository.findAll();
    }
}
