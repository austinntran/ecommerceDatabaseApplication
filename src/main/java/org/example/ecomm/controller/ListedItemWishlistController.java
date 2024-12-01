package org.example.ecomm.controller;

import org.example.ecomm.model.ListedItem;
import org.example.ecomm.model.ListedItemWishlist;
import org.example.ecomm.model.ListedItemWishlistId;
import org.example.ecomm.model.Wishlist;
import org.example.ecomm.repository.ListedItemWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ListedItemWishlistController {

    @Autowired
    ListedItemWishlistRepository listedItemWishlistRepository;

    @GetMapping("/listeditemwishlists")
    public ResponseEntity<List<ListedItemWishlist>> getAllListedItemWishlists(@RequestParam(required = false) Long wishlistId, @RequestParam(required = false) Long listedItemId) {
        try {
            List<ListedItemWishlist> listedItemWishlists = new ArrayList<>();
            if (wishlistId == null || listedItemId == null) {
                listedItemWishlistRepository.findAll().forEach(listedItemWishlists::add);
            } else {
                ListedItemWishlistId id = new ListedItemWishlistId(wishlistId, listedItemId);
                Optional<ListedItemWishlist> wishlist = listedItemWishlistRepository.findById(id);
                wishlist.ifPresent(listedItemWishlists::add);
            }
            return new ResponseEntity<>(listedItemWishlists, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listeditemwishlists/{wishlistId}/{listedItemId}")
    public ResponseEntity<ListedItemWishlist> getWishlistByCustomId(@PathVariable("wishlistId") Long wishlistId, @PathVariable("listedItemId") Long listedItemId) {
        ListedItemWishlistId id = new ListedItemWishlistId(wishlistId, listedItemId);
        Optional<ListedItemWishlist> listedItemWishlistData = listedItemWishlistRepository.findById(id);

        return listedItemWishlistData.map(listedItemWishlist -> new ResponseEntity<>(listedItemWishlist, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listeditemwishlists/{wishlistId}")
    public ResponseEntity<List<ListedItemWishlist>> getWishlistByWishlist(@PathVariable("wishlistId") Long wishlistId) {
        List<ListedItemWishlist> listedItemWishlistData = listedItemWishlistRepository.findAll();
        List<ListedItemWishlist> filtered = listedItemWishlistData.stream().filter(l -> l.getWishlist().getWishlist_id() == wishlistId).toList();
        return ResponseEntity.ok(filtered);
    }

    @DeleteMapping("/listeditemwishlists")
    public ResponseEntity<HttpStatus> deleteAllWishlists() {
        try {
            listedItemWishlistRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/listeditemwishlists")
    public ResponseEntity<ListedItemWishlist> createListedItemWishlist(@RequestBody ListedItemWishlist listedItemWishlist) {
        try {
            ListedItemWishlistId id = new ListedItemWishlistId(listedItemWishlist.getWishlist().getWishlist_id(), listedItemWishlist.getListedItem().getItemId());
            if (listedItemWishlistRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            ListedItemWishlist savedWishlist = listedItemWishlistRepository.save(listedItemWishlist);
            return new ResponseEntity<>(savedWishlist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
