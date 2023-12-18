package com.tictac.demo.service;

import com.tictac.demo.entity.ContenidoDigital;
import com.tictac.demo.repository.ContenidoDigitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContenidoDigitalService {

    @Autowired
    ContenidoDigitalRepository contenidoDigitalRepository;

    public Optional<ContenidoDigital> getContenidoDigital(Integer id){
        return contenidoDigitalRepository.findById(id);
    }

    public ContenidoDigital createContenidoDigital(ContenidoDigital contenidoDigital){
        return contenidoDigitalRepository.save(contenidoDigital);
    }

    public void deleteContenidoDigital(Integer id){
        contenidoDigitalRepository.deleteById(id);
    }

    public List<ContenidoDigital> listContenidoDigital(){
        return contenidoDigitalRepository.findAll();
    }
}
