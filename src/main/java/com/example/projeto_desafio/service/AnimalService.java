package com.example.projeto_desafio.service;

import com.example.projeto_desafio.dto.AnimalDTO;
import com.example.projeto_desafio.entity.Animal;
import com.example.projeto_desafio.exception.EntityNotFoundException;
import com.example.projeto_desafio.mapper.AnimalMapper;
import com.example.projeto_desafio.repository.AnimalRepository;
import com.example.projeto_desafio.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final CategoriaRepository categoriaRepository;

    public AnimalService(AnimalRepository animalRepository, CategoriaRepository categoriaRepository) {
        this.animalRepository = animalRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<AnimalDTO> listAll() {
        try {
            List<Animal> animals = animalRepository.findAll();
            return animals.stream()
                    .map(AnimalMapper::toDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            // Log the exception details
            System.out.println("Error occurred while fetching all animals: " + e.getMessage());
            throw e; // Rethrow to allow further handling if necessary
        }
    }

    public AnimalDTO findById(Integer id) {
        try {
            Animal animal = animalRepository.findById(id).orElseThrow(()-> new RuntimeException("Animal not found with ID: " + id));
            return AnimalMapper.toDTO(animal);
        } catch (Exception e) {
            System.out.println("Error occurred while fetching animal by ID: " + e.getMessage());
            throw e;
        }
    }

    public AnimalDTO save(AnimalDTO animal) {
        try {
             Animal createdAnimal =  AnimalMapper.toEntity(animal, this.categoriaRepository);
             animalRepository.save(createdAnimal);
             animal.setId(createdAnimal.getId());
             return animal;
        } catch (Exception e) {
            System.out.println("Error occurred while saving animal: " + e.getMessage());
            throw e;
        }
    }


    public void delete(Integer id) {
        try {
            if (!animalRepository.existsById(id)) {
                throw new EntityNotFoundException("Animal with ID " + id + " not found.");
            }
            animalRepository.deleteById(id);
        } catch (EntityNotFoundException error) {
            System.out.println(error.getMessage());
            throw error;
        } catch (Exception e) {
            System.out.println("Error occurred while deleting animal: " + e.getMessage());
            throw e;
        }
    }
}
