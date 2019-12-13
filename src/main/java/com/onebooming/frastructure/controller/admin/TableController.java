package com.onebooming.frastructure.controller.admin;


import com.onebooming.frastructure.constant.LogActions;
import com.onebooming.frastructure.constant.WebConst;
import com.onebooming.frastructure.controller.BaseController;
import com.onebooming.frastructure.model.OptionsDomain;
import com.onebooming.frastructure.service.log.LogService;
import com.onebooming.frastructure.service.option.OptionService;
import com.onebooming.frastructure.utils.APIResponse;
import com.onebooming.frastructure.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Donghua.Chen on 2018/4/30.
 */
@Api("设备表单")
@Controller
@RequestMapping("/admin/table")
public class TableController extends BaseController {


    @ApiOperation("进入设置页")
    @GetMapping(value = "")
    public String setting(HttpServletRequest request){
        return "admin/table";
    }








}
