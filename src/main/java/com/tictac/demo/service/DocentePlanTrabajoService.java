package com.tictac.demo.service;

import com.tictac.demo.entity.DocentePlanTrabajo;
import com.tictac.demo.repository.DocentePlanTrabajoRepository;
import com.tictac.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DocentePlanTrabajoService {

    @Autowired
    DocentePlanTrabajoRepository docentePlanTrabajoRepository;

    @Autowired
    PersonaRepository personaRepository;
    Map<String, String> docente = new HashMap<>();
    List<Map<String, String>> listDocentes = new ArrayList<>();

    public List<Map<String, String>> getDocentePlanTrabajo(Integer idActividadPlan){
       listDocentes.clear();
        docente.clear();

        List<DocentePlanTrabajo> d = docentePlanTrabajoRepository.findByIdActividadPlan(idActividadPlan);
        for (DocentePlanTrabajo docentePlanTrabajo : d) {
            docente.put("nombre", ""+personaRepository.findById(docentePlanTrabajo.getIdDocente()));
            listDocentes.add(docente);
        }

        return listDocentes;
    }

    public String deleteDocentePlanTrabajo(String id) {
        if (docentePlanTrabajoRepository.existsById(id)){
            docentePlanTrabajoRepository.deleteById(id);
            return "Docente de este plan de trabajo eliminado con éxito";
        }else{
            return null;
        }
    }

    public DocentePlanTrabajo createDocentePlanTrabajo(DocentePlanTrabajo docentePlanTrabajo){
        if(docentePlanTrabajo.getIdDocente() == null || docentePlanTrabajo.getIdDocente().trim().isEmpty() ||
            docentePlanTrabajo.getIdActividadPlan() == null || docentePlanTrabajo.getIdActividadPlan().toString().trim().isEmpty()){
            return null;
        }else{
            return docentePlanTrabajoRepository.save(docentePlanTrabajo);
        }
    }


}
