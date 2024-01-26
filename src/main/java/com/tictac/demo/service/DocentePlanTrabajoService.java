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

    public List<DocentePlanTrabajo> listDocentePlanTrabajo(Integer idActividadPlan){
       return docentePlanTrabajoRepository.findByIdActividadPlan(idActividadPlan);
    }

    public String deleteDocentePlanTrabajo(Integer idActividadPlan, String idDocente) {
        if (docentePlanTrabajoRepository.existsByIdActividadPlanAndIdDocente(idActividadPlan, idDocente)){
            docentePlanTrabajoRepository.deleteByIdActividadPlanAndIdDocente(idActividadPlan, idDocente);
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
