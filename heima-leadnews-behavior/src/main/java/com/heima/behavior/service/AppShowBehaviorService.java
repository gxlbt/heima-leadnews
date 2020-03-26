package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * create on 2020/3/23 22:03
 *
 * @Author lbt
 */
public interface AppShowBehaviorService {

    /**
     * 保持行为数据
     * @param dto
     * @return
     */
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
