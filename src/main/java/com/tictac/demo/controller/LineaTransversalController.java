package com.tictac.demo.controller;

import com.tictac.demo.entity.LineaTransversal;
import com.tictac.demo.service.LineaTransversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/linea-transversal")
public class LineaTransversalController {

    @Autowired
    LineaTransversalService lineaTransversalService;

    @PostMapping("/create")
    @ResponseBody
    public LineaTransversal saveLineaTransversal(@RequestBody LineaTransversal lineaTransversal){
        return lineaTransversalService.saveLineaTransversal(lineaTransversal);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLineaTransversal(@PathVariable Integer id){
        lineaTransversalService.deleteLineaTransversal(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<LineaTransversal> listLineaTransversal(){
        return lineaTransversalService.listLineaTransversal();
    }
}
