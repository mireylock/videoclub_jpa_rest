package org.iesvdm.videoclub.exception;

public class CategoriaNotFoundException extends RuntimeException{
    public CategoriaNotFoundException(Long id) {
        super("Not found categoría con id: " +id);
    }
}
