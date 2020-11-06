package org.tpokora.services.common.utils;

public interface IResolver<I, O> {

    public O resolve(I input);
}
