package com.onebooming.frastructure.service.hardware.impl;

import com.onebooming.frastructure.dao.PhysicServerDao;
import com.onebooming.frastructure.dto.cond.PhysicServerCond;
import com.onebooming.frastructure.model.PhysicServerEntity;
import com.onebooming.frastructure.service.hardware.HardwareGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Onebooming
 * @Date 2020/4/15
 * @Version V1.0
 **/
@Service
public class HardwareGraphServiceImpl  implements HardwareGraphService {
    @Autowired
    private PhysicServerDao physicServerDao;

    @Override
    public Map<String, Integer> serverRatioByDataCenter() {
        PhysicServerCond physicServerCond = new PhysicServerCond();
        //通过设置datacenter条件，分别从数据库查询不同数据中心的记录---->改进：根据分组查询记录总数；提升性能
        physicServerCond.setDatacenter("B9企业");
        List<PhysicServerEntity> B9EList = physicServerDao.getPhysicServersByCond(physicServerCond);
        physicServerCond.setDatacenter("B9互联网");
        List<PhysicServerEntity> B9IList = physicServerDao.getPhysicServersByCond(physicServerCond);
        physicServerCond.setDatacenter("HQ");
        List<PhysicServerEntity> HQList = physicServerDao.getPhysicServersByCond(physicServerCond);
        physicServerCond.setDatacenter("B4");
        List<PhysicServerEntity> B4List = physicServerDao.getPhysicServersByCond(physicServerCond);
        int B9I_count = B9IList.size();
        int B9E_count = B9EList.size();
        int HQ_count = HQList.size();
        int B4_count = B4List.size();
        Integer count = (B9I_count + B9E_count + HQ_count + B4_count);
        // 计算比例

        Map<String, Integer> result = new HashMap<>();
        result.put("B9E",100*B9E_count/count); //计算B9企业服务器的比例
        result.put("B9I",100*B9I_count/count); //计算B9互联网服务器的比例
        result.put("HQ",100*HQ_count/count); //计算HQ服务器的比例
        result.put("B4",100*B4_count/count); //计算B4服务器的比例

        return result;
    }

    @Override
    public Map<String, List<String>> serverToSwitchs(String serverName) {
        System.out.println("serverName="+serverName);
        Map<String, List<String>> result = new HashMap<>();
        List<String> strings = physicServerDao.searchSwitchNameList(serverName);
        for (String string : strings) {
            System.out.println(string);
        }
        if (strings.size() > 0){
             result.put(serverName,strings);
             return result;
        }else {
            return null;
        }
    }
}
