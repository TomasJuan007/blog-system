package com.ykse.blogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 游戏控制器
 * 
 * @author huangtao
 *
 */
@Controller
@RequestMapping("/")
public class GameController {  
	
    /**
     *获取“旋转的星火”页面
     * 
     * @return
     */
    @RequestMapping(value="/star", method=RequestMethod.GET)
    public ModelAndView getStar() {
        ModelAndView modelAndView = new ModelAndView("star");
        return modelAndView;
    }
    
    /**
     *获取“旋转的方块”页面
     * 
     * @return
     */
    @RequestMapping(value="/cube", method=RequestMethod.GET)
    public ModelAndView getCube() {
        ModelAndView modelAndView = new ModelAndView("cube");
        return modelAndView;
    }
    
    /**
     *获取“火星方块”页面
     * 
     * @return
     */
    @RequestMapping(value="/starcube", method=RequestMethod.GET)
    public ModelAndView getStarCube() {
        ModelAndView modelAndView = new ModelAndView("starcube");
        return modelAndView;
    }
}  
