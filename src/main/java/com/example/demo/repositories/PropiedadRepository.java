package com.example.demo.repositories;

import com.example.demo.entities.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
    // Con esto heredás todos los métodos como .save(), .findAll(), .delete()
}
