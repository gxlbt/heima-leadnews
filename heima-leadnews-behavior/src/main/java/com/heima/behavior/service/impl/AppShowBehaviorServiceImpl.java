package com.heima.behavior.service.impl;


import com.heima.behavior.service.AppShowBehaviorService;
import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.behavior.pojos.ApBehaviorEntry;
import com.heima.model.behavior.pojos.ApShowBehavior;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.mappers.app.ApBehaviorEntryMapper;
import com.heima.model.mappers.app.ApShowBehaviorMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create on 2020/3/23 22:05
 *
 * @Author lbt
 */
@Service
public class AppShowBehaviorServiceImpl implements AppShowBehaviorService {

    @Autowired
    private ApShowBehaviorMapper apShowBehaviorMapper;

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;

    @Override
    public ResponseResult saveShowBehavior(ShowBehaviorDto dto) {
        ApUser user = AppThreadLocalUtils.getUser();
        //用户和设备不能同时为空
        if (user == null && (dto.getArticleIds() == null || dto.getArticleIds().isEmpty())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        Long userId = null;
        if (user != null){
            userId = user.getId();
        }
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipment(Math.toIntExact(userId),dto.getEquipmentId());
        if (apBehaviorEntry == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        //过滤新数据
        Integer[] temp = new Integer[dto.getArticleIds().size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = dto.getArticleIds().get(i).getId();

        }
        List<ApShowBehavior> list = apShowBehaviorMapper.selectListByEntryIdAndArticleIds(apBehaviorEntry.getId(),temp);
        List<Integer> stringList = new ArrayList<>(Arrays.asList(temp));
        if (!list.isEmpty()){
            list.forEach(item -> {
                stringList.remove(item.getArticleId());
            });
        }

        //插入新数据
        if (!stringList.isEmpty()){
            temp = new Integer[stringList.size()];
            apShowBehaviorMapper.saveBehaviors(apBehaviorEntry.getId(),temp);
            /*!mycat:catlet=io.mycat.route.sequence.BatchInsertSequence */
        }
        return ResponseResult.okResult(0);
    }
}
