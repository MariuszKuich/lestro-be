package pl.mariuszk.productservice.config.orika;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import pl.mariuszk.elasticsearch.model.ProductElastic;
import pl.mariuszk.productservice.elasticsearch.repository.ProductRepository;
import pl.mariuszk.productservice.model.frontend.NewProductDto;

import static pl.mariuszk.common.constants.ProductsConstants.STANDARD_PRODUCT_CODE_PREFIX;

@RequiredArgsConstructor
public class NewProductDtoConverter extends CustomConverter<NewProductDto, ProductElastic> {

    private final ProductRepository productRepository;

    @Override
    public ProductElastic convert(NewProductDto newProductDto, Type<? extends ProductElastic> type, MappingContext mappingContext) {
        long availableOrdinalNumber = productRepository.findTopByOrderByNoDesc().getNo() + 1;

        return ProductElastic.builder()
                .no(availableOrdinalNumber)
                .code(STANDARD_PRODUCT_CODE_PREFIX + availableOrdinalNumber)
                .name(newProductDto.getName())
                .description(newProductDto.getDescription())
                .imgLinks(newProductDto.getImgLinks())
                .price(newProductDto.getPrice().doubleValue())
                .height(newProductDto.getHeight())
                .width(newProductDto.getWidth())
                .length(newProductDto.getLength())
                .plants(newProductDto.getPlants())
                .build();
    }
}
