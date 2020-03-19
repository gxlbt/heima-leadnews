package com.heima.model.mappers.app;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * created by lbt on 2020/3/19 22:03
 *
 * @Author lbt
 */
public interface ApUserArticleListMapper {

    /**
     *
     * @param user
     * @param dto
     * @param type
     * @return
     */
    List<ApUserArticleList> loadArticleIdListByUser(@Param("user") ApUser user,
                                                    @Param("dto")ArticleHomeDto dto, @Param("type") short type);
}
