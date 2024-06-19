package com.example.sem8.entidad;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="cursos")
@Data

public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @Min(value = 1,message = "El curso debe tener al menos 1 credito")
    @NotNull(message = "Los creditos deben tener al menos un valor")
    private Integer creditos;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDocente",
        nullable = false,
        referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "fk_docentes_cursos"))
    private Docente docente;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "curso",cascade = CascadeType.ALL)
    private List<Nota> notas;
    public Curso(){
        this.docente=new Docente();
    }

}
