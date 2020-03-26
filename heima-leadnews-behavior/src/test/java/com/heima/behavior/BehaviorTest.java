package com.heima.behavior;

/**
 * create on 2020/3/23 22:49
 *
 * @Author lbt
 */

import com.heima.behavior.service.AppShowBehaviorService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试文章列表相关接口
 */
@SpringBootTest(classes = BehaviorJarApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BehaviorTest {


    @Autowired
    private AppShowBehaviorService showBehaviorService;


    @Test
    public void testSaveBehavior() {
        ApUser apUser = new ApUser();
        apUser.setId(1l);
        AppThreadLocalUtils.setUser(apUser);
        ShowBehaviorDto dto = new ShowBehaviorDto();
        List<ApArticle> list = new ArrayList<>();
        ApArticle apArticle = new ApArticle();
        apArticle.setId(200);
        list.add(apArticle);
        dto.setArticleIds(list);
        showBehaviorService.saveShowBehavior(dto);
        //articleIndexService.saveBehaviors(data);
    }
}
