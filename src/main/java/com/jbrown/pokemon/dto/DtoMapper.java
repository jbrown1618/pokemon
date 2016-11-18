package com.jbrown.pokemon.dto;

public interface DtoMapper<T, S> {
    public S toDto(T t);
}
