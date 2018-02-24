package it.sevenbits.backend.spring.sample.web.contorllers;

import it.sevenbits.backend.spring.sample.core.repository.ItemsRepository;
import it.sevenbits.backend.spring.sample.core.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private ItemsRepository itemsRepository;

    @Autowired
    public ItemsController(final ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Item> list() {
        return itemsRepository.getAllItems();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Item> create(@RequestBody Item newItem) {
        Item createdItem = itemsRepository.create(newItem);
        URI location = UriComponentsBuilder.fromPath("/items/")
            .path(String.valueOf(createdItem.getId())).build().toUri();
        return ResponseEntity.created(location).body(createdItem);
    }
}