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
        return liderLineaRepository.save(liderLinea);
    }

    public void deleteLiderLinea(String id){
        liderLineaRepository.deleteById(id);
    }

    public List<LiderLinea> listLiderLinea(){
        return liderLineaRepository.findAll();
    }

}
