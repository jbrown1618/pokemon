package com.jbrown.pokemon.domain.dto;

public interface DtoMapper<T, S> {
    public S toDto(T t);
}
