package com.example.sem8.entidad;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Alumnos")
@Data
public class Alumno {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 35, nullable = false)
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(min=2,max=35,message="El nombre debe tener entre 2 y 35 caracteres")
    private String nombre;
    @Column(length = 35, nullable = false)
    @NotBlank(message = "El apellido no puede estar en blanco")
    @Size(min=2,max=35,message="El apellido debe tener entre 2 y 35 caracteres")
    private String apellido;
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
    private String eMail;
    @Column(length = 80)
    private String direccion;
    @DecimalMax(value="20.0",message="El promedio no puede ser mayor a 20")
    @DecimalMin(value="0.0",message = "El promedio no puede ser menor que 0")
    private Double promedio;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "alumno",cascade = CascadeType.ALL)
    private List<Nota> notas;

}
