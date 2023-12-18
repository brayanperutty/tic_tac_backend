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
        return momentoRepository.save(momento);
    }

    public void deleteMomento(Integer id){
        momentoRepository.deleteById(id);
    }


    public List<Momento> listMomento(){
        return momentoRepository.findAll();
    }
}
