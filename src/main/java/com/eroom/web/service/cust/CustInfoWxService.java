package com.eroom.web.service.cust;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.eroom.web.constants.CustConstants;
import com.eroom.web.dao.cust.CmCustWxDao;
import com.eroom.web.dao.cust.TCustInfoDao;
import com.eroom.web.entity.po.CmCustWx;
import com.eroom.web.entity.po.SystemBase;
import com.eroom.web.entity.po.CustInfo;
import com.eroom.web.service.system.SystemBaseService;
import com.eroom.web.service.wechat.WechatService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.EmojiUtil;
import com.eroom.web.utils.util.StringUtil;

@Service
public class CustInfoWxService extends BaseService{

    @Resource
    private CmCustWxDao cmCustWxDao;
    
    @Resource
    private TCustInfoDao tCustInfoDao;

    @Resource
    private WechatService wechatService;

    @Resource
    private SystemBaseService systemBaseService;

    public CmCustWx getCmCustByOpenId(String openid) throws Exception {
        return cmCustWxDao.getCmCustByOpenId(openid);
    }
    
    public CustInfo getTCustInfoByOpenId(String openid) throws Exception {
    	return tCustInfoDao.getTCustInfoByOpenid(openid);
    }

    public CmCustWx getCmCustByQrCode(String qrCode) throws Exception {
        return cmCustWxDao.getCmCustByQrCode(qrCode);
    }

    public CmCustWx getCmCustByCustId(Long custId) throws Exception {
        return cmCustWxDao.getCmCustByCustId(custId);
    }

    public void saveCmCust(CmCustWx cmCust) throws Exception {
        cmCustWxDao.save(cmCust);
    }

    public void updateCmCust(CmCustWx cmCust) throws Exception {
        cmCustWxDao.update(cmCust);
    }

    /**
     * 微信登录
     * 
     * @param code
     * @return
     * @throws Exception
     */
    public CustInfo addByWechat(String code) throws Exception {
        SystemBase systemBase = systemBaseService.getSystemBase();
        if (ObjectUtils.isEmpty(systemBase)) {
            throw new BusinessException("未获取到机构信息");
        }
        Map<String, String> map = wechatService.getParamForWechat(systemBase, code);
        loginfo("CustInfoWxService.addByWechat   通过code获取的内容：   map:{} ", map.toString());
        String openid = map.get("openid");
        if (StringUtil.isBlank(openid)) {
            throw new BusinessException("登录的openid标识为空...");
        }

        String accessToken = map.get("access_token");
        CustInfo custInfo = tCustInfoDao.getTCustInfoByOpenid(openid);
        if (!ObjectUtils.isEmpty(custInfo) && !StringUtil.isBlank(custInfo.getNickName())) {
            return custInfo;
        } else if (ObjectUtils.isEmpty(custInfo)) {
            custInfo = new CustInfo();
            JSONObject js = wechatService.getWeixinInfo(accessToken, openid);
            loginfo("微信鉴权  新用户  通过accessToken获取用户信息   js:{} ", js.toJSONString());
            String nickname = js.getString("nickname");
            if (StringUtil.isBlank(nickname)) {
                nickname = "*";
            } else {
                nickname = EmojiUtil.filterEmoji(nickname);
            }
            custInfo.setOpenid(openid);
            custInfo.setNickName(nickname);
            custInfo.setImage(js.getString("headimgurl"));
            Date nowTime = new Date();
            custInfo.setUpdateTime(nowTime);
            custInfo.setIsOwner(CustConstants.CustInfo.IsOwner.RENTER);
            // 如果新用户 设置创建时间及关注时间
            custInfo.setState(SystemConstants.State.ACTIVE);
            custInfo.setCreateTime(nowTime);
            tCustInfoDao.save(custInfo);
        } else if (StringUtil.isBlank(custInfo.getNickName())) {
            JSONObject js = wechatService.getWeixinInfo(accessToken, openid);
            loginfo("微信鉴权  老用户  通过accessToken获取用户信息   js:{} ", js.toJSONString());
            String nickname = js.getString("nickname");
            if (StringUtil.isBlank(nickname)) {
                nickname = "*";
            } else {
                nickname = EmojiUtil.filterEmoji(nickname);
            }
            custInfo.setOpenid(openid);
            custInfo.setNickName(nickname);
            custInfo.setImage(js.getString("headimgurl"));
            Date nowTime = new Date();
            custInfo.setUpdateTime(nowTime);
            // 如果新用户 设置创建时间及关注时间
            custInfo.setState(SystemConstants.State.ACTIVE);
            tCustInfoDao.update(custInfo);
        }
        custInfo = tCustInfoDao.getTCustInfoByOpenid(openid);
        return custInfo;
    }

    /**
     * 注册成为经纪人
     * 
     * @param cmCust
     * @throws Exception
     */
    public CmCustWx becomeAgent(CmCustWx cmCust) throws Exception {
        // 获取基本accesstoken
        String accessToken = wechatService.getBasisAccessToken();
        // 获取二维码
        String qrCode = wechatService.getQRCode(accessToken, cmCust.getTenantNo(),
                cmCust.getCustId());
        cmCust.setQrCode(qrCode);
        cmCust.setAgentFlag(CustConstants.CmCust.AgentFlag.YES);
        cmCust.setAgentType(CustConstants.CmCust.AgentType.ONE);
        return cmCust;
    }

    public void addCustByAgent(String openid, String tenantNo, String qrCode) throws Exception {
        CmCustWx agent = this.getCmCustByQrCode(qrCode);
        if (ObjectUtils.isEmpty(agent)) {
            throw new BusinessException("未查询到此用户信息");
        }
        CmCustWx oldCust = this.getCmCustByOpenId(openid);
        if (ObjectUtils.isEmpty(oldCust)) {
            CmCustWx cmCust = new CmCustWx();
            cmCust.setAuthFlag(CustConstants.CmCust.AuthFlag.NOT_AUTH);
            cmCust.setOpenid(openid);
            cmCust.setTenantNo(tenantNo);
            Date nowTime = new Date();
            cmCust.setUpdateTime(nowTime);
            cmCust.setCreateTime(nowTime);
            cmCust.setFollowTime(nowTime);
            cmCust.setState(CustConstants.CmCust.State.NOLMAL);
            cmCust.setAgentFlag(CustConstants.CmCust.AgentFlag.NO);
            cmCust.setAgentCustId(agent.getCustId());
            cmCust.setAgentGroupNo(agent.getAgentGroupNo());
            this.saveCmCust(cmCust);
        }
    }

}
