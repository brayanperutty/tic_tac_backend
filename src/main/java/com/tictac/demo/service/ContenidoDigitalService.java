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
        if(contenidoDigital.getDocenteAutor() == null || contenidoDigital.getDocenteAutor().trim().isEmpty() ||
            contenidoDigital.getNombreContDigital() == null || contenidoDigital.getNombreContDigital().trim().isEmpty() ||
            contenidoDigital.getVisibilidad() == null || contenidoDigital.getVisibilidad().toString().trim().isEmpty() ||
            contenidoDigital.getIdLinea() == null || contenidoDigital.getIdLinea().toString().trim().isEmpty() ||
            contenidoDigital.getEstado() == null || contenidoDigital.getEstado().trim().isEmpty() ||
            contenidoDigital.getRecomendacion() == null || contenidoDigital.getRecomendacion().trim().isEmpty() ||
            contenidoDigital.getFechaAprobacion() == null || contenidoDigital.getFechaAprobacion().toString().trim().isEmpty() ||
            contenidoDigital.getFechaCreacion() == null || contenidoDigital.getFechaCreacion().toString().trim().isEmpty()){
            return null;
        }else {
            return contenidoDigitalRepository.save(contenidoDigital);
        }
    }

    public String deleteContenidoDigital(Integer id){
        if(contenidoDigitalRepository.existsById(id)){
            contenidoDigitalRepository.deleteById(id);
            return "Contenido digital eliminado con éxito";
        }else{
            return null;
        }
    }

    public List<ContenidoDigital> listContenidoDigital(){
        return contenidoDigitalRepository.findAll();
    }

    public String updateContenidoDigital(ContenidoDigital contenidoDigital){
        if(contenidoDigitalRepository.existsById(contenidoDigital.getIdContenidoDigital())){
            Optional<ContenidoDigital> conte = contenidoDigitalRepository.findById(contenidoDigital.getIdContenidoDigital());

            conte.get().setDocenteAutor(contenidoDigital.getDocenteAutor());
            conte.get().setNombreContDigital(contenidoDigital.getNombreContDigital());
            conte.get().setVisibilidad(contenidoDigital.getVisibilidad());
            conte.get().setIdLinea(contenidoDigital.getIdLinea());
            conte.get().setEstado(contenidoDigital.getEstado());
            conte.get().setRecomendacion(contenidoDigital.getRecomendacion());
            conte.get().setFechaAprobacion(contenidoDigital.getFechaAprobacion());
            conte.get().setFechaCreacion(contenidoDigital.getFechaCreacion());
            contenidoDigitalRepository.save(conte.get());
            return "Contenido digital actualizado con éxito";
        }else{
            return null;
        }
    }
}
