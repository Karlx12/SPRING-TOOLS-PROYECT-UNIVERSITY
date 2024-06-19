package com.example.sem8.entidad;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotaID implements Serializable {
    private static final long serialVersionUID=1L;
    private Long alumno;
    private Long curso;
    public NotaID(){

    }
    public NotaID(Long alumno, Long curso){
        super();
        this.alumno=alumno;
        this.curso=curso;
    }

}
