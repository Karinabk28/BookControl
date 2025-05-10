package proyecto.bookcontrol.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Min(1)
    @Max(5)
    private int valoracion; 

    @Column(length = 1000)
    private String comentario;

    public Review() {}

    public Review(Libro libro, Usuario usuario, int valoracion, String comentario) {
        this.libro = libro;
        this.usuario = usuario;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }
}
