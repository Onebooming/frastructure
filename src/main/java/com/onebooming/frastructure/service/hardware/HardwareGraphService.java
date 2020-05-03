package com.onebooming.frastructure.service.hardware;



import com.onebooming.frastructure.model.PhysicServerEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Onebooming
 * @Date 2020/4/15
 * @Version V1.0
 **/
public interface HardwareGraphService {
    Map<String, Integer> serverRatioByDataCenter();

    /**
     * 获得对应主机
     * @param serverName：虚拟机名称
     * @return
     */
    Map<String, List<String>> serverToSwitchs(String serverName);
}
