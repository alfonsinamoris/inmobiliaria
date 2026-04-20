package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repositories.PropiedadRepository;

@Controller
public class PropiedadController {

    @Autowired
    private PropiedadRepository propiedadRepository;

    // ==========================================
    //            SECCIÓN PÚBLICA
    // ==========================================

    // Ruta raíz → redirige al home público
    @GetMapping("/")
    public String index() {
        return "redirect:/publica/home";
    }

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
}