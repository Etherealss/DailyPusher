package cn.seven.dailypusher.common.database.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wtk
 */
@Configuration
public class MyBatisPlusAuditConfig {
    /**
     * 审计数据插件配置
     * @return AuditMetaObjectHandler
     */
    @Bean
    public AuditMetaObjectHandler auditMetaObjectHandler() {
        return new AuditMetaObjectHandler();
    }
}
