package springdata.technest22.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import springdata.technest22.dto.ProductDto;
import springdata.technest22.model.Product;
import springdata.technest22.model.ShoppingCart;
import springdata.technest22.repository.ProductRepository;
import springdata.technest22.repository.ShoppingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartService {

    private final ProductRepository productRepository;
    private final ShoppingRepository shoppingRepository;
    private final RedisTemplate<Long, Product> redisTemplate;

    public Product update(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        redisTemplate.delete(id);
        product.setName(productDto.getName());
        productRepository.save(product);
        redisTemplate.opsForValue().set(id, product);
        return product;
    }


    public Product getProduct (Long id){
        Product product = redisTemplate.opsForValue().get(id);
        if(product == null){
            log.info("Get from DB: {}", id);
            product = productRepository.findById(id).orElseThrow(RuntimeException::new);
            redisTemplate.opsForValue().set(id,product);
        return product;
        }else {
            log.info("Get from Cache: {}", id);
            return product;
        }
    }

    public void delete (Long id){
        productRepository.deleteById(id);
        redisTemplate.opsForValue().getAndDelete(id);

    }

    public void createShoppingCart (ShoppingCart cart){

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .name(cart.getName())
                .build();

        shoppingRepository.save(shoppingCart);
    }

    public void createProduct (Product product){

        Product p = Product.builder()
                .name(product.getName())
                .build();


        productRepository.save(p);
        redisTemplate.opsForValue().set(p.getId(),p);
    }

    public ShoppingCart addProductToCart(Long cartId, Long productId) {
        ShoppingCart cart = shoppingRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        cart.getProducts().add(product);
        return shoppingRepository.save(cart);
    }


}
