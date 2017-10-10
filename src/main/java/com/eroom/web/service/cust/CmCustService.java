package com.eroom.web.service.cust;

import javax.annotation.Resource;

import com.eroom.web.dao.cust.TCustInfoDao;
import com.eroom.web.entity.po.CustInfo;
import com.eroom.web.service.BaseService;
import com.eroom.web.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

import com.eroom.web.constants.CustConstants;
import com.eroom.web.dao.cust.CmCustDao;
import com.eroom.web.entity.po.CmCust;
import com.eroom.web.entity.po.CmCustWx;
import com.eroom.web.entity.po.CmCustWxExt;
import com.eroom.web.utils.exception.SystemException;
import com.eroom.web.utils.util.DateUtil;
import com.eroom.web.utils.util.StringUtil;

@Service
public class CmCustService extends BaseService{

    @Resource
    private CmCustDao cmCustDao;

    @Resource
    private TCustInfoDao custInfoDao;

    @Resource
    private CustInfoWxService cmCustWxService;

    @Resource
    private CmCustWxExtService cmCustWxExtService;

    /**
     * 根据openid查询客户信息
     */
    public CmCust getCmCustByOpenid(String tenantNo, String openid) throws Exception {
        if (StringUtil.isBlank(tenantNo)) {
            throw new SystemException("租户编码不能为空");
        }

        if (StringUtil.isBlank(openid)) {
            throw new SystemException("微信openid不能为空");
        }

        return cmCustDao.getCmCustByOpenid(tenantNo, openid);
    }

    /**
     * 根据openid查询客户信息
     */
    public CustInfo getTCustInfoByCustId(Long custId) throws Exception {
        if(custId == null){
            throw new BusinessException("用户id不能为空");
        }
        return custInfoDao.getTCustInfoByCustId(custId);
    }

    /**
     * 用户注册
     */
    public CustInfo addCustInfo(CustInfo custInfo) throws Exception {
        if(custInfo == null){
            logger.error("CmCustService.addCustInfo注册用户传入null");
            return null;
        }
        logger.error("CmCustService.addCustInfo  custInfo:"+custInfo.toString());
        if(!StringUtil.isBlank(custInfo.getOpenid())){
            CustInfo info = custInfoDao.getTCustInfoByOpenid(custInfo.getOpenid());
            if(info == null){
                if(custInfo.getCustId() == null || custInfo.getCustId() == 0){
                    custInfo = custInfoDao.save(custInfo);
                }
            }else{
                logger.info("CmCustService.addCustInfo  已经注册  custInfo:"+custInfo.toString());
            }
        }
        return custInfo;
    }

    /**
     * 修改用户信息
     */
    public CustInfo updateCustInfo(CustInfo custInfo) throws Exception {
        if(custInfo == null){
            throw new BusinessException("没有获取到修改信息");
        }
        CustInfo t = custInfoDao.get(CustInfo.class, custInfo.getCustId());
        StringUtil.isNullStringParam(custInfo.getName(), custInfo.getEmail(), custInfo.getNickName(), custInfo.getPhone(), custInfo.getQq());
        if(!StringUtil.isBlank(custInfo.getEmail())){
            t.setEmail(custInfo.getEmail());
        }
        if(!StringUtil.isBlank(custInfo.getNickName())){
            t.setNickName(custInfo.getNickName());
        }
        if(!StringUtil.isBlank(custInfo.getPhone())){
            t.setPhone(custInfo.getPhone());
        }
        if(!StringUtil.isBlank(custInfo.getQq())){
            t.setQq(custInfo.getQq());
        }
        custInfoDao.save(t);
        return t;
    }

    /**
     * 下单、绑定手机号同步至客户表
     */
    public void updateCmCustFromWechat(String tenantNo, String openid, String name, String phone)
            throws Exception {
        if (StringUtil.isBlank(tenantNo)) {
            throw new SystemException("租户编码不能为空");
        }

        if (StringUtil.isBlank(openid)) {
            throw new SystemException("微信openid不能为空");
        }

        if (StringUtil.isBlank(phone)) {
            throw new SystemException("手机号码不能为空");
        }

        CmCust cmCust = cmCustDao.getCmCust(tenantNo, openid, phone);

        // 如果客户存在则更新名称和手机号码
        if (cmCust != null) {
            if (!StringUtil.isBlank(name)) {
                cmCust.setWxNickname(name);
            }
            cmCust.setContactTel(phone);
            cmCust.setUpdateTime(DateUtil.getCurrentDate());
            cmCustDao.update(cmCust);
        }

        // 如果不存在则新增一条客户记录
        if (cmCust == null) {
            this.saveCmCustFromWechat(openid);
        }
    }

