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
        if(competencia.getNombre() == null || competencia.getNombre().trim().isEmpty()){
            return null;
        }else{
            return competenciaRepository.save(competencia);
        }
    }

    public String updateCompetencia(Competencia competencia){
        if(competenciaRepository.existsById(competencia.getIdCompetencia())){
            Optional<Competencia> compe = competenciaRepository.findById(competencia.getIdCompetencia());

            compe.get().setNombre(competencia.getNombre());
            competenciaRepository.save(compe.get());
            return "Competencia actualizada con éxito";
        }else{
            return null;
        }
    }

    public String deleteCompetencia(Integer id){
        if(competenciaRepository.existsById(id)){
            competenciaRepository.deleteById(id);
            return "Competencia eliminada con éxito";
        }else{
            return null;
        }
    }

    public List<Competencia> listCompetencia(){
        return competenciaRepository.findAll();
    }
}
