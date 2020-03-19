package com.heima.article.service.impl;

import com.heima.article.service.ArticleIndexService;
import com.heima.common.common.article.contants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mappers.app.ApArticleMapper;
import com.heima.model.mappers.app.ApUserArticleListMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * created by lbt on 2020/3/19 21:33
 *
 * @Author lbt
 */
@Service
@SuppressWarnings("all")
public class ArticleIndexServiceImpl implements ArticleIndexService {

    //单页最大加载数字
    private final static short MAX_PAGE_SIZE = 50;

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Autowired
    private ApUserArticleListMapper apUserArticleListMapper;

    /**
     * @param type 1加载更多，2加载最新
     * @param dto
     * @return
     */
    @Override
    public ResponseResult load(Short type, ArticleHomeDto dto) {
        ApUser user = AppThreadLocalUtils.getUser();
        Integer size = dto.getSize();
        String tag = dto.getTag();
        //分页参数校验
        if (size == null || size <= 0) {
            size = 20;
        }
        //return (a <= b) ? a : b;
        size = Math.min(size, MAX_PAGE_SIZE);
        dto.setSize(size);

        //类型参数校验
        if (!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE) && !type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)) {
            type = ArticleConstants.LOADTYPE_LOAD_MORE;
        }

        //文章频道参数验证
        if (StringUtils.isEmpty(tag)) {
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        //最大时间处理
        if (dto.getMaxBehotTime() == null) {
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime() == null) {
            dto.setMinBehotTime(new Date());
        }

        //数据加载
        if (user != null) {
            return ResponseResult.okResult(getUserArticle(user, dto, type));
        } else {
            return ResponseResult.okResult(getDefaultArticle(dto, type));
        }

    }

    /**
     * 先从用户的推荐表中查找文章，如果没有再从大文章列表中获取
     * @param user
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApUserArticleList> list = apUserArticleListMapper.loadArticleIdListByUser(user,dto,type);
        if (!list.isEmpty()){
            List<ApArticle> temp = apArticleMapper.loadArticleListByIdList(list);
            return temp;
        } else {
            return getDefaultArticle(dto,type);
        }
    }

    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto,type);
    }

}
