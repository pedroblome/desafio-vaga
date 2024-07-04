package com.example.projeto_desafio.service;

import com.example.projeto_desafio.entity.Categoria;
import com.example.projeto_desafio.exception.EntityNotFoundException;
import com.example.projeto_desafio.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listAll() {
        try {
            return categoriaRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error occurred while fetching all categories: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Categoria> findById(Integer id) {
        try {
            return categoriaRepository.findById(id);
        } catch (Exception e) {
            System.out.println("Error occurred while fetching category by ID: " + e.getMessage());
            throw e;
        }
    }

    public Categoria save(Categoria categoria) {
        try {
            return categoriaRepository.save(categoria);
        } catch (Exception e) {
            System.out.println("Error occurred while saving category: " + e.getMessage());
            throw e;
        }
    }

    public Categoria update(Categoria categoria) {
        try {
            if (categoria.getId() == null || !categoriaRepository.existsById(categoria.getId())) {
                throw new EntityNotFoundException("Category with ID " + categoria.getId() + " not found.");
            }
            return categoriaRepository.save(categoria);
        } catch (EntityNotFoundException enfe) {
            System.out.println(enfe.getMessage());
            throw enfe;
        } catch (Exception e) {
            System.out.println("Error occurred while updating category: " + e.getMessage());
            throw e;
        }
    }

    public void delete(Integer id) {
        try {
            if (!categoriaRepository.existsById(id)) {
                throw new EntityNotFoundException("Category with ID " + id + " not found.");
            }
            categoriaRepository.deleteById(id);
        } catch (EntityNotFoundException error) {
            System.out.println(error.getMessage());
            throw error;
        } catch (Exception e) {
            System.out.println("Error occurred while deleting category: " + e.getMessage());
            throw e;
        }
    }
}
