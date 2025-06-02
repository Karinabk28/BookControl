package proyecto.bookcontrol.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Listas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Libro libro;

    @Enumerated(EnumType.STRING)
    private EstadoLectura estadoLectura;

    private boolean favorito = false;
}