    /**
     * 客户资料修改,数据同步至客户表
     */
    public void updateCmCustFromWechat(String tenantNo, String openid, CmCustWxExt custWxExt)
            throws Exception {
        if (StringUtil.isBlank(tenantNo)) {
            throw new SystemException("租户编码不能为空");
        }

        if (StringUtil.isBlank(openid)) {
            throw new SystemException("微信openid不能为空");
        }

        if (custWxExt == null) {
            throw new SystemException("客户扩展对象不存在");
        }

        CmCust cmCust = cmCustDao.getCmCust(tenantNo, openid, custWxExt.getContactTel());

        // 如果客户存在则更新名称和手机号码
        if (cmCust != null) {
            cmCust.setCustName(custWxExt.getCustName());
            cmCust.setSex(custWxExt.getSex());
            cmCust.setContactTel(custWxExt.getContactTel());
            cmCust.setContactTel2(custWxExt.getContactTel2());
            cmCust.setContactTel3(custWxExt.getContactTel3());
            cmCust.setContactQq(custWxExt.getContactQq());
            cmCust.setContactWechat(custWxExt.getContactWechat());
            cmCust.setInvestAge(custWxExt.getInvestAge());
            cmCust.setCityCode(custWxExt.getCityCode());
            cmCust.setUpdateTime(DateUtil.getCurrentDate());
            cmCustDao.update(cmCust);
        }

        // 如果不存在则新增一条客户记录
        if (cmCust == null) {
            this.saveCmCustFromWechat(openid);
        }
    }

    /**
     * 保存客户信息
     */
    private void saveCmCustFromWechat(String openid) throws Exception {
        CmCustWx custWx = cmCustWxService.getCmCustByOpenId(openid);
        if (custWx == null) {
            throw new SystemException("微信客户表[openid=" + openid + "]中记录不存在");
        }

        CmCustWxExt custWxExt = cmCustWxExtService.getCmCustWxExtByCustId(custWx.getCustId());
        if (custWxExt == null) {
            throw new SystemException("微信客户扩展表[cust_id=" + custWx.getCustId() + "]中记录不存在");
        }

        CmCust cmCust = new CmCust();
        cmCust.setTenantNo(custWx.getTenantNo());
        cmCust.setCustName(custWxExt.getCustName());
        cmCust.setContactTel(custWxExt.getContactTel());
        cmCust.setContactTel2(custWxExt.getContactTel2());
        cmCust.setContactTel3(custWxExt.getContactTel3());
        cmCust.setContactQq(custWxExt.getContactQq());
        cmCust.setContactWechat(custWxExt.getContactWechat());
        cmCust.setWxNickname(custWx.getNickname());
        cmCust.setWxOpenid(custWx.getOpenid());
        cmCust.setEmail(custWxExt.getEmail());
        cmCust.setSex(custWxExt.getSex());
        cmCust.setProvinceCode(custWxExt.getProvinceCode());
        cmCust.setCityCode(custWxExt.getCityCode());
        cmCust.setAssetLevel(custWxExt.getAssetLevel());
        cmCust.setInvestAge(custWxExt.getInvestAge());
        cmCust.setFromType(CustConstants.CmCust.FromType.WEIXIN);
        cmCust.setState(CustConstants.CmCust.CustState.UNSETTLED);
        cmCust.setCreateTime(DateUtil.getCurrentDate());
        cmCust.setAssignSaleFlag(CustConstants.CmCust.AssignSaleFlag.UNDISTRIBUTED);
        cmCust.setAssignServiceFlag(CustConstants.CmCust.AssignServiceFlag.UNDISTRIBUTED);

        cmCustDao.save(cmCust);
    }

}
