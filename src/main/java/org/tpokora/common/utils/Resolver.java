package org.tpokora.common.utils;

public interface Resolver<I, O> {

    public O resolve(I input);
}
