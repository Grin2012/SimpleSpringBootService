package it.sevenbits.backend.spring.sample.web.contorllers;

import it.sevenbits.backend.spring.sample.core.model.Item;
import it.sevenbits.backend.spring.sample.core.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Controller
@RequestMapping("items/{id}")
public class ItemController {
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemController(final ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Item> get(@PathVariable long id) {
        Item result = itemsRepository.getItemById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Item> update(
            @PathVariable long id,
            @RequestBody Item newItem) {
        Item createdItem = itemsRepository.update(id, newItem);
        if (createdItem == null) {
            return ResponseEntity.notFound().build();
        }
        URI location = UriComponentsBuilder.fromPath("/items/")
            .path(String.valueOf(createdItem.getId())).build().toUri();
        return ResponseEntity.created(location).body(createdItem);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable long id) {
        boolean deleted = itemsRepository.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}