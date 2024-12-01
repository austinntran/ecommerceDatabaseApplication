package org.example.ecomm.controller;

import org.example.ecomm.model.ShoppingCart;
import org.example.ecomm.repository.ShoppingCartRepository;
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
public class ShoppingCartController {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @GetMapping("/shoppingcarts")
    public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts(@RequestParam(required = false) Long id) {
        try {
            List<ShoppingCart> carts = new ArrayList<>();
            shoppingCartRepository.findAll().forEach(carts::add);
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/shoppingcarts/{shoppingcart_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable(value = "shoppingcart_id") Long id) {
        Optional<ShoppingCart> shoppingCartData = shoppingCartRepository.findById(id);
        if (shoppingCartData.isPresent()) {
            return new ResponseEntity<>(shoppingCartData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/shoppingcarts")
    public ResponseEntity<HttpStatus> deleteAllShoppingCarts() {
        try {
            shoppingCartRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/shoppingcarts")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        try {
            ShoppingCart shoppingCart1 = new ShoppingCart();
            shoppingCart1.setUser(shoppingCart.getUser());
            ShoppingCart _shoppingCart = shoppingCartRepository.save(shoppingCart1);
            return new ResponseEntity<>(_shoppingCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/shoppingcarts/{shoppingcart_id}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable("shoppingcart_id") Long id, @RequestBody ShoppingCart shoppingCart) {
        Optional<ShoppingCart> shoppingCartData = shoppingCartRepository.findById(id);
        if (shoppingCartData.isPresent()) {
            ShoppingCart _shoppingCart = shoppingCartData.get();
            _shoppingCart.setUser(shoppingCart.getUser());
            return new ResponseEntity<>(shoppingCartRepository.save(_shoppingCart), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/shoppingcarts/{shoppingcart_id}")
    public ResponseEntity<HttpStatus> deleteShoppingCart(@PathVariable(value = "shoppingcart_id") Long id) {
        try {
            shoppingCartRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
