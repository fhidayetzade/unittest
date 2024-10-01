package springdata.technest22.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import springdata.technest22.model.Product;
import springdata.technest22.repository.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartServiceRedis productService;
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

        assertNotNull(result);
        assertEquals(product, result);

        verify(productRepository, never()).findById(anyLong());

        verify(valueOperations, times(1)).get(1L);

    }

    @Test
    void testGetProduct_FromDB_WhenNotInCache() {
        when(valueOperations.get(anyLong())).thenReturn(null);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1L);

        assertNotNull(result);
        assertEquals(product, result);

        verify(productRepository, times(1)).findById(1L);
        verify(valueOperations, times(1)).set(1L, product);

        verify(valueOperations, times(1)).get(1L);
    }

    @Test
    void testGetProduct_NotFoundInDB_ThrowsException() {
        when(valueOperations.get(anyLong())).thenReturn(null);
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.getProduct(1L));

        verify(productRepository, times(1)).findById(1L);

        verify(valueOperations, never()).set(anyLong(), any(Product.class));

        verify(valueOperations, times(1)).get(1L);
    }


}