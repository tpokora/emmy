package org.tpokora.common.utils;

public interface IResolver<I, O> {

    public O resolve(I input);
}
