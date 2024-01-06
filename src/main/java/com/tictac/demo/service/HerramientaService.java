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
        if(herramienta.getIdTema() == null || herramienta.getIdTema().toString().trim().isEmpty() ||
            herramienta.getDocenteAutor() == null || herramienta.getDocenteAutor().trim().isEmpty() ||
            herramienta.getNombreHerramienta() == null || herramienta.getNombreHerramienta().trim().isEmpty() ||
            herramienta.getObjetivos() == null || herramienta.getObjetivos().trim().isEmpty() ||
            herramienta.getVisibilidad() == null || herramienta.getVisibilidad().toString().trim().isEmpty() ||
            herramienta.getComentarios() == null || herramienta.getComentarios().trim().isEmpty() ||
            herramienta.getEstado() == null || herramienta.getEstado().trim().isEmpty() ||
            herramienta.getRecomendacion() == null || herramienta.getRecomendacion().trim().isEmpty() ||
            herramienta.getFechaAprobacion() == null || herramienta.getFechaAprobacion().toString().trim().isEmpty() ||
            herramienta.getFechaCreacion() == null || herramienta.getFechaCreacion().toString().trim().isEmpty()){
            return null;
        }else{
            return herramientaRepository.save(herramienta);
        }

    }

    public String deleteHerramienta(Integer id){
        if(herramientaRepository.existsById(id)){
            herramientaRepository.deleteById(id);
            return "herramienta eliminada con éxito";
        }else {
            return null;
        }
    }

    public String updateHerramienta(Herramienta herramienta){
        if(herramientaRepository.existsById(herramienta.getIdHerramienta())){
            Optional<Herramienta> h = herramientaRepository.findById(herramienta.getIdHerramienta());

            h.get().setIdTema(herramienta.getIdTema());
            h.get().setDocenteAutor(herramienta.getDocenteAutor());
            h.get().setNombreHerramienta(herramienta.getNombreHerramienta());
            h.get().setObjetivos(herramienta.getObjetivos());
            h.get().setVisibilidad(herramienta.getVisibilidad());
            h.get().setComentarios(herramienta.getComentarios());
            h.get().setEstado(herramienta.getEstado());
            h.get().setRecomendacion(herramienta.getRecomendacion());
            h.get().setFechaAprobacion(herramienta.getFechaAprobacion());
            h.get().setFechaCreacion(herramienta.getFechaCreacion());
            herramientaRepository.save(h.get());
            return "Herramienta actualizada con éxito";
        }else{
            return null;
        }
    }

    public List<Herramienta> listHerramienta(){
        return herramientaRepository.findAll();
    }
}
