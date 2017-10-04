package com.eroom.web.controller.system;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.service.base.BaseSubwayStationService;
import com.eroom.web.service.system.SystemParamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/base")
public class BaseDataController extends BaseController {

    @Resource
    private BaseSubwayStationService baseSubwayStationService;

    /**
     * 获取房源地区地铁站条件信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/getAllCondition")
    @ResponseBody
    public Object getAllCondition(Long cityId) throws Exception {
        ResultVo result = new ResultVo();
        Map<String, Object> list = baseSubwayStationService.getSubwayStation(cityId);
        result.setDatas(list);
        result.setSuccess(true);
        return result;
    }

}
