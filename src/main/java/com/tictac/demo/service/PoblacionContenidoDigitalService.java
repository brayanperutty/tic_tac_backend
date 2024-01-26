package com.tictac.demo.service;

import com.tictac.demo.entity.PoblacionContenidoDigital;
import com.tictac.demo.repository.PoblacionContenidoDigitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoblacionContenidoDigitalService {

    @Autowired
    PoblacionContenidoDigitalRepository poblacionContenidoDigitalRepository;

    public List<PoblacionContenidoDigital> listPoblacionContenidoDigital(Integer id){
        return poblacionContenidoDigitalRepository.findByIdContenidoDigital(id);
    }

    public String deletePoblacionContenidoDigital(Integer idContenidoDigital, Integer idPoblacion){
        if(poblacionContenidoDigitalRepository.existsByIdContenidoDigitalAndIdPoblacion(idContenidoDigital, idPoblacion)){
            poblacionContenidoDigitalRepository.deleteByIdContenidoDigitalAndIdPoblacion(idContenidoDigital,idPoblacion);
            return "Población eliminada con éxito";
        }else{
            return null;
        }
    }

    public PoblacionContenidoDigital createPoblacionContenidoDigital(PoblacionContenidoDigital poblacionContenidoDigital) {
        if (poblacionContenidoDigital.getIdContenidoDigital() == null || poblacionContenidoDigital.getIdContenidoDigital().toString().trim().isEmpty() ||
                poblacionContenidoDigital.getIdPoblacion() == null || poblacionContenidoDigital.getIdPoblacion().toString().trim().isEmpty()){
            return null;
        }else{
           return poblacionContenidoDigitalRepository.save(poblacionContenidoDigital);
        }
    }
}
