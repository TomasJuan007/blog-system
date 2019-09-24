package com.ykse.blogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class GameController {

    @RequestMapping(value="/star", method=RequestMethod.GET)
    public ModelAndView getStar() {
        return new ModelAndView("star");
    }

    @RequestMapping(value="/cube", method=RequestMethod.GET)
    public ModelAndView getCube() {
        return new ModelAndView("cube");
    }

    @RequestMapping(value="/starcube", method=RequestMethod.GET)
    public ModelAndView getStarCube() {
        return new ModelAndView("starcube");
    }
}  
