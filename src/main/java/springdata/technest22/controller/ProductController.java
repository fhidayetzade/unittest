package springdata.technest22.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springdata.technest22.model.Product;
import springdata.technest22.model.ShoppingCart;
import springdata.technest22.service.ShoppingCartService;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ShoppingCartService shoppingCartService;



    @PostMapping("/createShopping")
    public void createShopping (ShoppingCart cart){
        shoppingCartService.createShoppingCart(cart);
    }
    @PostMapping("/createProduct")
    public void createProduct (@RequestBody Product product){
        shoppingCartService.createProduct(product);
    }

    @PostMapping("/{cId}/{pId}/product")
    public ResponseEntity<ShoppingCart> addProductToCart(@PathVariable Long cId, @PathVariable Long pId) {

        ShoppingCart cart = shoppingCartService.addProductToCart(cId, pId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public Product product(@RequestBody Long id){
        return shoppingCartService.update(id);
    }

    @GetMapping("/getProduct/{id}")
    public Product getProduct (@PathVariable Long id){
        return shoppingCartService.getProduct(id);
    }

}
