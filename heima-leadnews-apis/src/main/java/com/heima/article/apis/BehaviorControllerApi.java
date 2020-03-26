package com.heima.article.apis;

import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * create on 2020/3/23 22:00
 *
 * @Author lbt
 */
public interface BehaviorControllerApi {

    /**
     * 用户行为
     * @param dto
     * @return
     */
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
