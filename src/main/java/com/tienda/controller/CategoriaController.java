package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller //funciona como controlador
@RequestMapping("/categoria") //se va a reservar una URL dentro del sitio principal
public class CategoriaController {
  
    @Autowired //venir y manipular el objeto de categoriaService
    private CategoriaService categoriaService;
    
    @GetMapping("/listado") //crea la URL de listado dentro de /categoria
    private String listado(Model model) { //utiliza el SpringFramework.ui.model que sirve para obtener información de una interacción con la interfaz
        var categorias = categoriaService.getCategorias(false); // variable categoria que obteniene todas las categorias
        model.addAttribute("categorias", categorias); //llama al Framework para agregar un atributo de categorias
        model.addAttribute("totalCategorias",categorias.size()); //llama al Framework para devolver el .size o un tamaño, devuele cuantas categorias encontró
        return "/categoria/listado";  //retorna la misma información
    } // lo que está en naranja es codigo de vista y el resto es codigo de controlador
    
    @GetMapping("/nuevo")
    public String categoriaNuevo(Categoria categoria) {
        return "/categoria/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String categoriaGuardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            categoriaService.save(categoria);
            categoria.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "categoria", 
                            categoria.getIdCategoria()));
        }
        categoriaService.save(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{idCategoria}")
    public String categoriaEliminar(Categoria categoria) {
        categoriaService.delete(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{idCategoria}")
    public String categoriaModificar(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "/categoria/modifica";
    }
}