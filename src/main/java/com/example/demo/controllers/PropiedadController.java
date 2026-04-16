package com.example.demo.controllers;

import com.example.demo.entities.Propiedad;
import com.example.demo.repositories.PropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PropiedadController {

    @Autowired
    private PropiedadRepository propiedadRepository;

    // ==========================================
    //            SECCIÓN PÚBLICA
    // ==========================================

    @GetMapping("/publica/home")
public String mostrarHome() {
    return "publica/home";
}

@GetMapping("/publica/propiedades")
public String mostrarPropiedades(Model model) {
    model.addAttribute("propiedades", propiedadRepository.findAll());
    return "publica/propiedades";
}

@GetMapping("/publica/contacto")
public String mostrarContacto() {
    return "publica/contacto";
}

// Ruta por defecto (si entran a /publica que vaya al home)
@GetMapping("/publica")
public String index() {
    return "redirect:/publica/home";
}

    // ==========================================
    //          SECCIÓN ADMINISTRADOR
    // ==========================================

    @GetMapping("/admin/dashboard")
    public String panelAdmin(Model model) {
        model.addAttribute("propiedades", propiedadRepository.findAll());
        return "admin/dashboard";
    }

    @GetMapping("/admin/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("propiedad", new Propiedad());
        return "admin/formulario";
    }

    @PostMapping("/admin/guardar")
    public String guardar(@ModelAttribute Propiedad propiedad) {
        // Guarda o actualiza en la nube de Render
        propiedadRepository.save(propiedad);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        propiedadRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    // Lógica para actualizar valores de alquiler (Lo que hablamos para tu trabajo)
    @PostMapping("/admin/actualizar-precios")
    public String actualizarPrecios(@RequestParam Double porcentaje) {
        List<Propiedad> propiedades = propiedadRepository.findAll();
        
        for (Propiedad p : propiedades) {
            if ("Alquiler".equalsIgnoreCase(p.getTipo())) {
                Double nuevoPrecio = p.getPrecio() * (1 + (porcentaje / 100));
                p.setPrecio(nuevoPrecio);
                propiedadRepository.save(p);
            }
        }
        return "redirect:/admin/dashboard";
    }
}