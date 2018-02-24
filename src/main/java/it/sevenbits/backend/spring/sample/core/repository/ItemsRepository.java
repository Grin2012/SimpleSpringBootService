package it.sevenbits.backend.spring.sample.core.repository;

import it.sevenbits.backend.spring.sample.core.model.Item;

import java.util.List;

public interface ItemsRepository {
    List<Item> getAllItems();
    Item getItemByID(long id);
    Item create(Item newItem);
    Item update(long id, Item newItem);
    boolean delete(long id);
}
