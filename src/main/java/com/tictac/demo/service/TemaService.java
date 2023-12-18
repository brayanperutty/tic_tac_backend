package com.tictac.demo.service;

import com.tictac.demo.entity.Tema;
import com.tictac.demo.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaService {

    @Autowired
    TemaRepository temaRepository;

    public Tema saveTema(Tema tema){
        return temaRepository.save(tema);
    }

    public void deleteTema(Integer id){
        temaRepository.deleteById(id);
    }

    public List<Tema> listTema(){
        return temaRepository.findAll();
    }
}
