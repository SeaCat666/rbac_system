package com.dsc.dao;

import com.dsc.domain.SysLog;

import java.util.List;

public interface ISysLogDao {

    /**
     * 保存日志信息
     */
    public void save(SysLog sysLog) throws Exception;

    /**
     * 查询日志信息
     */
    List<SysLog> findAll() throws Exception;
}
