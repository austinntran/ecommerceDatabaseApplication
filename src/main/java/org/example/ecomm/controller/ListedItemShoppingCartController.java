package org.example.ecomm.controller;

import org.example.ecomm.model.*;
import org.example.ecomm.repository.ListedItemShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ListedItemShoppingCartController {

    @Autowired
    ListedItemShoppingCartRepository listedItemShoppingCartRepository;

    @GetMapping("/listeditemshoppingcarts")
    public ResponseEntity<List<ListedItemShoppingCart>> getAllListedItemShoppingCarts(@RequestParam(required = false) Long shoppingCartId, @RequestParam(required = false) Long listedItemId) {
        try {
            List<ListedItemShoppingCart> listedItemShoppingCarts = new ArrayList<>();
            if (shoppingCartId == null || listedItemId == null) {
                listedItemShoppingCartRepository.findAll().forEach(listedItemShoppingCarts::add);
            } else {
                ListedItemShoppingCartId id = new ListedItemShoppingCartId(shoppingCartId, listedItemId);
                Optional<ListedItemShoppingCart> shoppingCart = listedItemShoppingCartRepository.findById(id);
                shoppingCart.ifPresent(listedItemShoppingCarts::add);
            }
            return new ResponseEntity<>(listedItemShoppingCarts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listeditemshoppingcarts/{shoppingCartId}/{listedItemId}")
    public ResponseEntity<ListedItemShoppingCart> getWishlistByCustomId(@PathVariable("shoppingCartId") Long shoppingCartId, @PathVariable("listedItemId") Long listedItemId) {
        ListedItemShoppingCartId id = new ListedItemShoppingCartId(shoppingCartId, listedItemId);
        Optional<ListedItemShoppingCart> listedItemShoppingCartData = listedItemShoppingCartRepository.findById(id);

        return listedItemShoppingCartData.map(listedItemShoppingCart -> new ResponseEntity<>(listedItemShoppingCart, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listeditemshoppingcarts/{shoppingCartId}")
    public ResponseEntity<List<ListedItemShoppingCart>> getShoppingCartByShoppingCart(@PathVariable("shoppingCartId") Long shoppingCartId) {
        List<ListedItemShoppingCart> listedItemShoppingCartData = listedItemShoppingCartRepository.findAll();
        List<ListedItemShoppingCart> filtered = listedItemShoppingCartData.stream().filter(l -> l.getShoppingCart().getShoppingcart_id() == shoppingCartId).toList();
        return ResponseEntity.ok(filtered);
    }

    @DeleteMapping("/listeditemshoppingcarts/{shoppingCartId}/{listedItemId}")
    public ResponseEntity<HttpStatus> deleteListedItemFromWishlist(@PathVariable("shoppingCartId") Long shoppingCartId, @PathVariable("listedItemId") Long listedItemId) {
        try {
            ListedItemShoppingCartId id = new ListedItemShoppingCartId(shoppingCartId, listedItemId);
            if (!listedItemShoppingCartRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            listedItemShoppingCartRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/listeditemwishlists")
    public ResponseEntity<HttpStatus> deleteAllWishlists() {
        try {
            listedItemShoppingCartRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/listeditemwishlists")
    public ResponseEntity<ListedItemShoppingCart> createListedItemWishlist(@RequestBody ListedItemShoppingCart listedItemShoppingCart) {
        try {
            ListedItemShoppingCartId id = new ListedItemShoppingCartId(listedItemShoppingCart.getShoppingCart().getShoppingcart_id(), listedItemShoppingCart.getListedItem().getItemId());
            if (listedItemShoppingCartRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            ListedItemShoppingCart savedShoppingCart = listedItemShoppingCartRepository.save(listedItemShoppingCart);
            return new ResponseEntity<>(savedShoppingCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
