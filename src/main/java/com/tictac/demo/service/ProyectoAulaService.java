package com.tictac.demo.service;

import com.tictac.demo.entity.ProyectoAula;
import com.tictac.demo.repository.ProyectoAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProyectoAulaService {

    @Autowired
    ProyectoAulaRepository proyectoAulaRepository;

    public Optional<ProyectoAula> getProyectoAula(Integer id){
        return proyectoAulaRepository.findById(id);
    }

    public ProyectoAula createProyectoAula(ProyectoAula proyectoAula){
        return proyectoAulaRepository.save(proyectoAula);
    }

    public void deleteProyectoAula(Integer id){
        proyectoAulaRepository.deleteById(id);
    }

    public List<ProyectoAula> listProyectoAula(){
        return proyectoAulaRepository.findAll();
    }
}
