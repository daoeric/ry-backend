package com.ruoyi.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.TRealnameAuth;

import java.util.List;

/**
 * 实名认证Service接口
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
public interface ITRealnameAuthService extends IService<TRealnameAuth>
{
    /**
     * 查询实名认证
     * 
     * @param id 实名认证主键
     * @return 实名认证
     */
    public TRealnameAuth selectTRealnameAuthById(Long id);

    /**
     * 查询实名认证列表
     * 
     * @param tRealnameAuth 实名认证
     * @return 实名认证集合
     */
    public List<TRealnameAuth> selectTRealnameAuthList(TRealnameAuth tRealnameAuth);

    /**
     * 新增实名认证
     * 
     * @param tRealnameAuth 实名认证
     * @return 结果
     */
    public int insertTRealnameAuth(TRealnameAuth tRealnameAuth);

    /**
     * 修改实名认证
     * 
     * @param tRealnameAuth 实名认证
     * @return 结果
     */
    public int updateTRealnameAuth(TRealnameAuth tRealnameAuth);

    /**
     * 批量删除实名认证
     * 
     * @param ids 需要删除的实名认证主键集合
     * @return 结果
     */
    public int deleteTRealnameAuthByIds(Long[] ids);

    /**
     * 删除实名认证信息
     * 
     * @param id 实名认证主键
     * @return 结果
     */
    public int deleteTRealnameAuthById(Long id);
    
    /**
     * 根据商户ID查询实名认证记录
     * 
     * @param customerId 商户ID
     * @return 实名认证记录
     */
    public TRealnameAuth selectByCustomerId(Long customerId);

    boolean approve(Long id, int i, String auditReason,String username);
}