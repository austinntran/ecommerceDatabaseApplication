package org.example.ecomm.repository;

import org.example.ecomm.model.ListedItem;
import org.example.ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ListedItemRepository extends JpaRepository<ListedItem, Long> {

}
