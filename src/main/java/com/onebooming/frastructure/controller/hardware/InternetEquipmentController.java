package com.onebooming.frastructure.controller.hardware;

import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.constant.LogActions;
import com.onebooming.frastructure.controller.BaseController;
import com.onebooming.frastructure.dto.cond.InternetEquipmentCond;
import com.onebooming.frastructure.model.InternetEquipment;
import com.onebooming.frastructure.service.InternetEquipment.InternetEquipmentService;
import com.onebooming.frastructure.service.log.LogService;

import com.onebooming.frastructure.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Onebooming
 * @Date 2020/4/7
 * @Version V1.0
 **/
@Api("网络设备")
@Controller
@RequestMapping("/admin/iEquipment")
public class InternetEquipmentController extends BaseController {
    @Autowired
    InternetEquipmentService internetEquipmentService;

    @Autowired
    private LogService logService;


    @ApiOperation("网络设备列表页")
    @GetMapping(value = "")
    public String index(
            HttpServletRequest request,
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
                    int page,
            @ApiParam(name = "limit", value = "每页数量", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "15")
                    int limit
    ) {
        PageInfo<InternetEquipment> internetEquipments = internetEquipmentService.getInternetEquipments(page,limit);
        request.setAttribute("internetEquipments", internetEquipments);
        List<InternetEquipment> list = internetEquipments.getList();
        for (InternetEquipment internetEquipment : list) {
            System.out.println(internetEquipment);
        }
        return "admin/internet_list";
    }


    @ApiOperation("删除网络设备")
    @PostMapping(value = "/delete")
    @ResponseBody
    public APIResponse deleteArticle(
            @ApiParam(name = "id", value = "网络设备主键", required = true)
            @RequestParam(name = "id", required = true)
                    Long id,
            HttpServletRequest request
    ) {
        internetEquipmentService.deleteById(id);
        logService.addLog(LogActions.DEL_ARTICLE.getAction(), id + "", request.getRemoteAddr(), this.getUid(request));
        return APIResponse.success();
    }




    @ApiOperation("网络设备详情")
    @GetMapping(value = "/profile/{id}")
    public String internetEquipmentProfile(@ApiParam(name = "id", value = "网络设备ID", required = true)
                                      @PathVariable
                                              Long id,
                                      HttpServletRequest request) {
        InternetEquipment internetEquipment = internetEquipmentService.findInternetEquipmentById(id);
        System.out.println(internetEquipment);
        request.setAttribute("internetEquipment", internetEquipment);
        return "admin/internet_edit";
    }


    @ApiOperation("编辑保存网络设备信息")
    @PostMapping("/save")
    @ResponseBody
    public APIResponse save(
            HttpServletRequest request,
            @ApiParam(name = "InternetEquipment", value = "网络设备参数", required = true)
            @RequestBody Map<String,String> params
            ) throws ParseException {
        internetEquipmentService.save(params);
        return APIResponse.success();
    }


    /**
     * 模糊搜索
     * @param request
     * @param param
     * @return
     */
    @GetMapping(value = "/search/{param}")
    public String searchByParamResult(
            HttpServletRequest request,
            @ApiParam(name = "param", value = "关键词", required = true)
            @PathVariable(name = "param", required = true)
                    String param,
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
                    int page,
            @ApiParam(name = "limit", value = "每页数量", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "15")
                    int limit
    ) throws ServletException, IOException {

        PageInfo<InternetEquipment> internetEquipments = internetEquipmentService.getInternetEquipmentByParamPage(param,page,limit);
        /**
         * setAttribute这个方法，在JSP内置对象session和request都有这个方法，
         * 这个方法作用就是保存数据，然后还可以用getAttribute方法来取出。
         * request.setAttribute("physicServers", physicServers)这个方法是将physicServers这个对象保存在request作用域中，然后在转发进入的页面就可以获取到你的值
         */
        /**
         * setAttribute这个方法，在JSP内置对象session和request都有这个方法，
         * 这个方法作用就是保存数据，然后还可以用getAttribute方法来取出。
         * request.setAttribute("physicServers", physicServers)这个方法是将physicServers这个对象保存在request作用域中，然后在转发进入的页面就可以获取到你的值
         */
        request.setAttribute("internetEquipments", internetEquipments);
        return "admin/internet_list";
    }


    /**
     * 将数据导入Excel表格
     * @param request
     * @return
     */
    @ApiOperation("导出网络设备信息到Excel中")
    @PostMapping(value = "/exportToExcel")
    @ResponseBody
    public APIResponse exportToExcel(
            HttpServletRequest request
    ) {

        if(internetEquipmentService.exportToExcel(new InternetEquipmentCond())){
            return APIResponse.success();
        }
        return APIResponse.fail("文件导出失败");
    }
}
