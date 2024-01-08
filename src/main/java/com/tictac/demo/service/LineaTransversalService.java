package com.tictac.demo.service;

import com.tictac.demo.entity.LineaTransversal;
import com.tictac.demo.repository.LineaTransversalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineaTransversalService {

    @Autowired
    LineaTransversalRepository lineaTransversalRepository;

    public Optional<LineaTransversal> getLineaTransversal(Integer id){
        return lineaTransversalRepository.findById(id);
    }
    public LineaTransversal saveLineaTransversal(LineaTransversal lineaTransversal){
        if(lineaTransversal.getNombre() == null || lineaTransversal.getNombre().trim().isEmpty()){
            return null;
        }else{
            return lineaTransversalRepository.save(lineaTransversal);
        }
    }

    public String updateLineaTransversal(LineaTransversal lineaTransversal){
        if(lineaTransversalRepository.existsById(lineaTransversal.getIdLinea())){
            Optional<LineaTransversal> l = lineaTransversalRepository.findById(lineaTransversal.getIdLinea());

            l.get().setNombre(lineaTransversal.getNombre());
            lineaTransversalRepository.save(l.get());
            return "Linea transversal actualizada con éxito";
        }else{
            return null;
        }
    }

    public String deleteLineaTransversal(Integer id){
        if(lineaTransversalRepository.existsById(id)){
            lineaTransversalRepository.deleteById(id);
            return "Línea transversal elinada con éxito";
        }else{
            return null;
        }
    }

    public List<LineaTransversal> listLineaTransversal(){
        return lineaTransversalRepository.findAll();
    }
}
