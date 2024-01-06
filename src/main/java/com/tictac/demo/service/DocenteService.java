package com.tictac.demo.service;

import com.tictac.demo.entity.Docente;
import com.tictac.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    DocenteRepository docenteRepository;

    public Optional<Docente> getDocente(String id){
        return docenteRepository.findById(id);
    }

    public Docente saveDocente(Docente docente){
        if(docente.getNumeroProyectosSociales() == null || docente.getNumeroProyectosSociales().toString().trim().isEmpty() ||
            docente.getNumeroProyectosSexualidad() == null || docente.getNumeroProyectosSexualidad().toString().isEmpty() ||
            docente.getNumeroProyectosAmbiental() == null || docente.getNumeroProyectosAmbiental().toString().trim().isEmpty() ||
            docente.getNumeroProyectosEmprendimiento() == null || docente.getNumeroProyectosEmprendimiento().toString().trim().isEmpty() ||
            docente.getNumeroProyectosTic() == null || docente.getNumeroProyectosTic().toString().trim().isEmpty() ||
                docente.getNumeroContenidosSociales() == null || docente.getNumeroContenidosSociales().toString().trim().isEmpty() ||
                docente.getNumeroContenidosSexualidad() == null || docente.getNumeroContenidosSexualidad().toString().trim().isEmpty() ||
                docente.getNumeroContenidosAmbiental() == null || docente.getNumeroContenidosAmbiental().toString().trim().isEmpty() ||
                docente.getNumeroContenidosEmprendimiento() == null || docente.getNumeroContenidosEmprendimiento().toString().trim().isEmpty() ||
                docente.getNumeroContenidosTic() == null || docente.getNumeroContenidosTic().toString().trim().isEmpty() ||
                    docente.getNumeroHerramientasSociales() == null || docente.getNumeroHerramientasSociales().toString().trim().isEmpty() ||
                    docente.getNumeroHerramientasSexualidad() == null || docente.getNumeroHerramientasSexualidad().toString().trim().isEmpty() ||
                    docente.getNumeroHerramientasAmbiental() ==  null || docente.getNumeroHerramientasAmbiental().toString().trim().isEmpty() ||
                    docente.getNumeroHerramientasEmprendimiento() == null || docente.getNumeroHerramientasEmprendimiento().toString().trim().isEmpty() ||
                    docente.getNumeroHerramientasTic() == null || docente.getNumeroHerramientasTic().toString().trim().isEmpty()){
            return null;
        }else {
            return docenteRepository.save(docente);
        }
    }

    public String deleteDocente(String id){
        if(docenteRepository.existsById(id)){
            docenteRepository.deleteById(id);
            return "Docente eliminado con éxito";
        }else{
            return null;
        }
    }

    public String updateDocente(Docente docente) {
        if (docenteRepository.existsById(docente.getIdDocente())){
            Optional<Docente> d = docenteRepository.findById(docente.getIdDocente());

        d.get().setNumeroProyectosSociales(docente.getNumeroProyectosSociales());
        d.get().setNumeroProyectosSexualidad(docente.getNumeroProyectosSexualidad());
        d.get().setNumeroProyectosAmbiental(docente.getNumeroProyectosAmbiental());
        d.get().setNumeroProyectosEmprendimiento(docente.getNumeroProyectosEmprendimiento());
        d.get().setNumeroProyectosTic(docente.getNumeroProyectosTic());
            d.get().setNumeroContenidosSociales(docente.getNumeroContenidosSociales());
            d.get().setNumeroContenidosSexualidad(docente.getNumeroContenidosSexualidad());
            d.get().setNumeroContenidosAmbiental(docente.getNumeroContenidosAmbiental());
            d.get().setNumeroContenidosEmprendimiento(docente.getNumeroContenidosEmprendimiento());
            d.get().setNumeroContenidosTic(docente.getNumeroContenidosTic());
                d.get().setNumeroHerramientasSociales(docente.getNumeroHerramientasSociales());
                d.get().setNumeroHerramientasSexualidad(docente.getNumeroHerramientasSexualidad());
                d.get().setNumeroHerramientasAmbiental(docente.getNumeroHerramientasAmbiental());
                d.get().setNumeroHerramientasEmprendimiento(docente.getNumeroHerramientasEmprendimiento());
                d.get().setNumeroHerramientasTic(docente.getNumeroHerramientasTic());
        docenteRepository.save(d.get());
        return "Docente actualizado con éxito";
        }
        else{
            return null;
        }
    }

    public List<Docente> listDocente(){
        return docenteRepository.findAll();
    }
}
