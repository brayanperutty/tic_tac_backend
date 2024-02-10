package com.tictac.demo.service;

import com.tictac.demo.entity.Docente;
import com.tictac.demo.entity.Persona;
import com.tictac.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    DocenteService docenteService;

    Map<String, Object> datos = new LinkedHashMap<>();

    public Optional<Persona> getPersona(String cedula){
        return personaRepository.findById(cedula);
    }

    public List<Persona> getPersonaByInstitucion(String institucion){
        return personaRepository.findByIdInstitucion(Integer.parseInt(institucion));
    }

    public String savePersona(Persona persona){
        if(persona.getNombre() == null || persona.getNombre().trim().isEmpty() ||
            persona.getApellido() == null || persona.getApellido().trim().isEmpty() ||
            persona.getPassword() == null || persona.getApellido().trim().isEmpty() ||
            persona.getFechaNacimiento() == null || persona.getFechaNacimiento().toString().trim().isEmpty() ||
            persona.getCodigo() == null || persona.getCodigo().trim().isEmpty() ||
            persona.getIdRol() == null || persona.getIdRol().toString().trim().isEmpty() ||
            persona.getIdInstitucion() == null || persona.getIdInstitucion().toString().trim().isEmpty()){
            return null;
        }else{
            personaRepository.save(persona);
            Docente docente = new Docente();
            docente.setIdDocente(persona.getCedula());
            docenteService.saveDocente(docente);
            return "Docente creado con éxito";
        }
    }

    public String updatePersona(Persona persona){
        if(personaRepository.existsById(persona.getCedula())){
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

    public String asignarRol(String codigoDocente, Boolean estado){
        Optional<Persona> p = personaRepository.findByCodigo(codigoDocente);
        if(estado){
                p.get().setIdRol(2);
                personaRepository.save(p.get());
        }else{
            p.get().setIdRol(3);
            personaRepository.save(p.get());
        }

        return "Rol de docente actualizado con éxito";
    }

    public Map<String, Object> loginPersona(String codigo, String password, Integer idRol){
        datos.clear();
        personaRepository.findByCodigoAndPasswordAndIdRol(codigo, password, idRol).forEach(p -> {

            datos.put("id_institucion", p[0]);
            datos.put("nombre_institucion", p[1]);
            datos.put("nombre_docente", p[2]);
            datos.put("rol", p[3]);
        });

        return datos;
    }

    public List<Object> listDocentes(Integer idInstitucion){

        List<Object> listDocentes = new ArrayList<>();

        personaRepository.listDocentesInstitucion(idInstitucion).forEach(d -> {

            Map<String, Object> datosDocente = new LinkedHashMap<>();
            datosDocente.put("nombre_docente", d[0]);
            datosDocente.put("codigo", d[1]);
            datosDocente.put("rol", d[2]);
            listDocentes.add(datosDocente);
        });
        return listDocentes;
    }
}
