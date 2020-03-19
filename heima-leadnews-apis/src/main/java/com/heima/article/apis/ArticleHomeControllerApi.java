package com.heima.article.apis;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * created by lbt on 2020/3/19 21:56
 * 首页文章
 * @Author lbt
 */
public interface ArticleHomeControllerApi {

    /**
     * 加载首页文章
     * @param dto 封装参数对象
     * @return 文章列表数据
     */
    ResponseResult load(ArticleHomeDto dto);

    /**
     * 加载更多
     * @param dto
     * @return
     */
    ResponseResult loadMore(ArticleHomeDto dto);

    /**
     * 加载最新的数据
     * @param dto
     * @return
     */
    ResponseResult loadNew(ArticleHomeDto dto);
}
