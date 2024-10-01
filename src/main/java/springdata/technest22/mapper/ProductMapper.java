package springdata.technest22.mapper;

import org.mapstruct.Mapper;
import springdata.technest22.dto.ProductRequestDto;
import springdata.technest22.dto.ProductResponseDto;
import springdata.technest22.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapToEntity(ProductRequestDto requestDto);

    ProductResponseDto mapToResponseDto(Product product);
}
