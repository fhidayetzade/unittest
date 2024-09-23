package springdata.technest22.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import springdata.technest22.model.ShoppingCart;
import springdata.technest22.repository.ShoppingRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @InjectMocks
    ShoppingCartService shoppingCartService;
    @Mock
    ShoppingRepository shoppingRepository;


   /* @Test
    void getValidIdWhenProductSuccess (){
        ShoppingCart shoppingCart = ShoppingCart
                .builder()
                .name("Ali")
                .build();
        Mockito.when(shoppingRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(shoppingCart));
        ShoppingCart cart = shoppingCartService.getShoppingId(1L);
        Assertions.assertThat(cart.getName()).isEqualTo(shoppingCart.getName());
    }*/
}