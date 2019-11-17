package com.zfx.supper.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/17 0017 12:49
 */
@Controller
@RequestMapping("${api-url}")
public class ApiController {

    /**
     * 页面跳转  根据视图名称
     * @param modelAndView
     * @param pageName
     * @return
     */
    @RequestMapping("/getPage")
    public ModelAndView getPage(ModelAndView modelAndView,String pageName){
        modelAndView.setViewName(pageName);
        return modelAndView;
    }

    
}
