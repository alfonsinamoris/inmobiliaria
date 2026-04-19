package com.example.demo.controllers;  

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // Ruta para entrar al Panel de Control
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        // Acá podrías pasar la lista de propiedades para editarlas
        // model.addAttribute("propiedades", propiedadService.listarTodas());
        return "admin/dashboard"; // Busca el archivo en templates/admin/dashboard.html
    }

    // Ruta para mostrar tu formulario de Login personalizado
    @GetMapping("/login")
    public String login() {
        return "login"; // Busca el archivo en templates/login.html
    }
}

