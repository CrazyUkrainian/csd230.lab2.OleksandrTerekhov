package csd230.lab2.respositories;


import csd230.lab2.entities.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    void removeById(Long id);
}
