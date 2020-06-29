package org.tpokora.weather.mapper;

public interface IJSONMapper<T> {

    public T map(String json);
}
