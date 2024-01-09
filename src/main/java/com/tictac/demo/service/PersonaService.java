package com.tictac.demo.service;

import com.tictac.demo.entity.Persona;
import com.tictac.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    public Optional<Persona> getPersona(String cedula){
        return personaRepository.findById(cedula);
    }

    public List<Persona> getPersonaByInstitucion(String institucion){
        return personaRepository.findByIdInstitucion(Integer.parseInt(institucion));
    }

    public Persona savePersona(Persona persona){
        if(persona.getNombre() == null || persona.getNombre().trim().isEmpty() ||
            persona.getApellido() == null || persona.getApellido().trim().isEmpty() ||
            persona.getPassword() == null || persona.getApellido().trim().isEmpty() ||
            persona.getFechaNacimiento() == null || persona.getFechaNacimiento().toString().trim().isEmpty() ||
            persona.getCodigo() == null || persona.getCodigo().trim().isEmpty() ||
            persona.getIdRol() == null || persona.getIdRol().toString().trim().isEmpty() ||
            persona.getIdInstitucion() == null || persona.getIdInstitucion().toString().trim().isEmpty()){
            return null;
        }else{
            return personaRepository.save(persona);
        }
    }

    public String updatePersona(Persona persona){
        if(personaRepository.existsById(persona.getCedula())){
            Optional<Persona> personaOptional = personaRepository.findById(persona.getCedula());

                Optional<Persona> p = personaRepository.findById(persona.getCedula());
                p.get().setNombre(persona.getNombre());
                p.get().setApellido(persona.getApellido());
                p.get().setPassword(persona.getPassword());
                p.get().setFechaNacimiento(persona.getFechaNacimiento());
                p.get().setIdRol(persona.getIdRol());
                p.get().setIdInstitucion(persona.getIdInstitucion());
                personaRepository.save(p.get());
                return "Persona actualizada con éxito";
        }else{
            return null;
        }
    }

    public String deletePersona(String id){
        if(personaRepository.existsById(id)){
            personaRepository.deleteById(id);
            return "Persona eliminada con éxito";
        }else{
            return null;
        }
    }

    public List<Persona> listPersona(){
        return personaRepository.findAll();
    }

    public String asignarRol(String cedula, Integer rol){
        if(personaRepository.existsById(cedula)){
            Optional<Persona> p = personaRepository.findById(cedula);
                p.get().setIdRol(rol);
                personaRepository.save(p.get());
                return "Rol de persona actualizada con éxito";
        }else{
            return null;
        }
    }

    public Optional<Persona> loginPersona(String codigo, String password, Integer idRol){
        return personaRepository.findByCodigoAndPasswordAndIdRol(codigo, password, idRol);
    }
}
