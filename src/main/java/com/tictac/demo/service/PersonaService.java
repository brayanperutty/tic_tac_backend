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

    public Persona savePersona(Persona persona){
        return personaRepository.save(persona);
    }

    public void deletePersona(String id){
        personaRepository.deleteById(id);
    }

    public List<Persona> listPersona(){
        return personaRepository.findAll();
    }
}
