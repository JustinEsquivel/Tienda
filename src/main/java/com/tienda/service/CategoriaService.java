import com.tienda.domain.Categoria; 
import java.util.List;
package com.tienda.service;

public interface CategoriaService 
{
    //se obtiene listado de la BD
    public List<Categoria> getCategorias(boolean activo);
    //trae todas las categorias que estan activas
    
}
