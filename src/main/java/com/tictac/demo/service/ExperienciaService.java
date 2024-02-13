package com.tictac.demo.service;

import com.tictac.demo.DTO.ByteArrayMultipartFile;
import com.tictac.demo.DTO.ExperienciaDTO;
import com.tictac.demo.entity.Experiencia;
import com.tictac.demo.entity.EvidenciaExperiencia;
import com.tictac.demo.repository.ExperienciaRepository;
import com.tictac.demo.repository.EvidenciaExperienciaRepository;
import com.tictac.demo.util.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ExperienciaService {

    @Autowired
    ExperienciaRepository experienciaRepository;

    @Autowired
    EvidenciaExperienciaRepository evidenciaExperienciaRepository;

    @Autowired
    CloudinaryService cloudinaryService;

    List<Object> listExperiencias = new ArrayList<>();

    public String createExperiencia(ExperienciaDTO experiencia){

        Experiencia e = new Experiencia();

        e.setNombre(experiencia.getNombreExperiencia());
        e.setDescripcion(experiencia.getDescripcion());
        e.setIdLinea(experiencia.getLineaPPT());
        experienciaRepository.save(e);
        for(int i = 0; i < experiencia.getImages().size(); i++){
            //byte[] decodedBytes = Base64.getDecoder().decode(experiencia.getImages().get(i));
            //String filename = "evidencia"+i+1;
            EvidenciaExperiencia image = new EvidenciaExperiencia();

            //try {
                //MultipartFile multipartFile = new ByteArrayMultipartFile(filename, filename, "application/octet-stream", decodedBytes);
                //image.setRecurso(cloudinaryService.upload(multipartFile).get("url").toString());
                image.setRecurso(experiencia.getImages().get(i));
                image.setIdExperiencia(e.getId());
                evidenciaExperienciaRepository.save(image);

            //} catch (IOException ex) {
              //  throw new RuntimeException(ex);
            //}
        }

        return "Experiencia creada con éxito";
    }

    public List<Object> listExperiencias(){
        listExperiencias.clear();

        experienciaRepository.listExperiencia().forEach(e -> {

            Map<String, Object> experiencia = new LinkedHashMap<>();

            List<String> listEvidencias = new ArrayList<>();

            experiencia.put("nombre_experiencia", e[1]);
            experiencia.put("descripcion", e[2]);

            experienciaRepository.listExperienciaEvidencias(Integer.parseInt(e[0].toString())).forEach(ev ->{
                listEvidencias.add(ev[0].toString());
            });
            experiencia.put("evidencias", listEvidencias);
            listExperiencias.add(experiencia);
        });
        return listExperiencias;
    }

}
