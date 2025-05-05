package proyecto.bookcontrol.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String titulo;

    private String portada; 

    @Column(length = 1000)
    private String descripcion;

    @NotEmpty
    private String autor;

    private String genero;

    @Column(name = "valoracion_media")
    private Double valoracionMedia; //La API me permite ver la valoracion media
    

    @Column(name = "enlace_lectura")
    private String enlaceLectura; //Esto lo voy a usar para coger de una API y poder leer el libro



    public Libro() {}

    public Libro(String titulo, String portada, String descripcion, String autor, String genero, String enlaceLectura) {
        this.titulo = titulo;
        this.portada = portada;
        this.descripcion = descripcion;
        this.autor = autor;
        this.genero = genero;
        this.enlaceLectura = enlaceLectura;
    }
    
}
