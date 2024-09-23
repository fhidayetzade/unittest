package springdata.technest22.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.technest22.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
