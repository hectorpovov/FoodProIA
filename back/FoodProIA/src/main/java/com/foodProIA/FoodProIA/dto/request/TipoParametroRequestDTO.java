package com.foodProIA.FoodProIA.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo"
)
@JsonSubTypes({
    @JsonSubTypes.Type(
        value = TipoParametroBooleanoRequestDTO.class,
        name = "BOOLEANO"
    ),
    @JsonSubTypes.Type(
        value = TipoParametroNumericoRequestDTO.class,
        name = "NUMERICO"
    ),
    @JsonSubTypes.Type(
        value = TipoParametroTextualRequestDTO.class,
        name = "TEXTUAL"
    )
})
public abstract class TipoParametroRequestDTO {

    @NotBlank
    private String nome;
}