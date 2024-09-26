package springdata.technest22.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springdata.technest22.dto.ProductDto;
import springdata.technest22.model.Product;
import springdata.technest22.model.ShoppingCart;
import springdata.technest22.service.ShoppingCartServiceRedis;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductControllerRedis {

    private final ShoppingCartServiceRedis shoppingCartServiceRedis;



    @PostMapping("/createShopping")
    public void createShopping (ShoppingCart cart){
        shoppingCartServiceRedis.createShoppingCart(cart);
    }
    @PostMapping("/createProduct")
    public void createProduct (@RequestBody Product product){
        shoppingCartServiceRedis.createProduct(product);
    }

    /*@PostMapping("/{cId}/{pId}/product")
    public ResponseEntity<ShoppingCart> addProductToCart(@PathVariable Long cId, @PathVariable Long pId) {

        ShoppingCart cart = shoppingCartServiceRedis.addProductToCart(cId, pId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }*/

    @PutMapping("/update/{id}")
    public Product product(@PathVariable Long id, @RequestBody ProductDto productDto){
        return shoppingCartServiceRedis.update(id, productDto);
    }

    @GetMapping("/{id}")
    public Product getProduct (@PathVariable Long id){
        return shoppingCartServiceRedis.getProduct(id);
    }

}
