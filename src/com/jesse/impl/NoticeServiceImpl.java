package com.jesse.impl;

import com.jesse.bean.Notice;
import com.jesse.dao.NoticeDao;
import com.jesse.service.NoticeService;
import com.jesse.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jesse on 2017/6/22 0022.
 */
public class NoticeServiceImpl implements NoticeService {
    /**
     * 自动注入持久层dao对象
     */
    @Autowired
    NoticeDao noticeDao;

    @Transactional(readOnly = true)
    @Override
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("notice",notice);
        int recordCount=noticeDao.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<Notice> notices=noticeDao.selectByPage(params);
        return notices;
    }

    @Override
    public void removeNoticeById(Integer id) {
        noticeDao.deleteById(id);
    }

    @Override
    public Notice findNoticeById(Integer id) {
        return noticeDao.selectById(id);
    }

    @Override
    public void addNotice(Notice notice) {
        noticeDao.save(notice);
    }

    @Override
    public void modifyNotice(Notice notice) {
        noticeDao.update(notice);
    }
}
