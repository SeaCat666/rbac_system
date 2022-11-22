package com.dsc.service;

import com.dsc.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    /**
     * 保存日志信息
     */
    void save(SysLog sysLog) throws Exception;

    /**
     * 查询日志信息
     */
    List<SysLog> findAll() throws Exception;
}
