package com.example.demo.Product;

import com.example.demo.Product.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // To tell Spring Boot that this is repository(interface), hence it extends the following
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Lecture 4 - 4 things to do-
    // Fully complete our CRUD operations this time.
    // Create Java Object -> Hold the data and map to db.
    // Create Table in MySQL -> to store the data
    // Create Repository Interface -> acts as bridge between java code and sql operations.
    // -------------------------

    // This Jp Repository implementation is gonna give us so many methods already to implement CRUD.
    // The functions are simple, we can check them by clicking on JpaRepository and see its code.
    // The JpaRepository extends ListCrudRepository, which contains findAll(), findAllById() methods.
    // Which are also being used in that project I saw (Flight management system).

    // Basically we dont need to write such small sqls, this Jpa basically
    // writes the SQL query for us in the background.
    // We will dive deep into the course later where we would implement custom SQLs.


}
