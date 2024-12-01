package org.example.ecomm.controller;

import org.example.ecomm.model.User;
import org.example.ecomm.model.Wishlist;
import org.example.ecomm.repository.WishlistRepository;
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
public class WishlistController {

    @Autowired
    WishlistRepository wishlistRepository;
    @GetMapping("/wishlists")
    public ResponseEntity<List<Wishlist>> getAllWishlists(@RequestParam(required = false) long id) {
        try {
            List<Wishlist> wishlists = new ArrayList<>();
            if (id < 0) wishlistRepository.findAll().forEach(wishlists::add);
            else wishlistRepository.findByIdContaining(id).forEach(wishlists::add);
            return new ResponseEntity<>(wishlists, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/wishlists/{wishlist_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable(value = "wishlist_id") long id) {
        Optional<Wishlist> wishlistData = wishlistRepository.findById(id);
        if (wishlistData.isPresent()) {
            return new ResponseEntity<>(wishlistData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/wishlists")
    public ResponseEntity<HttpStatus> deleteAllWishlists() {
        try {
            wishlistRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/wishlists")
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Wishlist wishlist) {
        try {
            Wishlist wishlist1 = new Wishlist();
            wishlist1.setWishlist_id(wishlist.getWishlist_id());
            wishlist1.setUser(wishlist.getUser());
            Wishlist _wishlist = wishlistRepository.save(wishlist1);
            return new ResponseEntity<>(_wishlist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/wishlists/{wishlist_id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable("wishlist_id") long wishlist_id, @RequestBody Wishlist wishlist) {
        Optional<Wishlist> wishlistData = wishlistRepository.findById(wishlist_id);
        if (wishlistData.isPresent()) {
            Wishlist _wishlist = wishlistData.get();
            _wishlist.setWishlist_id(wishlist.getWishlist_id());
            _wishlist.setUser(wishlist.getUser());
            return new ResponseEntity<>(wishlistRepository.save(_wishlist), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
