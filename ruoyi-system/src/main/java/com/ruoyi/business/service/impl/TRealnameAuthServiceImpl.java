package com.ruoyi.business.service.impl;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.service.ITCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TRealnameAuthMapper;
import com.ruoyi.business.domain.TRealnameAuth;
import com.ruoyi.business.service.ITRealnameAuthService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实名认证Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
@Service
public class TRealnameAuthServiceImpl extends ServiceImpl<TRealnameAuthMapper, TRealnameAuth> implements ITRealnameAuthService
{
    @Autowired
    private TRealnameAuthMapper tRealnameAuthMapper;

    @Autowired
    private ITCustomerService tCustomerService;

    /**
     * 查询实名认证
     * 
     * @param id 实名认证主键
     * @return 实名认证
     */
    @Override
    public TRealnameAuth selectTRealnameAuthById(Long id)
    {
        return tRealnameAuthMapper.selectTRealnameAuthById(id);
    }

    /**
     * 查询实名认证列表
     * 
     * @param tRealnameAuth 实名认证
     * @return 实名认证
     */
    @Override
    public List<TRealnameAuth> selectTRealnameAuthList(TRealnameAuth tRealnameAuth)
    {
        return tRealnameAuthMapper.selectTRealnameAuthList(tRealnameAuth);
    }

    /**
     * 新增实名认证
     * 
     * @param tRealnameAuth 实名认证
     * @return 结果
     */
    @Override
    public int insertTRealnameAuth(TRealnameAuth tRealnameAuth)
    {
        tRealnameAuth.setCreateTime(DateUtils.getNowDate());
        return tRealnameAuthMapper.insertTRealnameAuth(tRealnameAuth);
    }

    /**
     * 修改实名认证
     * 
     * @param tRealnameAuth 实名认证
     * @return 结果
     */
    @Override
    public int updateTRealnameAuth(TRealnameAuth tRealnameAuth)
    {
        tRealnameAuth.setUpdateTime(DateUtils.getNowDate());
        return tRealnameAuthMapper.updateTRealnameAuth(tRealnameAuth);
    }

    /**
     * 批量删除实名认证
     * 
     * @param ids 需要删除的实名认证主键
     * @return 结果
     */
    @Override
    public int deleteTRealnameAuthByIds(Long[] ids)
    {
        return tRealnameAuthMapper.deleteTRealnameAuthByIds(ids);
    }

    /**
     * 删除实名认证信息
     * 
     * @param id 实名认证主键
     * @return 结果
     */
    @Override
    public int deleteTRealnameAuthById(Long id)
    {
        return tRealnameAuthMapper.deleteTRealnameAuthById(id);
    }
    
    /**
     * 根据商户ID查询实名认证记录
     * 
     * @param customerId 商户ID
     * @return 实名认证记录
     */
    @Override
    public TRealnameAuth selectByCustomerId(Long customerId)
    {
        return tRealnameAuthMapper.selectByCustomerId(customerId);
    }

    @Override
    @Transactional
    public boolean approve(Long id, int status, String auditReason) {
        UpdateWrapper<TRealnameAuth> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("status", status);
        updateWrapper.set("audit_reason", auditReason);
        boolean result = this.update(updateWrapper);
        if (result) {
            UpdateWrapper<TCustomer> updateWrapper1 = new UpdateWrapper<>();
            updateWrapper1.eq("id", tRealnameAuthMapper.selectById(id).getCustomerId());
            updateWrapper1.set("realname_status", status==1?2:3);
            tCustomerService.update(updateWrapper1);
        }
        return result;
    }
}