package com.ruoyi.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.TRealnameAuth;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 实名认证Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
public interface TRealnameAuthMapper extends BaseMapper<TRealnameAuth>
{
    /**
     * 查询实名认证
     * 
     * @param id 实名认证主键
     * @return 实名认证
     */
    TRealnameAuth selectTRealnameAuthById(Long id);

    /**
     * 查询实名认证列表
     * 
     * @param tRealnameAuth 实名认证
     * @return 实名认证集合
     */
    List<TRealnameAuth> selectTRealnameAuthList(TRealnameAuth tRealnameAuth);

    /**
     * 新增实名认证
     * 
     * @param tRealnameAuth 实名认证
     * @return 结果
     */
    int insertTRealnameAuth(TRealnameAuth tRealnameAuth);

    /**
     * 修改实名认证
     * 
     * @param tRealnameAuth 实名认证
     * @return 结果
     */
    int updateTRealnameAuth(TRealnameAuth tRealnameAuth);

    /**
     * 删除实名认证
     * 
     * @param id 实名认证主键
     * @return 结果
     */
    int deleteTRealnameAuthById(Long id);

    /**
     * 批量删除实名认证
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteTRealnameAuthByIds(Long[] ids);
    
    /**
     * 根据商户ID查询实名认证记录
     * 
     * @param customerId 商户ID
     * @return 实名认证记录
     */
    @Select("SELECT * FROM t_realname_auth WHERE customer_id = #{customerId} ORDER BY create_time DESC LIMIT 1")
    TRealnameAuth selectByCustomerId(@Param("customerId") Long customerId);
}