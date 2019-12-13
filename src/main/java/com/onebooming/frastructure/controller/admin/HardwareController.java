package com.onebooming.frastructure.controller.admin;


import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.constant.LogActions;
import com.onebooming.frastructure.constant.WebConst;
import com.onebooming.frastructure.controller.BaseController;
import com.onebooming.frastructure.dto.cond.ContentCond;
import com.onebooming.frastructure.model.ContentDomain;
import com.onebooming.frastructure.model.OptionsDomain;
import com.onebooming.frastructure.service.content.ContentService;
import com.onebooming.frastructure.service.log.LogService;
import com.onebooming.frastructure.service.meta.MetaService;
import com.onebooming.frastructure.service.option.OptionService;
import com.onebooming.frastructure.utils.APIResponse;
import com.onebooming.frastructure.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Donghua.Chen on 2018/4/30.
 */
@Api("设备表单")
@Controller
@RequestMapping("/admin/hardware")
public class HardwareController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ContentService contentService;

    @Autowired
    private MetaService metaService;

    @Autowired
    private LogService logService;


    @ApiOperation("文章页")
    @GetMapping(value = "")
    public String index(
            HttpServletRequest request,
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
                    int page,
            @ApiParam(name = "limit", value = "每页数量", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "15")
                    int limit
    ){
        PageInfo<ContentDomain> articles = contentService.getArticlesByCond(new ContentCond(), page, limit);
        request.setAttribute("articles", articles);
        return "admin/hardware_list";
    }

}
