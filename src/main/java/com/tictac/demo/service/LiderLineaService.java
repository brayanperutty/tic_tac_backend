package com.tictac.demo.service;

import com.tictac.demo.entity.LiderLinea;
import com.tictac.demo.repository.LiderLineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiderLineaService {

    @Autowired
    LiderLineaRepository liderLineaRepository;

    public Optional<LiderLinea> getLiderLinea(String id){
        return liderLineaRepository.findById(id);
    }

    public LiderLinea saveLiderLinea(LiderLinea liderLinea){
        if (liderLinea.getIdLinea() == null || liderLinea.getIdLinea().toString().trim().isEmpty() ||
            liderLinea.getEsLider() == null || liderLinea.getEsLider().toString().trim().isEmpty() ||
            liderLinea.getIdDocente() == null || liderLinea.getIdDocente().trim().isEmpty()) {
            return null;
        }else{
            return liderLineaRepository.save(liderLinea);
        }
    }

    public String deleteLiderLinea(String id){
        if(liderLineaRepository.existsById(id)){
            liderLineaRepository.deleteById(id);
            return "Líder de línea eliminado con éxito";
        }else{
            return null;
        }
    }

    public String updateLiderLinea(LiderLinea liderLinea){
        if(liderLineaRepository.existsById(liderLinea.getIdDocente())){
            Optional<LiderLinea> l = liderLineaRepository.findById(liderLinea.getIdDocente());

            l.get().setIdLinea(liderLinea.getIdLinea());
            l.get().setEsLider(liderLinea.getEsLider());

            liderLineaRepository.save(l.get());
            return "Líder de línea actualizado con éxito";
        }else{
            return null;
        }
    }

    public List<LiderLinea> listLiderLinea(){
        return liderLineaRepository.findAll();
    }

}
