package com.zfx.supper.base.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 分页工具类
 * @author: zheng-fx
 * @time: 2019/11/17 0017 19:45
 */
@Data
public class PageTableRequest implements Serializable {
    
    private Integer page;
    private Integer limit;
    private Integer offset;
    
    //这里使用的是mysql的limit排序，limit5,10  ---检索6-15
    public void countOffset(){
        if(null==this.page||null==this.limit){
            this.offset=0;
            return;
        }
        this.offset=(this.page-1)*limit;
    }
}
