package cn.seven.dailypusher.common.base.validation.entity;


import cn.seven.dailypusher.common.base.exception.service.NotFoundException;

/**
 * 检查实体是否存在的接口
 * @author wtk
 */
public interface EntityValidator {
    void validate(Long id) throws NotFoundException;
}