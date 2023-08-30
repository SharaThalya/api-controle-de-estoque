package controle.estoque.api.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "saida")
public class Saida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;
    @Column(nullable = false)
    private LocalDate dataSaida;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(nullable = false)
    private boolean isEstornado;
}
