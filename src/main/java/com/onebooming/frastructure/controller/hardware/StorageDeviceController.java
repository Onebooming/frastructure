package com.onebooming.frastructure.controller.hardware;

import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.controller.BaseController;
import com.onebooming.frastructure.model.InternetEquipment;
import com.onebooming.frastructure.model.StorageDevice;
import com.onebooming.frastructure.service.StorageDevice.StorageDeviceService;
import com.onebooming.frastructure.service.log.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-04-27 21:08
 * @ApiNote
 */
@Api("存储设备")
@Controller
@RequestMapping("admin/storagedevice")
public class StorageDeviceController extends BaseController {
    @Autowired
    private StorageDeviceService storageDeviceService;
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
        PageInfo<StorageDevice> storageDevices = storageDeviceService.getStorageDevice(page,limit);
        request.setAttribute("storagedevices", storageDevices);
        List<StorageDevice> list = storageDevices.getList();
        for (StorageDevice storageDevice : list) {
            System.out.println(storageDevice);
        }
        return "admin/storagedevice_list";
    }
}
