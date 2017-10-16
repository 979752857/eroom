package com.eroom.web.controller.system;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eroom.web.service.system.SystemCfgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.service.system.SystemParamService;

@Controller
@RequestMapping("/systemParam")
public class ParamController extends BaseController {

    @Resource
    private SystemParamService sysParamService;

    @Resource
    private SystemCfgService systemCfgService;

    @RequestMapping("/getAllSystemParam")
    @ResponseBody
    public Object getAllSystemParam(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        ResultVo result = new ResultVo();
        Map<String, String> map = sysParamService.getSystemParam();

        result.setDatas(map);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/getAllSystemCfg")
    @ResponseBody
    public Object getAllSystemCfg(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        ResultVo result = new ResultVo();
        Map<String, String> map = systemCfgService.getBufferAllCfgValue();
        result.setDatas(map);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/getSystemCfg")
    @ResponseBody
    public Object getSystemCfg(String cfgType, String cfgCode)
            throws Exception {
        ResultVo result = new ResultVo();
        String str = systemCfgService.getCfgValue(cfgType, cfgCode);

        result.setDatas(str);
        result.setSuccess(true);
        return result;
    }

}
