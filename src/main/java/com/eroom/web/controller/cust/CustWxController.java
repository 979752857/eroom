package com.eroom.web.controller.cust;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.constants.CustConstants;
import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.CmCustWx;
import com.eroom.web.entity.po.CmCustWxExt;
import com.eroom.web.entity.po.SystemBaseExt;
import com.eroom.web.entity.po.TCustInfo;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.service.cust.CmCustService;
import com.eroom.web.service.cust.CmCustWxExtService;
import com.eroom.web.service.cust.CustInfoWxService;
import com.eroom.web.service.system.SystemBaseService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.exception.LoginTimeOutException;

@Controller
@RequestMapping("/custWx")
public class CustWxController extends BaseController {

    @Resource
    private CmCustService cmCustService;

    @Resource
    private CustInfoWxService custInfoWxService;

    @Resource
    private CmCustWxExtService cmCustWxExtService;

    @Resource
    private SystemBaseService systemBaseService;

    /**
     * 获取用户信息，并将custid openid存入session
     * 
     * @return
     * @throws Exception
     * @author tendy
     */
    @RequestMapping("/getCustInfo")
    @ResponseBody
    public ResultVo getCustInfo() throws Exception {
        String openid = request.getParameter("openid");
        ResultVo result = new ResultVo();
        TCustInfo custInfo = custInfoWxService.getTCustInfoByOpenId(openid);
        // 存入session中
        SessionVo cust = new SessionVo();
        cust.setCustId(custInfo.getCustId());
        cust.setOpenid(custInfo.getOpenid());
        session.setAttribute(CustConstants.CUST_SESSION, cust);
        logger.info("用户登录");
        result.setDatas(custInfo);
        return result;
    }

    @RequestMapping("/getAgentInfo")
    @ResponseBody
    public ResultVo getAgentInfo() throws Exception {
        String custId = request.getParameter("custId");
        ResultVo result = new ResultVo();
        CmCustWx cmCust = custInfoWxService.getCmCustByCustId(Long.parseLong(custId));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("agent", cmCust);
        // 分享所需要的参数
        SystemBaseExt systemBaseExt = systemBaseService.getSystemBaseExt();
        map.put("systemBaseExt", systemBaseExt);
        result.setDatas(map);
        return result;
    }

    /**
     * 个人中心初始化
     * 
     * @return
     * @throws Exception
     * @author zhangym
     */
    @RequestMapping("/showPersonal")
    @ResponseBody
    public ResultVo showPersonal() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo custSession = this.getCustSession();
        Map<String, Object> map = new HashMap<String, Object>();
        CmCustWx cmCust = custInfoWxService.getCmCustByOpenId(custSession.getOpenid());
        map.put("headimg", cmCust.getHeadImgUrl());
        map.put("nickname", cmCust.getNickname());
        map.put("custId", cmCust.getCustId());
        SystemBaseExt systemBaseExt = systemBaseService.getSystemBaseExt();
        if (ObjectUtils.isEmpty(systemBaseExt)) {
            throw new LoginTimeOutException();
        }
        map.put("serviceUrl", systemBaseExt.getServiceUrl());
        map.put("aboutUsUrl", systemBaseExt.getAboutUrl());
        result.setDatas(map);
        if (ObjectUtils.isEmpty(custSession)) {
            throw new LoginException("请在登录后进行此操作");
        }

        return result;
    }

    /**
     * 获取客户的扩展信息
     * 
     * @author jingbao
     */
    @RequestMapping("/getCmCustWxExt")
    @ResponseBody
    public ResultVo getCmCustWxExt() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo custSession = this.getCustSession();
        if (ObjectUtils.isEmpty(custSession)) {
            throw new LoginException("请在登录后进行此操作");
        }
        CmCustWxExt cmCustWxExt = cmCustWxExtService
                .getCmCustWxExtByCustId(custSession.getCustId());
        result.setDatas(cmCustWxExt);
        return result;
    }

    /**
     * 新增或者修改客户的扩展信息
     * 
     * @author jingbao
     */
    @RequestMapping("/saveUpdateCmCustWxExt")
    @ResponseBody
    public ResultVo saveUpdateCmCustWxExt(CmCustWxExt cmCustWxExt) throws Exception {
        if (cmCustWxExt == null) {
            throw new BusinessException("客户的扩展信息不能为空");
        }

        ResultVo result = new ResultVo();
        SessionVo custSession = this.getCustSession();

        // 保存更新的记录
        cmCustWxExtService.saveUpdateCmCustWxExt(cmCustWxExt, custSession.getCustId(),
                custSession.getTenantNo(), custSession.getOpenid());

        result.setMessage("操作成功");
        return result;
    }

}
