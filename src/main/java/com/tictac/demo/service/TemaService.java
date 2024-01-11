package com.tictac.demo.service;

import com.tictac.demo.entity.Tema;
import com.tictac.demo.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemaService {

    @Autowired
    TemaRepository temaRepository;

    public Optional<Tema> getTema(Integer id){
        return temaRepository.findById(id);
    }

    public Tema saveTema(Tema tema){
        if(tema.getNombre() == null || tema.getNombre().trim().isEmpty() ||
            tema.getIdLinea() == null || tema.getIdLinea().toString().trim().isEmpty() ||
            tema.getIdCompetencia() == null || tema.getIdCompetencia().toString().trim().isEmpty()){
            return null;
        }else{
            return temaRepository.save(tema);
        }
    }

    public String updateTema(Tema tema){
        if(temaRepository.existsById(tema.getIdTema())){
            Optional<Tema> t = temaRepository.findById(tema.getIdTema());

            t.get().setNombre(tema.getNombre());
            t.get().setIdLinea(tema.getIdLinea());
            t.get().setIdCompetencia(tema.getIdCompetencia());
            temaRepository.save(t.get());
            return "Tema actualizado con éxito";
        }else{
            return null;
        }
    }

    public String deleteTema(Integer id){
        if(temaRepository.existsById(id)){
            temaRepository.deleteById(id);
            return "Tema eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<Tema> listTema(){
        return temaRepository.findAll();
    }
}
