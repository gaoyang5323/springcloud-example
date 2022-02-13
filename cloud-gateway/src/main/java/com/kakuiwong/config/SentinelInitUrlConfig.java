package com.kakuiwong.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * 初始化限流分组
 */
//@Configuration
public class SentinelInitUrlConfig {

    @PostConstruct
    public void doInit() {
        loadApiDefinitions();
    }

    public void loadApiDefinitions() {
        Set<ApiDefinition> apiDefinitions = GatewayApiDefinitionManager.getApiDefinitions();

        ApiDefinition api = new ApiDefinition("分组")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern("/auth/*")
                            //参数值的匹配策略
                            // 精确匹配（PARAM_MATCH_STRATEGY_EXACT）
                            // 子串匹配（PARAM_MATCH_STRATEGY_CONTAINS）
                            // 正则匹配（PARAM_MATCH_STRATEGY_REGEX）
                            .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_EXACT));
                }});
        apiDefinitions.add(api);
        GatewayApiDefinitionManager.loadApiDefinitions(apiDefinitions);
    }
}
