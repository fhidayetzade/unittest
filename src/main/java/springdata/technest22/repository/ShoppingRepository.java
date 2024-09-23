package springdata.technest22.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springdata.technest22.model.ShoppingCart;

public interface ShoppingRepository extends JpaRepository<ShoppingCart, Long> {
}
