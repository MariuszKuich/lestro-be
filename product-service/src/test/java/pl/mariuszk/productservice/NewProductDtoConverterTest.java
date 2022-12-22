package pl.mariuszk.productservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.mariuszk.elasticsearch.model.ProductElastic;
import pl.mariuszk.productservice.config.orika.NewProductDtoConverter;
import pl.mariuszk.productservice.elasticsearch.repository.ProductRepository;
import pl.mariuszk.productservice.model.frontend.NewProductDto;

import java.math.BigDecimal;
import java.util.Collections;

class NewProductDtoConverterTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    @Test
    void convertNewProductDtoToProductElastic() {
        //arrange
        NewProductDto inputData = NewProductDto.builder()
                .name("Testowa ozdoba")
                .description("Opis testowej ozdoby")
                .imgLinks(Collections.singletonList("https://i.ibb.co/r3QF0wR/1.jpg"))
                .price(BigDecimal.valueOf(49.99))
                .height(30)
                .width(10)
                .length(10)
                .plants(Collections.singletonList("stokrotka"))
                .build();
        Mockito.when(productRepository.findTopByOrderByNoDesc()).thenReturn(ProductElastic.builder().no(11L).build());
        NewProductDtoConverter converter = new NewProductDtoConverter(productRepository);

        //act
        ProductElastic result = converter.convert(inputData, null, null);

        //assert
        ProductElastic expectedResult = ProductElastic.builder()
                .no(12L)
                .code("STR-12")
                .name("Testowa ozdoba")
                .description("Opis testowej ozdoby")
                .imgLinks(Collections.singletonList("https://i.ibb.co/r3QF0wR/1.jpg"))
                .price(49.99)
                .height(30)
                .width(10)
                .length(10)
                .plants(Collections.singletonList("stokrotka"))
                .build();

        Assertions.assertEquals(expectedResult, result);
    }
}
