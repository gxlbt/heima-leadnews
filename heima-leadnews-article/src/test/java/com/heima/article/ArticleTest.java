package com.heima.article;

import com.heima.article.service.ArticleIndexService;
import com.heima.common.common.article.contants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * created by lbt on 2020/3/19 22:35
 *
 * @Author lbt
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleTest {

    @Autowired
    private ArticleIndexService articleIndexService;

    @Test
    public void testLoad(){
        ApUser apUser = new ApUser();
        apUser.setId(1L);
        AppThreadLocalUtils.setUser(apUser);
        ArticleHomeDto dto = new ArticleHomeDto();
        ResponseResult data = articleIndexService.load(ArticleConstants.LOADTYPE_LOAD_MORE, dto);
        System.out.println(data.getData());
    }
}
