package com.tictac.demo.service;

import com.tictac.demo.DTO.experiencia.ByteArrayMultipartFile;
import com.tictac.demo.entity.Informe;
import com.tictac.demo.repository.InformeRepository;
import com.tictac.demo.util.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@Service
public class InformeService {

    @Autowired
    InformeRepository informeRepository;

    @Autowired
    CloudinaryService cloudinaryService;

    Map<String, Object> datos = new LinkedHashMap<>();

    public String createInforme(Informe informe) throws IOException {

        Informe i = new Informe();

        byte[] decodedBytes = Base64.getDecoder().decode(informe.getRecurso());
        String filename = ""+informe.getNombre();
        MultipartFile multipartFile = new ByteArrayMultipartFile(filename, filename, "application/octet-stream", decodedBytes);

        i.setNombre(informe.getNombre());
        i.setRecurso(cloudinaryService.upload(multipartFile).get("url").toString());
        Date fechaActual = new Date();
        i.setFecha(fechaActual);
        i.setDocenteAutor(informe.getDocenteAutor());

        informeRepository.save(i);

        return "Informe creado con éxito";
    }

    public Optional<Informe> getInforme(Integer id){
        return informeRepository.findById(id);
    }

    public List<Informe> listInforme(String idDocente){
        return informeRepository.informesDocente(idDocente);
    }

    public List<Object> listInformesInstitucion(Integer idInstitucion){

        List<Object> informes = new ArrayList<>();
        informeRepository.informesInstitucion(idInstitucion).forEach(i -> {

            Map<String, Object> informe = new LinkedHashMap<>();

            informe.put("nombreInforme", i[0]);
            informe.put("fecha", i[1]);
            informe.put("recurso", i[2]);
            informe.put("docente_autor", i[3]);

            informes.add(informe);
        });

        return informes;
    }
}
