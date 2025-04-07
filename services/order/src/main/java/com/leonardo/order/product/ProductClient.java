package com.leonardo.order.product;

import com.leonardo.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}") // assim eu coloco valor ja dentro da variavel
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        // aqui tem que ser httpsheaders do spring
        // para usar token e outras funcionalidades do header, e por esse modelo de
        // codigo
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);

        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>(){};
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );
        // iserror olha ja se ela tem status de erro que ai ja apresenta
        // como true o if
        if(responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
