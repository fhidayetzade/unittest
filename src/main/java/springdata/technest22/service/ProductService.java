package springdata.technest22.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import springdata.technest22.dto.ProductDto;
import springdata.technest22.model.Product;
import springdata.technest22.repository.ProductRepository;
import springdata.technest22.repository.ShoppingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ShoppingRepository shoppingRepository;
    private final RedisTemplate<Long, Product> redisTemplate;

    public Product getProduct (Long id){

           Product product = productRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException(String.format("Product with id %s not found", id)));

        return product;

    }
}
