package com.jbrown.pokemon.dto;

import com.jbrown.pokemon.entities.Species;
import com.jbrown.pokemon.enums.Type;
import org.springframework.stereotype.Component;

public class SpeciesDto {
    private int number;
    private String name;
    private String type1;
    private String type2;

    public SpeciesDto(int number, String name, String type1, String type2) {
        this.number = number;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
    }

    @Component
    public static class Mapper implements DtoMapper<Species, SpeciesDto> {
        @Override
        public SpeciesDto toDto(Species species) {
            Type type1 = species.getType1();
            Type type2 = species.getType2();

            return new SpeciesDto(
                species.getNumber(),
                species.getName(),
                type1 == null ? null : type1.name(),
                type2 == null ? null : type2.name()
            );
        }
    }
}
