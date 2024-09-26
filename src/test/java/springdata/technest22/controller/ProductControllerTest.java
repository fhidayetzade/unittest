package springdata.technest22.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import springdata.technest22.model.Product;
import springdata.technest22.service.ProductService;
import springdata.technest22.service.ShoppingCartServiceRedis;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProduct() throws Exception {
        // Arrange
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("Sample Product");

        // Mock the service call
        Mockito.when(productService.getProduct(productId)).thenReturn(mockProduct);

        // Act and Assert
        mockMvc.perform(get("/product/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(productId))
                .andExpect(jsonPath("name").value("Sample Product"));
    }

    @Test
    public void testGetProduct_NotFound() throws Exception {
        // Arrange
        Long productId = 1l;

        // Mock the service to throw an exception when the product is not found
        Mockito.when(productService.getProduct(productId)).thenThrow(new RuntimeException("Product not found"));

        // Act and Assert
        mockMvc.perform(get("/product/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}