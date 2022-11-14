package pl.mariuszk.orderservice.config.orika;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import pl.mariuszk.elasticsearch.model.ProductElastic;
import pl.mariuszk.elasticsearch.model.ProductOrderElastic;
import pl.mariuszk.orderservice.elasticsearch.repository.ProductRepository;
import pl.mariuszk.orderservice.model.frontend.order.OrderItemDto;

@RequiredArgsConstructor
public class OrderItemDtoConverter extends CustomConverter<OrderItemDto, ProductOrderElastic> {

    private final ProductRepository productRepository;

    @Override
    public ProductOrderElastic convert(OrderItemDto orderItemDto, Type<? extends ProductOrderElastic> type, MappingContext mappingContext) {
        ProductElastic productData = productRepository.getByCode(orderItemDto.getProductCode());

        return ProductOrderElastic.builder()
                .quantity(orderItemDto.getQuantity())
                .code(productData.getCode())
                .name(productData.getName())
                .img(productData.getImgLinks().get(0))
                .price(productData.getPrice())
                .build();
    }
}
