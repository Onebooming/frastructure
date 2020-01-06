package com.onebooming.frastructure.controller.admin;


import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.constant.LogActions;
import com.onebooming.frastructure.controller.BaseController;
import com.onebooming.frastructure.dto.cond.PhysicServerCond;
import com.onebooming.frastructure.model.PhysicServerEntity;
import com.onebooming.frastructure.service.log.LogService;
import com.onebooming.frastructure.service.phsicServer.PhysicServerService;
import com.onebooming.frastructure.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Onebooming
 */
@Api("设备表单")
@Controller
@RequestMapping("/admin/hardware")
public class HardwareController extends BaseController {


    @Autowired
    PhysicServerService physicServerService;

    @Autowired
    private LogService logService;


    @ApiOperation("服务器列表页")
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
        PageInfo<PhysicServerEntity> physicServers = physicServerService.getPhysicServerByCond2(new PhysicServerCond(), page, limit);
        /**
         * setAttribute这个方法，在JSP内置对象session和request都有这个方法，
         * 这个方法作用就是保存数据，然后还可以用getAttribute方法来取出。
         * request.setAttribute("physicServers", physicServers)这个方法是将physicServers这个对象保存在request作用域中，然后在转发进入的页面就可以获取到你的值
         */
        request.setAttribute("physicServers", physicServers);
        return "admin/hardware_list";
    }

    @ApiOperation("删除服务器")
    @PostMapping(value = "/delete")
    @ResponseBody
    public APIResponse deleteArticle(
            @ApiParam(name = "id", value = "服务器主键", required = true)
            @RequestParam(name = "id", required = true)
                    Long id,
            HttpServletRequest request
    ) {
        physicServerService.deleteById(id);
        logService.addLog(LogActions.DEL_ARTICLE.getAction(), id + "", request.getRemoteAddr(), this.getUid(request));
        return APIResponse.success();
    }

    @ApiOperation("编辑保存服务器信息")
    @PostMapping("/modify")
    @ResponseBody
    public APIResponse modify(
            HttpServletRequest request,
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(name = "id", required = true)
                    Long id,
            @ApiParam(name = "name", value = "服务器名", required = true)
            @RequestParam(name = "name", required = true)
                    String name,
            @ApiParam(name = "area", value = "地区", required = false)
            @RequestParam(name = "area", required = false)
                    String area,
            @ApiParam(name = "datacenter", value = "数据中心", required = false)
            @RequestParam(name = "datacenter", required = false)
                    String datacenter,
            @ApiParam(name = "department", value = "部门", required = false)
            @RequestParam(name = "department", required = false)
                    String department
            /*@ApiParam(name = "position", value = "位置", required = true)
            @RequestParam(name = "position", required = true)
                    String position,
            @ApiParam(name = "height", value = "高度", required = true)
            @RequestParam(name = "height", required = true)
                    String height,
            @ApiParam(name = "brand", value = "品牌", required = true)
            @RequestParam(name = "brand", required = true)
                    String brand,
            @ApiParam(name = "type", value = "型号", required = false)
            @RequestParam(name = "type", required = false)
                    String type,
            @ApiParam(name = "serialNumber", value = "序列号", required = false)
            @RequestParam(name = "serialNumber", required = false, defaultValue = "默认分类")
                    String serialNumber,
            @ApiParam(name = "bIp", value = "业务IP", required = true)
            @RequestParam(name = "bIp", required = true)
                    String bIp,
            @ApiParam(name = "mgmtIp", value = "管理IP", required = true)
            @RequestParam(name = "mgmtIp", required = true)
                    String mgmtIp*/
    ) {
        PhysicServerEntity physicServerEntity = new PhysicServerEntity();
        physicServerEntity.setId(id);
        physicServerEntity.setName(name);
        physicServerEntity.setArea(area);
        physicServerEntity.setDatacenter(datacenter);
        physicServerEntity.setDepartment(department);
//        physicServerEntity.setPosition(position);
//        physicServerEntity.setHeight(height);
//        physicServerEntity.setBrand(brand);
//        physicServerEntity.setType(type);
//        physicServerEntity.setSerialNumber(serialNumber);
//        physicServerEntity.setbIp(bIp);
//        physicServerEntity.setMgmtIp(mgmtIp);


        physicServerService.updatePhysicServer(physicServerEntity);
        return APIResponse.success();
    }


    @ApiOperation("服务器详情")
    @GetMapping(value = "/profile/{id}")
    public String physicServerProfile(@ApiParam(name = "id", value = "服务器ID", required = true)
                                      @PathVariable
                                              Long id,
                                      HttpServletRequest request) {
        PhysicServerEntity physicServerEntity = physicServerService.findEntityById(id);
        request.setAttribute("server", physicServerEntity);

        return "admin/hardware_edit";
    }


    @ApiOperation("编辑保存服务器信息")
    @PostMapping("/save")
    @ResponseBody
    public APIResponse save(
            HttpServletRequest request,
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(name = "id", required = true)
                    Long id,
            @ApiParam(name = "name", value = "服务器名", required = true)
            @RequestParam(name = "name", required = true)
                    String name,
            @ApiParam(name = "area", value = "地区", required = false)
            @RequestParam(name = "area", required = false)
                    String area,
            @ApiParam(name = "datacenter", value = "数据中心", required = false)
            @RequestParam(name = "datacenter", required = false)
                    String datacenter,

            @ApiParam(name = "position", value = "位置", required = true)
            @RequestParam(name = "position", required = true)
                    String position,
            @ApiParam(name = "height", value = "高度", required = true)
            @RequestParam(name = "height", required = true)
                    String height,
            @ApiParam(name = "department", value = "部门", required = true)
            @RequestParam(name = "department", required = true)
                    String department,
            @ApiParam(name = "user", value = "使用人", required = true)
            @RequestParam(name = "user", required = true)
                    String user,
            @ApiParam(name = "maintainor", value = "运维人员", required = true)
            @RequestParam(name = "maintainor", required = true)
                    String maintainor,

            @ApiParam(name = "mgmtIp", value = "管理IP", required = true)
            @RequestParam(name = "mgmtIp", required = true)
                    String mgmtIp,

            @ApiParam(name = "bIp", value = "业务IP", required = true)
            @RequestParam(name = "bIp", required = true)
                    String bIp,

            @ApiParam(name = "cpuNum", value = "CPU数量", required = true)
            @RequestParam(name = "cpuNum", required = true)
                    Integer cpuNum,

            @ApiParam(name = "memorySum", value = "内存大小", required = true)
            @RequestParam(name = "memorySum", required = true)
                    Integer memorySum,

            @ApiParam(name = "storage", value = "存储容量", required = true)
            @RequestParam(name = "storage", required = true)
                    Integer storage,

            @ApiParam(name = "brand", value = "品牌", required = true)
            @RequestParam(name = "brand", required = true)
                    String brand,

            @ApiParam(name = "serialNumber", value = "序列号", required = false)
            @RequestParam(name = "serialNumber", required = false, defaultValue = "默认分类")
                    String serialNumber,
            @ApiParam(name = "manufactor", value = "制造商", required = true)
            @RequestParam(name = "manufactor", required = true)
                    String manufactor,
            @ApiParam(name = "type", value = "型号", required = true)
            @RequestParam(name = "type", required = true)
                    String type
    ) {
        PhysicServerEntity physicServerEntity = new PhysicServerEntity();
        physicServerEntity.setId(id);
        physicServerEntity.setName(name);
        physicServerEntity.setArea(area);
        physicServerEntity.setDatacenter(datacenter);
        physicServerEntity.setCpuNum(cpuNum);//
        physicServerEntity.setPosition(position);
        physicServerEntity.setMemorySum(memorySum);
        physicServerEntity.setBrand(brand);
        physicServerEntity.setStorage(storage);
        physicServerEntity.setSerialNumber(serialNumber);
        physicServerEntity.setbIp(bIp);
        physicServerEntity.setMgmtIp(mgmtIp);//
        physicServerEntity.setHeight(height);
        physicServerEntity.setType(type);
        physicServerEntity.setManufactor(manufactor);
        physicServerEntity.setDepartment(department);
        physicServerEntity.setUser(user);
        physicServerEntity.setMaintainor(maintainor);


        physicServerService.updatePhysicServer(physicServerEntity);
        return APIResponse.success();
    }


    /**
     * 模糊搜索
     * @param request
     * @param param
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation("关键词搜索结果页")
    @PostMapping(value = "/search")
    public String searchByParamResult(
            HttpServletRequest request,
            @ApiParam(name = "param", value = "关键词", required = false)
            @RequestParam(name = "param", required = true, defaultValue = "")
                    String param,
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
                    int page,
            @ApiParam(name = "limit", value = "每页数量", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "15")
                    int limit
    ) {
        PageInfo<PhysicServerEntity> physicServers = physicServerService.getPhysicServerByParm(param,page,limit);
        /**
         * setAttribute这个方法，在JSP内置对象session和request都有这个方法，
         * 这个方法作用就是保存数据，然后还可以用getAttribute方法来取出。
         * request.setAttribute("physicServers", physicServers)这个方法是将physicServers这个对象保存在request作用域中，然后在转发进入的页面就可以获取到你的值
         */
        request.setAttribute("physicServers", physicServers);
        return "admin/hardware_list";
    }

}
