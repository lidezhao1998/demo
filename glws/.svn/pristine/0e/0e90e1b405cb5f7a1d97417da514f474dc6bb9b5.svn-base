package com.ruoyi.system.service.impl.gis;

import com.ruoyi.system.domain.gis.Codes;
import com.ruoyi.system.domain.gis.EologicalAttributes;
import com.ruoyi.system.mapper.gis.EologicalAttributesMapper;
import com.ruoyi.system.service.gis.EologicalAttributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunlei
 * @description
 * @date 2020/08/11/17:26
 */
@Service
public class EologicalAttributesServiceImpl implements EologicalAttributesService {
    @Autowired
    EologicalAttributesMapper eologicalAttributesMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean getCoefficient() {
        try {
            List<EologicalAttributes> eologicalCoefficient = eologicalAttributesMapper.getEologicalCoefficient();
            for (EologicalAttributes eologicalAttributes : eologicalCoefficient) {
                redisTemplate.opsForValue().set(String.valueOf(eologicalAttributes.getId()), String.valueOf(Codes.conversionCoefficient(eologicalAttributes.getGoalCode())));

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public String getAttributeName(Integer id) {
        EologicalAttributes eological = eologicalAttributesMapper.getAttributeName(id);
        if (eological != null) {
            return (eological.getGoal() + eological.getFamilie()) +
                    (eological.getSubordinate() == null ? "" : eological.getSubordinate()) +
                    (eological.getFollow() == null ? "" : eological.getFollow());
        }
        return "";
    }
}
