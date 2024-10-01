package springdata.technest22.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import springdata.technest22.dto.ProductRequestDto;
import springdata.technest22.dto.ProductResponseDto;
import springdata.technest22.exception.NotFoundException;
import springdata.technest22.exception.ProductNotFoundException;
import springdata.technest22.exception.SuccessResponse;
import springdata.technest22.mapper.ProductMapper;
import springdata.technest22.model.Product;
import springdata.technest22.repository.ProductRepository;
import springdata.technest22.repository.ShoppingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ShoppingRepository shoppingRepository;
    private final RedisTemplate<Long, Product> redisTemplate;
    private final ProductMapper productMapper;

    public Product getProduct (Long id){

           Product product = productRepository.findById(id)
                   .orElseThrow(() -> new ProductNotFoundException(
                           new String("NOT_FOUND_1001"),
                           String.format("Product with id %s not exc", id),
                           String.format("Product with id %s not found", id)));

        return product;

    }
    public SuccessResponse checkProduct(Long productId) {
        if (productRepository.existsById(productId)) {
            return SuccessResponse.builder()
                    .status("SUCCESS")
                    .data(SuccessResponse.ResponseData.builder()
                            .code(200)
                            .message("Product exists in the system")
                            .build())
                    .build();
        } else {
            throw new NotFoundException("DATA_NOT_FOUND", "Product not found", "The product with ID " + productId + " does not exist.");
        }
    }
    public List<Product> products () {
        return  productRepository.findAll();
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productMapper.mapToEntity(requestDto);

        Product savedProduct = productRepository.save(product);

        ProductResponseDto responseDto = productMapper.mapToResponseDto(savedProduct);

        return responseDto;
    }
}
