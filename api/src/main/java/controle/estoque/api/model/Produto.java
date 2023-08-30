package controle.estoque.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer estoqueMinimo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Double valor;
    @NotNull
    @Column
    private Integer saldo;
    @Column
    private String status;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Entrada> entradas = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Saida> saidas = new ArrayList<>();

    public void checkStatus(){
        if(saldo==0){
            status = "Sem estoque";
        } else if (saldo<=estoqueMinimo) {
            status = "Estoque Perigoso";
        }
        else{
            status = "Estoque Confortável";
        }
    }
}
