package com.example.projeto_desafio.service;

import com.example.projeto_desafio.entity.Animal;
import com.example.projeto_desafio.exception.EntityNotFoundException;
import com.example.projeto_desafio.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> listAll() {
        try {
            return animalRepository.findAll();
        } catch (Exception e) {
            // Log the exception details
            System.out.println("Error occurred while fetching all animals: " + e.getMessage());
            throw e; // Rethrow to allow further handling if necessary
        }
    }

    public Optional<Animal> findById(Integer id) {
        try {
            return animalRepository.findById(id);
        } catch (Exception e) {
            System.out.println("Error occurred while fetching animal by ID: " + e.getMessage());
            throw e;
        }
    }

    public Animal save(Animal animal) {
        try {
            return animalRepository.save(animal);
        } catch (Exception e) {
            System.out.println("Error occurred while saving animal: " + e.getMessage());
            throw e;
        }
    }

    public Animal update(Animal animal) {
        try {
            if (animal.getId() == null || !animalRepository.existsById(animal.getId())) {
                throw new EntityNotFoundException("Animal with ID " + animal.getId() + " not found.");
            }
            return animalRepository.save(animal);
        } catch (EntityNotFoundException enfe) {
            System.out.println(enfe.getMessage());
            throw enfe;
        } catch (Exception e) {
            System.out.println("Error occurred while updating animal: " + e.getMessage());
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
