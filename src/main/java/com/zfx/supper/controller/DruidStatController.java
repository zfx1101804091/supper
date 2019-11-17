package com.zfx.supper.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/17 0017 12:35
 */
@RestController
public class DruidStatController {
    /*
     * 功能描述: DruidStatManagerFacade#getDataSourceStatDataList 
                 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 
                 还提供了一些其他方法，你可以按需选择使用。
     * 
     * @Param: []
     * @Return: java.lang.Object
     * @Author: Administrator
     * @Date: 2019/11/17 0017 12:39
     */
    @GetMapping("/druid/stat")
    public Object druidStat(){
        
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

}
