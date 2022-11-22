package com.dsc.service.impl;

import com.dsc.dao.ISysLogDao;
import com.dsc.domain.SysLog;
import com.dsc.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {

    @Resource
    private ISysLogDao sysLogDao;

    /**
     * 保存日志信息
     */
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    /**
     * 查询日志信息
     */
    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
