package com.example.sem8.entidad;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@IdClass(NotaID.class)
@Table(name = "notas")
@Data
public class Nota {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumno",referencedColumnName = "id",nullable = false,foreignKey = @ForeignKey(name = "fk_alumnos_notas"))
    private Alumno alumno;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCurso",referencedColumnName = "id",nullable = false,foreignKey = @ForeignKey(name = "fk_cursos_notas"))
    private Curso curso;
    private Double unidad1;
    private Double unidad2;
    private Double unidad3;


}
