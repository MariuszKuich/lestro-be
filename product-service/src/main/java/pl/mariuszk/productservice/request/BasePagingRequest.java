package pl.mariuszk.productservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePagingRequest {

    private Integer pageNumber;
    private Integer pageSize;
    private String sort;
    private Sort.Direction order;
}
