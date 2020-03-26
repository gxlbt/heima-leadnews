package com.heima.model.behavior.pojos;

import lombok.Data;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author lbt
 */
@Data
public class ApBehaviorEntry {
    private Integer id;
    private Short type;
    private Integer entryId;
    private Date createdTime;
    public String burst;

    @Alias("ApBehaviorEntryEnumType")
    public enum  Type{
        USER((short)1),EQUIPMENT((short)0);
        @Getter
        short code;
        Type(short code){
            this.code = code;
        }
    }

    public boolean isUser(){
        return this.getType() != null && this.getType() == Type.USER.getCode();
    }
}