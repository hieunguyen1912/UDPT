package org.ddd.apigateway.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.ddd.apigateway.dto.request.IntrospectRequest;
import org.ddd.apigateway.dto.response.ApiResponse;
import org.ddd.apigateway.dto.response.IntrospectResponse;
import org.ddd.apigateway.repository.IdentityClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {

    IdentityClient identityClient;

    public Mono<ApiResponse<IntrospectResponse>> introspect(String token) {
        return identityClient.introspect(IntrospectRequest.builder().token(token).build());
    }
}
