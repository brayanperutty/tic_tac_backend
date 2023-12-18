package com.tictac.demo.service;

import com.tictac.demo.entity.LineaTransversal;
import com.tictac.demo.repository.LineaTransversalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaTransversalService {

    @Autowired
    LineaTransversalRepository lineaTransversalRepository;

    public LineaTransversal saveLineaTransversal(LineaTransversal lineaTransversal){
        return lineaTransversalRepository.save(lineaTransversal);
    }

    public void deleteLineaTransversal(Integer id){
        lineaTransversalRepository.deleteById(id);
    }

    public List<LineaTransversal> listLineaTransversal(){
        return lineaTransversalRepository.findAll();
    }
}
