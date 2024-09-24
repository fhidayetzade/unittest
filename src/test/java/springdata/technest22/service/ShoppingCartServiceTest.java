package springdata.technest22.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import springdata.technest22.dto.ProductDto;
import springdata.technest22.model.Product;
import springdata.technest22.model.ShoppingCart;
import springdata.technest22.repository.ProductRepository;
import springdata.technest22.repository.ShoppingRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartService productService;
    @Mock
    private RedisTemplate<Long, Product> redisTemplate;
    @Mock
    private ValueOperations<Long, Product> valueOperations;
    @Mock
    private ProductRepository productRepository;
    private Product product;




    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void testGetProduct_FromCache() {
        when(valueOperations.get(anyLong())).thenReturn(product);

        Product result = productService.getProduct(1L);

        // Verify that the product was retrieved from cache
        assertNotNull(result);
        assertEquals(product, result);

        // Verify repository was never called
        verify(productRepository, never()).findById(anyLong());

        // Verify Redis operations
        verify(valueOperations, times(1)).get(1L);

    }

    @Test
    void testGetProduct_FromDB_WhenNotInCache() {
        // Simulate cache miss
        when(valueOperations.get(anyLong())).thenReturn(null);
        // Simulate database hit
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1L);

        // Verify that the product was retrieved from DB
        assertNotNull(result);
        assertEquals(product, result);

        // Verify repository was called
        verify(productRepository, times(1)).findById(1L);

        // Verify product was saved in cache
        verify(valueOperations, times(1)).set(1L, product);

        // Verify Redis get operation
        verify(valueOperations, times(1)).get(1L);
    }

    @Test
    void testGetProduct_NotFoundInDB_ThrowsException() {
        // Simulate cache miss
        when(valueOperations.get(anyLong())).thenReturn(null);
        // Simulate product not found in DB
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.getProduct(1L));

        // Verify repository was called
        verify(productRepository, times(1)).findById(1L);

        // Verify Redis set was never called since product was not found
        verify(valueOperations, never()).set(anyLong(), any(Product.class));

        // Verify Redis get operation
        verify(valueOperations, times(1)).get(1L);
    }


}