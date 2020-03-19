package com.heima.article.controller.v1;

import com.heima.article.apis.ArticleHomeControllerApi;
import com.heima.article.service.ArticleIndexService;
import com.heima.common.common.article.contants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lbt on 2020/3/19 21:33
 *
 * @Author lbt
 */
@RestController
@RequestMapping("/api/v1/article")
public class ArticleIndexController implements ArticleHomeControllerApi {

    @Autowired
    private ArticleIndexService articleIndexService;

    @Override
    @GetMapping("/load")
    public ResponseResult load(ArticleHomeDto dto) {
        return articleIndexService.load(ArticleConstants.LOADTYPE_LOAD_MORE,dto);
    }

    @Override
    @GetMapping("/loadMore")
    public ResponseResult loadMore(ArticleHomeDto dto) {
        return articleIndexService.load(ArticleConstants.LOADTYPE_LOAD_MORE,dto);
    }

    @Override
    @GetMapping("/loadNew")
    public ResponseResult loadNew(ArticleHomeDto dto) {
        return articleIndexService.load(ArticleConstants.LOADTYPE_LOAD_NEW,dto);
    }
}
