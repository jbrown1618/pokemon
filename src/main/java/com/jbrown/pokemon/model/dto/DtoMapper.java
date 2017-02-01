package com.jbrown.pokemon.model.dto;

public interface DtoMapper<T, S> {
    public S toDto(T t);
}
