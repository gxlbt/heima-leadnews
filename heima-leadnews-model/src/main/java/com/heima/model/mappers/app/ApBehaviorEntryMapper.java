package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApBehaviorEntry;
import org.springframework.data.repository.query.Param;

/**
 * create on 2020/3/23 22:35
 *
 * @Author lbt
 */
public interface ApBehaviorEntryMapper {

    ApBehaviorEntry selectByUserIdOrEquipment(@Param("userId") Integer userId,@Param("equipmentId") Integer equipmentId);
}
