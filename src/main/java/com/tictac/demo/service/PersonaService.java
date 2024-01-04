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
        return personaRepository.save(persona);
    }

    public Persona editarPersona(Persona persona){
        Optional<Persona> personaOptional = personaRepository.findById(persona.getCedula());
        System.out.println(persona.getCedula());
        if (personaOptional.isPresent()){
            Persona p = personaOptional.get();
            p.setCedula(persona.getCedula());
            p.setNombre(persona.getNombre());
            p.setApellido(persona.getApellido());
            p.setPassword(persona.getPassword());
            p.setFechaNacimiento(persona.getFechaNacimiento());
            p.setIdRol(persona.getIdRol());
            p.setIdInstitucion(persona.getIdInstitucion());
            personaRepository.save(p);

            return p;
        }

        return null;
    }

    public void deletePersona(String id){
        personaRepository.deleteById(id);
    }

    public List<Persona> listPersona(){
        return personaRepository.findAll();
    }

    public Persona asignarRol(String cedula, Integer rol){
        Optional<Persona> p = personaRepository.findById(cedula);
        if(p.isPresent()){
            Persona persona = p.get();
            persona.setIdRol(rol);
            personaRepository.save(persona);
            return persona;
        }

        return null;
    }
}
