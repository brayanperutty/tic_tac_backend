package com.tictac.demo.service;

import com.tictac.demo.entity.Herramienta;
import com.tictac.demo.repository.HerramientaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HerramientaService {

    @Autowired
    HerramientaRepository herramientaRepository;

    public Optional<Herramienta> getHerramienta(Integer id){
        return herramientaRepository.findById(id);
    }
    
    public Herramienta createHerramienta(Herramienta herramienta){
        return herramientaRepository.save(herramienta);
    }

    public void deleteHerramienta(Integer id){
        herramientaRepository.deleteById(id);
    }

    public List<Herramienta> listHerramienta(){
        return herramientaRepository.findAll();
    }
}
