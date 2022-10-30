package pl.mariuszk.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mariuszk.productservice.request.BasePagingRequest;

@Service
@RequiredArgsConstructor
public class PagingService {

    public Pageable buildPageableFromRequest(BasePagingRequest pagingRequest) {
        Integer pageNo = pagingRequest.getPageNumber();
        Integer pageSize = pagingRequest.getPageSize();
        Sort.Direction order = pagingRequest.getOrder();
        String sort = pagingRequest.getSort();

        if (pageNo == null || pageSize == null) {
            return Pageable.unpaged();
        }

        if (order == null || sort == null) {
            return PageRequest.of(pageNo, pageSize);
        }

        return PageRequest.of(pageNo, pageSize, order, sort);
    }
}
