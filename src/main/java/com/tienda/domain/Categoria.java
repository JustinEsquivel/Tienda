package com.tienda.dao;
import jakarta.persistence.*; 
import java.io.Serializable; 
import lombok.Data;
@Data
@Entity 
@Table(name="Categoria")/*Tabla a la que va a estar asociados los datos*/
public class Categoria implements Serializable /*Encargado de habilitar el auto increment*/
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_categoria")
    /*Autoincrementa y busca cual es el valor que queremos auto incrementar*/
    private Long idCategoria;
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
    public Categoria() 
    {
    }
    public Categoria(String categoria, boolean activo)
    {
        this.descripcion = categoria;
        this.activo = activo;
    }
    
}
