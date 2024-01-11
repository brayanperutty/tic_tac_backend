package com.tictac.demo.service;

import com.tictac.demo.entity.Rol;
import com.tictac.demo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    public RolRepository rolRepository;

    public Optional<Rol> getRol(Integer id){
        return rolRepository.findById(id);
    }
    public Integer getRolByNombre(String nombre){
        if(nombre == null || nombre.trim().isEmpty()){
            return null;
        }else{
            Rol rol = rolRepository.findByNombre(nombre);
            return rol.getIdRol();
        }
    }

    public Rol saveRol(Rol rol){
        if(rol.getNombre() == null || rol.getNombre().trim().isEmpty()){
            return null;
        }else {
            return rolRepository.save(rol);
        }
    }

    public String updateRol(Rol rol){
        if(rolRepository.existsById(rol.getIdRol())){
            Optional<Rol> r = rolRepository.findById(rol.getIdRol());

            r.get().setNombre(rol.getNombre());
            rolRepository.save(r.get());
            return "Rol actualizado con éxito";
        }else{
            return null;
        }
    }

    public String deleteRol(Integer id){
        if(rolRepository.existsById(id)){
            rolRepository.deleteById(id);
            return "Rol eliminado con éxito";
        }else {
            return null;
        }
    }

    public List<Rol> rolList(){
        return rolRepository.findAll();
    }
}
