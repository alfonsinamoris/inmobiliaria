package com.example.demo.controllers;

import com.example.demo.entities.Propiedad;
import com.example.demo.repositories.PropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private PropiedadRepository propiedadRepository;

    // ==========================================
    //          SECCIÓN ADMINISTRADOR
    // ==========================================

    // Ruta para mostrar tu formulario de Login personalizado
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Panel principal: lista todas las propiedades
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("propiedades", propiedadRepository.findAll());
        return "admin/dashboard";
    }

    // Formulario para cargar una propiedad nueva
    @GetMapping("/admin/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("propiedad", new Propiedad());
        return "admin/formulario";
    }

    // Formulario para editar una propiedad existente
    @GetMapping("/admin/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("propiedad", propiedad);
        return "admin/formulario";
    }

    // Guarda o actualiza una propiedad (nuevo y editar usan el mismo formulario)
    @PostMapping("/admin/guardar")
    public String guardar(@ModelAttribute Propiedad propiedad) {
        propiedadRepository.save(propiedad);
        return "redirect:/admin/dashboard";
    }

    // Elimina una propiedad por ID
    @GetMapping("/admin/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        propiedadRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    // Actualiza precios de alquileres por porcentaje
    @PostMapping("/admin/actualizar-precios")
    public String actualizarPrecios(@RequestParam Double porcentaje) {
        List<Propiedad> propiedades = propiedadRepository.findAll();
        List<Propiedad> alquileres = new java.util.ArrayList<>();

        for (Propiedad p : propiedades) {
            if ("Alquiler".equalsIgnoreCase(p.getTipo())) {
                p.setPrecio(p.getPrecio() * (1 + (porcentaje / 100)));
                alquileres.add(p);
            }
        }
        propiedadRepository.saveAll(alquileres); // una sola llamada en vez de N
        return "redirect:/admin/dashboard";
    }
}