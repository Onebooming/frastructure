package com.onebooming.frastructure.controller.hardware;

import com.onebooming.frastructure.service.hardware.HardwareGraphService;
import com.onebooming.frastructure.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: 硬件资源的图形化显示
 * @Author Onebooming
 * @Date 2020/4/15
 * @Version V1.0
 **/
@Api("硬件资源的图形化显示")
@Controller
@RequestMapping("/admin/hardware/graph")
public class HardwareGraphController {
    @Autowired
    HardwareGraphService hardwareGraphService;

    @ApiOperation("硬件统计图表页")
    @GetMapping(value = "")
    public String graph(
            HttpServletRequest request) {
        return "admin/cmdb_hardware_graph";
    }

    @ApiOperation("数据中心硬件占比饼状图")
    @PostMapping(value = "/statistics/datacenters")
    @ResponseBody
    public Map<String, Integer> datacenter(
            HttpServletRequest request) {
        Map<String, Integer> datas = hardwareGraphService.serverRatioByDataCenter();
        return datas;
    }

    @ApiOperation("根据服务器名返回与之相连的交换机")
    @PostMapping(value = "/statistics/server2switch")
    @ResponseBody
    public Map<String, List<String>> switchListBySeverName(
            HttpServletRequest request, @RequestParam(required = true,name = "serverName") String severName) {
        Map<String, List<String>> relationResult = hardwareGraphService.serverToSwitchs(severName);
        if(relationResult != null && relationResult.size() > 0){
            return relationResult;
        }
        return null;
    }
}
