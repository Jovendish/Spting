package icu.redamancy.cloud.service;

import icu.redamancy.common.model.dao.cloud.HouseNumberDaoServiceImpl;
import icu.redamancy.common.model.dao.cloud.UnitDaoServiceImpl;
import icu.redamancy.common.model.pojo.cloud.HouseNumber;
import icu.redamancy.common.model.pojo.cloud.Unit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author redamancy
 * @Date 2022/6/7 14:06
 * @Version 1.0
 */
@Service
public class HouseNumberService {

    @Resource
    private UnitDaoServiceImpl unitDaoService;

    @Resource
    private HouseNumberDaoServiceImpl houseNumberDaoService;


    public Long addUnit(Unit unit) {

        unitDaoService.save(unit);

        return unit.getId();
    }


    public Boolean addHouseNumber(HouseNumber houseNumber, Unit unit) {

        Long unitID = this.addUnit(unit);
        houseNumber.setUnit(unitID);
        return houseNumberDaoService.save(houseNumber);
    }
}
