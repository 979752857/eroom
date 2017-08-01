package com.eroom.web.controller.rent;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.entity.vo.rent.RoomBookVo;
import com.eroom.web.service.rent.RoomBookService;
import com.eroom.web.service.rent.RoomUnLockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/roomunlock")
public class RoomUnLockController extends BaseController {

    @Resource
    private RoomUnLockService roomUnLockService;

    /**
     * 获取预约房间的密码
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRoomPassword")
    @ResponseBody
    public ResultVo getRoomPassword(Long bookId) throws Exception {
        ResultVo result = new ResultVo();
        String pw = roomUnLockService.getRoomPassword(bookId);
        result.setDatas(pw);
        return result;
    }

    /**
     * 扫描二维码解锁
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRoomPasswordByQRCode")
    @ResponseBody
    public ResultVo getRoomPasswordByQRCode() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();

        return result;
    }
}
