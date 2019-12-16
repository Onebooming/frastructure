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
        PageInfo<PhysicServerEntity> physicServers = physicServerService.getPhysicServerByCond(new PhysicServerCond(), page, limit);
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
    ){
        physicServerService.deleteArticleById(id);
        logService.addLog(LogActions.DEL_ARTICLE.getAction(), id + "", request.getRemoteAddr(), this.getUid(request));
        return APIResponse.success();
    }

    @ApiOperation("编辑保存服务器信息")
    @PostMapping("/modify")
    @ResponseBody
    public APIResponse modifyArticle(
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
                    String mgmtIp
    ){
        PhysicServerEntity physicServerEntity = new PhysicServerEntity();
        physicServerEntity.setId(id);
        physicServerEntity.setName(name);
        physicServerEntity.setArea(area);
        physicServerEntity.setDatacenter(datacenter);
        physicServerEntity.setPosition(position);
        physicServerEntity.setHeight(height);
        physicServerEntity.setBrand(brand);
        physicServerEntity.setType(type);
        physicServerEntity.setSerialNumber(serialNumber);
        physicServerEntity.setbIp(bIp);
        physicServerEntity.setMgmtIp(mgmtIp);


        physicServerService.updatePhysicServer(physicServerEntity);
        return APIResponse.success();
    }



}
