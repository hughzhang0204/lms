package com.hugo.LMS.controller;

public interface BaseController <E extends Object, D extends Object> {

    public abstract D getDTO(E entity);

    public abstract E getObject(D dto);
}
