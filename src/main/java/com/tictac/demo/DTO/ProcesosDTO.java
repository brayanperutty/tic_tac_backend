package com.tictac.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcesosDTO {

    private String proceso;

    private Integer recurso;

    private Integer tiempo;
}
