package org.example.ecomm.controller;

import org.example.ecomm.model.ListedItem;
import org.example.ecomm.model.User;
import org.example.ecomm.repository.ListedItemRepository;
import org.example.ecomm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ListedItemController {

    @Autowired
    ListedItemRepository listedItemRepository;
    @GetMapping("/items")
    public ResponseEntity<List<ListedItem>> getAllItems(@RequestParam(required = false) Long itemId) {
        try {
            List<ListedItem> items = new ArrayList<ListedItem>();

            listedItemRepository.findAll().forEach(items::add);

            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/items/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ListedItem> getItemByItemId(@PathVariable(value = "itemId") Long itemId) {
        Optional<ListedItem> itemData = listedItemRepository.findById(itemId);
        if (itemData.isPresent()) {
            return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable(value = "itemId") Long itemId) {
        try {
            listedItemRepository.deleteById(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/items")
    public ResponseEntity<HttpStatus> deleteAllItems() {
        try {
            listedItemRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<ListedItem> createItem(@RequestBody ListedItem item) {
        try {
            ListedItem listedItem = new ListedItem();
            listedItem.setSellerUsername(item.getSellerUsername());
            listedItem.setDescription(item.getDescription());
            listedItem.setQuantity(item.getQuantity());
            listedItem.setName(item.getName());
            listedItem.setPrice(item.getPrice());
            listedItem.setConditionId(item.getConditionId());
            ListedItem _listedItem = listedItemRepository.save(listedItem);
            return new ResponseEntity<>(_listedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<ListedItem> updateItem(@PathVariable("itemId") Long itemId, @RequestBody ListedItem item) {
        Optional<ListedItem> itemData = listedItemRepository.findById(itemId);
        if (itemData.isPresent()) {
            ListedItem _item = itemData.get();
            _item.setSellerUsername(item.getSellerUsername());
            _item.setDescription(item.getDescription());
            _item.setQuantity(item.getQuantity());
            if (item.getBuyerUsername() != null) {
                _item.setBuyerUsername(item.getBuyerUsername());
            }
            _item.setName(item.getName());
            _item.setPrice(item.getPrice());
            _item.setConditionId(item.getConditionId());
            return new ResponseEntity<>(listedItemRepository.save(_item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/items/{itemId}/buy")
    public ResponseEntity<ListedItem> updateItem(@PathVariable("itemId") Long itemId, @RequestBody User user) {
        Optional<ListedItem> itemData = listedItemRepository.findById(itemId);
        if (itemData.isPresent()) {
            ListedItem _item = itemData.get();
            _item.setBuyerUsername(user.getUsername());
            _item.setPurchaseDate(new Date(System.currentTimeMillis()));
            return new ResponseEntity<>(listedItemRepository.save(_item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
