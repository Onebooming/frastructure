package com.onebooming.frastructure.controller.advance;

import com.onebooming.frastructure.controller.admin.IndexController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/21 3:04 下午
 */
@Api("首页")
@Controller
@RequestMapping(value = "/advance")
public class AdvanceIndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);


    @ApiOperation("进入首页")
    @GetMapping(value = "")
    public String index(HttpServletRequest request){
        LOGGER.info("Enter admin index method");
        return "/advance/index4";
    }

    @ApiOperation("进入数据表格页")
    @GetMapping(value = "datatable")
    public String dataTable(HttpServletRequest request){
        LOGGER.info("Enter admin index method");
        return "/advance/pages/tables/data";
    }

    @ApiOperation("进入数据表格页")
    @GetMapping(value = "simple")
    public String simpleTable(HttpServletRequest request){
        return "/advance/pages/tables/simple";
    }
}
