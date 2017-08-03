import com.eroom.web.constants.RoomConstants;
import com.eroom.web.entity.bo.RoomRentBo;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.service.rent.RoomRentService;
import com.eroom.web.utils.util.CollectionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tendy on 2017/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class RoomRentServiceTest extends BaseTest{

    @Resource
    private RoomRentService roomRentService;

    @Test
    public void getRoomRentTest() throws Exception {
        RoomRentBo roomRentBo = new RoomRentBo();
        roomRentBo.setRentType(RoomConstants.RoomRent.RentType.JOINT_RENT);
        List<RoomRentVo> list = roomRentService.getRoomRent(roomRentBo);
        if(!CollectionUtil.isEmpty(list)){
            logger.info("\n测试数据："+list.size());
        }else{
            logger.info("\n获取空数据");
        }
    }
}
