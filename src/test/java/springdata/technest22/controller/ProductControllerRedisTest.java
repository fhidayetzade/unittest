package springdata.technest22.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import springdata.technest22.model.Product;
import springdata.technest22.service.ShoppingCartServiceRedis;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class ProductControllerRedisTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ShoppingCartServiceRedis cartService;

   /* @BeforeEach
    public void setup(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(restDocumentation)
                        .uris()
                        .withScheme("https")
                        .withHost("onsual.com")
                        .withPort(443))
                .build();
    }*/

    @Test
    void givenValidIdWhenGetProductThenSuccess() throws Exception {

        Mockito.when(cartService.getProduct(ArgumentMatchers.anyLong()))
                        .thenReturn(Product.builder()
                                .name("Farid")
                                .build());
        mockMvc.perform(get("/api/1"))
                .andExpect(status().isOk());
    }
}