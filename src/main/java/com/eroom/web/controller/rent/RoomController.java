package com.eroom.web.controller.rent;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.entity.bo.RoomRentBo;
import com.eroom.web.utils.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.service.rent.RoomRentService;

@Controller
@RequestMapping("/room")
public class RoomController extends BaseController {

    @Resource
    private RoomRentService roomRentService;

    /**
     * 获取房源信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRoom")
    @ResponseBody
    public ResultVo getRoom(String type, String position, String price, String sort) throws Exception {
        ResultVo result = new ResultVo();
        RoomRentBo roomRentBo = new RoomRentBo();
        logger.info("RoomController.getRoom   type:"+type+", position:"+position+", price:"+price+", sort:"+sort);
        if("整租".equals(type)){
            roomRentBo.setRentType(RoomConstants.RoomRent.RentType.WHOLE_RENT);
        }else if("合租".equals(type)){
            roomRentBo.setRentType(RoomConstants.RoomRent.RentType.JOINT_RENT);
        }else if("短租".equals(type)){
            roomRentBo.setRentType(RoomConstants.RoomRent.RentType.SHORT_RENT);
        }
        if(!StringUtil.isBlank(position)){
            String[] array = position.split("-");
            if(RoomConstants.RoomCondition.DISTRICT.equals(array[0])){
                if(array.length == 2){
                    roomRentBo.setDistrictId(Long.valueOf(array[1]));
                }else if(array.length == 3){
                    roomRentBo.setBussinessId(Long.valueOf(array[2]));
                }
            }else if(RoomConstants.RoomCondition.SUBWAY.equals(array[0])){
                if(array.length == 2){
                    roomRentBo.setSubwayId(Long.valueOf(array[1]));
                }else if(array.length == 3){
                    roomRentBo.setStationId(Long.valueOf(array[2]));
                }
            }
        }
        if(!StringUtil.isBlank(price)){
            price = price.substring(0, price.length()-1);
            String[] array = price.split("-");
            roomRentBo.setPriceMin(new BigDecimal(array[0]));
            roomRentBo.setPriceMax(new BigDecimal(array[1]));
        }
        if(!StringUtil.isBlank(sort)){
            if("价格".equals(sort)){

            }else if("区域".equals(sort)){

            }
        }
        List<RoomRentVo> list = roomRentService.getRoomRent(roomRentBo);
        result.setDatas(list);

        return result;
    }

    /**
     * 获取房源信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRoomPage")
    @ResponseBody
    public ResultVo getRoom(int page) throws Exception {
        ResultVo result = new ResultVo();
        List<RoomRentVo> list = roomRentService.getRoomRent(null, page);
        result.setDatas(list);
        return result;
    }

    /**
     * 获取房源筛选条件接口
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRoomCondition")
    @ResponseBody
    public ResultVo getRoomCondition() throws Exception {
        ResultVo result = new ResultVo();
        List<RoomRentVo> list = null;
        result.setDatas(list);
        return result;
    }

}
