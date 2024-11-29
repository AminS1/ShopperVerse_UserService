package org.example.shopperverse_userservice.Models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
abstract public class BaseModel {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    Long id;
}