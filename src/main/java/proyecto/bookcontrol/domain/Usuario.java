package proyecto.bookcontrol.domain;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")

@Entity
public class Usuario {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String nombre;

    @Column(unique = true)
    @NotEmpty
    @Email 
    private String email;
    
    @Size(min = 4)
    private String clave;

    @Enumerated(EnumType.STRING)
    private Rol rol;


    @NotNull
    private LocalDate fechaRegistro;

    public Usuario(@NotEmpty String nombre, String clave, Rol rol, String email) {
        this.nombre = nombre;
        this.clave = clave;
        this.rol = rol;
        this.email = email;
        this.fechaRegistro = LocalDate.now();
    }

    public Usuario() {
        this.fechaRegistro = LocalDate.now();
    }
    
}