package com.onebooming.frastructure.controller.admin;

import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.api.QiniuCloudService;
import com.onebooming.frastructure.constant.Types;
import com.onebooming.frastructure.constant.WebConst;
import com.onebooming.frastructure.dto.AttAchDto;
import com.onebooming.frastructure.service.attach.AttAchService;
import com.onebooming.frastructure.utils.Commons;
import com.onebooming.frastructure.utils.TaleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 附件控制器
 *
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/21 12:39 下午
 */
@Api("附件相关接口")
@Controller
@RequestMapping("admin/attach")
public class AttAchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttAchController.class);
    public static final String CLASSPATH = TaleUtils.getUplodFilePath();

    @Autowired
    private AttAchService attAchService;
    @Autowired
    private QiniuCloudService qiniuCloudService;

    @ApiOperation("文件管理首页")
    @GetMapping(value = "")
    public String index(
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
                    int page,
            @ApiParam(name = "limit", value = "条数", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "12")
                    int limit,
            HttpServletRequest request

    ) {
        PageInfo<AttAchDto> atts = attAchService.getAtts(page, limit);

        request.setAttribute("attachs", atts);
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType(), Commons.site_url()));
        request.setAttribute("max_file_size", WebConst.MAX_FILE_SIZE / 1024);
        return "admin/attach";
    }

}
