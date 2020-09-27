package com.example.demo.repo.reposervice;

import com.example.demo.entity.MachineDetailsEntity;
import com.example.demo.exception.InternalServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRepoService<T, U> {
    @Autowired
    protected abstract JpaRepository<T, U> getRepo();

    public T createEntity(T entity) {
        T createdEntity;
        try {
            createdEntity = getRepo().save(entity);
        } catch (Exception e) {
            throw new InternalServerException(
                    "Error while creating entity in DB: " + entity.toString() + " : " + e
                            .getMessage());
        }
        return createdEntity;
    }

    public List<T> createEntities(List<T> entities) {
        return getRepo().saveAll(entities);
    }

    public List<T> updateEntities(Iterable<T> entities) {
        return getRepo().saveAll(entities);
    }

    public T getEntityById(U id) {
        Optional<T> entity;
        try {
            entity = getRepo().findById(id);
        } catch (Exception e) {
            throw new InternalServerException(
                    "Error while retrieving entity from DB: " + id + " : " + e.getMessage());
        }
        return entity.get();
    }

    public List<T> getAllEntities() {
        List<T> entities;
        try {
            entities = getRepo().findAll();
        } catch (Exception e) {
            throw new InternalServerException(
                    "Error while retrieving entities from DB:" + e.getMessage());
        }
        return entities;
    }

    public T updateEntity(T entity) {
        T updatedEntity;
        try {
            updatedEntity = getRepo().save(entity);
        } catch (Exception e) {
            throw new InternalServerException(
                    "Error while updating entity from DB: " + entity.toString() + " : " + e
                            .getMessage());
        }
        return updatedEntity;
    }

    public void deleteEntity(T entity) {
        try {
            getRepo().delete(entity);
        } catch (Exception e) {
            throw new InternalServerException(
                    "Error while deleting entity from DB: " + entity.toString() + " : " + e
                            .getMessage());
        }
    }

}
