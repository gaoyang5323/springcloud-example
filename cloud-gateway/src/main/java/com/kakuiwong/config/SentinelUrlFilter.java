package com.kakuiwong.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 限流根据url分组
 */
@Component
@Order(Integer.MIN_VALUE + 1)
public class SentinelUrlFilter implements GlobalFilter {
    private Set<String> uris = new CopyOnWriteArraySet<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String uri = "/" + gatewayUrl.getUri().getHost() + exchange.getRequest().getURI().getPath();

        if (uris.add(uri)) {
            loadApiDefinitions(uri);
        }

        return chain.filter(exchange);
    }

    public void loadApiDefinitions(String uri) {

        Set<ApiDefinition> apiDefinitions = GatewayApiDefinitionManager.getApiDefinitions();

        ApiDefinition api = new ApiDefinition(uri)
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern(uri)
                            .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_EXACT));
                }});
        apiDefinitions.add(api);
        GatewayApiDefinitionManager.loadApiDefinitions(apiDefinitions);
    }
}
