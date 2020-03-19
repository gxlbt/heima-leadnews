package com.heima.article.service;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * created by lbt on 2020/3/19 21:32
 *
 * @Author lbt
 */
public interface ArticleIndexService {

    /**
     *
     * @param type 1加载更多，2加载最新
     * @param dto
     * @return
     */
    ResponseResult load(Short type, ArticleHomeDto dto);
}
