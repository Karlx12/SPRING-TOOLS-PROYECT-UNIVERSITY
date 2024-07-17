package com.example.sem8.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Docentes")
@Data
public class Docente {

        @Id
        @GeneratedValue
        private Long id;
        @Column(length = 35, nullable = false)
        @NotBlank(message = "El nombre no puede estar en blanco")
        @Size(min=2,max=35,message="El nombre debe tener entre 2 y 35 caracteres")
        private String nombres;
        @Column(length = 35, nullable = false)
        @NotBlank(message = "El apellido no puede estar en blanco")
        @Size(min=2,max=35,message="El apellido debe tener entre 2 y 35 caracteres")
        private String apellidos;
        @Column(length = 8, nullable = false, unique = true)
        @NotBlank(message = "El DNI no puede estar en blanco")
        @Size(min=8,max=8,message="El DNI debe tener entre 2 y 35 caracteres")
        private String dni;
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern="yyyy-MM-dd")
        @Past(message="El nacimiento debe ser anterior a la fecha actual")
        private Date fechaNacimiento;
        @Column(length = 9, nullable = false)
        @NotBlank(message = "El celular no puede estar en blanco")
        @Size(min=9,max=9,message="El celular debe tener entre 2 y 35 caracteres")
        private String celular;

        @Column(length = 80)
        @NotBlank(message = "El eMail no puede estar en blanco")
        @Email(message="Debe ingresar un correo válido")
        private String email;
        @Column(length = 80)
        private String direccion;
        @DateTimeFormat(iso=ISO.DATE)
        @Past(message="El ingreso debe ser anterior a la fecha actual")
        private Date fechaIngreso;
        @Column(length = 50)
        private String especialidad;
        @Temporal(TemporalType.TIMESTAMP)
        private Date creationTime;
        @Temporal(TemporalType.TIMESTAMP)
        private Date modificationTime;
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "docente",cascade = CascadeType.ALL)
        private List<Curso> cursos;


}
