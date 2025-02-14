import com.tienda.domain.Categoria; 
import org.springframework.data.jpa.repository.JpaRepository;
package com.tienda.dao;

/**
 Interface implementa metodos, no tiene logica, solamente implementa metodos
 */
public interface CategoriaDao extends JpaRepository<Categoria, Long>
{
    
}
