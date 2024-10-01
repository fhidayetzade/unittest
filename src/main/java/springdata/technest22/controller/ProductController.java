package springdata.technest22.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springdata.technest22.dto.ProductRequestDto;
import springdata.technest22.dto.ProductResponseDto;
import springdata.technest22.exception.SuccessResponse;
import springdata.technest22.model.Product;
import springdata.technest22.service.ProductService;
import springdata.technest22.service.ShoppingCartServiceRedis;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @GetMapping("/all")
    public List<Product> products(){
        return productService.products();
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto responseDto = productService.createProduct(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public SuccessResponse getProductById(@PathVariable Long id) {
        return productService.checkProduct(id);
    }
}
