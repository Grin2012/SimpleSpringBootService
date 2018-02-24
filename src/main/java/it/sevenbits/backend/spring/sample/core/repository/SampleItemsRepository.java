package it.sevenbits.backend.spring.sample.core.repository;

import org.springframework.stereotype.Repository;
import it.sevenbits.backend.spring.sample.core.model.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class SampleItemsRepository implements ItemsRepository {
    private List<Item> items = new ArrayList<Item>();
    private long countID = 0;
    private long getNextID () {
        return ++countID;
    }

    public SampleItemsRepository() {
        /*Fake repository*/
        items.add(new Item(1, "name1"));
        items.add(new Item(2, "name2"));
        items.add(new Item(3, "name3"));
    }

    @Override
    public List<Item> getAllItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public Item getItemByID(long id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public Item create(Item item) {
        Item newItem = new Item(getNextID(),item.getName());
        items.add(newItem);
        return newItem;
    }

    @Override
    public Item update(long id, Item newItem) {
        return items.set(items.indexOf(getItemByID(id)), new Item(id,newItem.getName()));
    }

    @Override
    public boolean delete(long id) {
        return items.remove(getItemByID(id));
    }
}
