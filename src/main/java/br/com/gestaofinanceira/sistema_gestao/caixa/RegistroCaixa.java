package br.com.gestaofinanceira.sistema_gestao.caixa;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data   // Anotação do Lombok: cria getters, setters, toString, etc. automaticamente.
@Entity(name = "registros_caixa") // Anotação do JPA: indica que esta classe é uma tabela no banco de dados.
public class RegistroCaixa {

    @Id // Indica que este atributo é a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados irá gerar o valor do ID automaticamente.
    private Long id;

    @Enumerated(EnumType.STRING) // Diz ao JPA para salvar o texto ("ENTRADA" ou "SAIDA") no banco.
    private TipoMovimentacao tipo;

    private String descricao;

    private BigDecimal valor; // Dica de Mestre: Sempre use BigDecimal para valores financeiros!

    private LocalDate data;

    private LocalTime hora;




}
