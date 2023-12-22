package com.tictac.demo.service;

import com.tictac.demo.entity.Competencia;
import com.tictac.demo.repository.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenciaService {

    @Autowired
    CompetenciaRepository competenciaRepository;

    public Optional<Competencia> getCompetencia(Integer id){
        return competenciaRepository.findById(id);
    }
    public Competencia saveCompetencia(Competencia competencia){
        return competenciaRepository.save(competencia);
    }

    public void deleteCompetencia(Integer id){
        competenciaRepository.deleteById(id);
    }

    public List<Competencia> listCompetencia(){
        return competenciaRepository.findAll();
    }
}
