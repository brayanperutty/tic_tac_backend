package com.tictac.demo.service;

import com.tictac.demo.entity.Rol;
import com.tictac.demo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    public RolRepository rolRepository;

    public Rol saveRol(Rol rol){
        return rolRepository.save(rol);
    }

    public void deleteRol(Integer id){
        rolRepository.deleteById(id);
    }

    public List<Rol> rolList(){
        return rolRepository.findAll();
    }
}
