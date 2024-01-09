package com.tictac.demo.service;

import com.tictac.demo.entity.Momento;
import com.tictac.demo.repository.MomentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MomentoService {

    @Autowired
    MomentoRepository momentoRepository;

    public Optional<Momento> getMomento(Integer id){
        return momentoRepository.findById(id);
    }
    
    public Momento createMomento(Momento momento){
        if(momento.getIdHerramienta() == null || momento.getIdHerramienta().toString().trim().isEmpty() ||
            momento.getNombre() == null || momento.getNombre().trim().isEmpty() ||
            momento.getDescripcion() == null || momento.getDescripcion().trim().isEmpty()){
            return null;
        }else{
            return momentoRepository.save(momento);
        }
    }

    public String updateMomento(Momento momento){
        if(momentoRepository.existsById(momento.getIdMomento())){
            Optional<Momento> m = momentoRepository.findById(momento.getIdMomento());

            m.get().setIdHerramienta(momento.getIdHerramienta());
            m.get().setNombre(momento.getNombre());
            m.get().setDescripcion(momento.getDescripcion());
            momentoRepository.save(m.get());
            return "Ciudad actualizada con éxito";
        }else{
            return null;
        }
    }

    public String deleteMomento(Integer id){
        if(momentoRepository.existsById(id)){
            momentoRepository.deleteById(id);
            return "Momento eliminado con éxito";
        }else {
            return null;
        }
    }


    public List<Momento> listMomento(){
        return momentoRepository.findAll();
    }
}
