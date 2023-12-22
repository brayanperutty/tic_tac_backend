package com.tictac.demo.controller;

import com.tictac.demo.entity.LineaTransversal;
import com.tictac.demo.service.LineaTransversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/linea-transversal")
public class LineaTransversalController {

    @Autowired
    LineaTransversalService lineaTransversalService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public LineaTransversal getLineaTransversal(@PathVariable Integer id){
        Optional<LineaTransversal> lineaTransversal = lineaTransversalService.getLineaTransversal(id);

        return lineaTransversal.get();
    }


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
