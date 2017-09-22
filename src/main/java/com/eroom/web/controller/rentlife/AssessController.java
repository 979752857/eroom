package com.eroom.web.controller.rentlife;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.RoomAssess;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.rentlife.RoomAssessVo;
import com.eroom.web.service.rentlife.RoomAssessService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/assess")
public class AssessController extends BaseController {

    @Resource
    private RoomAssessService roomAssessService;

    @RequestMapping("/submitAssess")
    @ResponseBody
    public ResultVo submitAssess(Long targetId, Long custId, Double level, String content, String type,
            MultipartFile[] files) throws Exception {

        RoomAssess object = roomAssessService.addAssess(targetId, custId, level, content, type, files);
        ResultVo resultVo = new ResultVo();
        Map<String, Object> data = new HashMap<>();
        resultVo.setSuccess(true);
        if (object != null) {
            data.put("code", SystemConstants.ResultCode.SUCCESS); //成功
        } else {
            data.put("code", SystemConstants.ResultCode.FAIL); //失败
        }
        return resultVo;
    }

    @RequestMapping("/getAssess")
    @ResponseBody
    public ResultVo getAssess(Long targetId) throws Exception {
        List<RoomAssessVo> data = roomAssessService.getRoomAssess(targetId);
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(true);
        resultVo.setDatas(data);
        return resultVo;
    }
}
