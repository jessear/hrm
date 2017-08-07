package com.jesse.impl;

import com.jesse.bean.Notice;
import com.jesse.mapper.NoticeMapper;
import com.jesse.service.NoticeService;
import com.jesse.util.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jesse on 2017/6/22 0022.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("notice",notice);
        int recordCount=noticeMapper.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            params.put("pageModel",pageModel);
        }
        List<Notice> notices=noticeMapper.selectByPage(params);
        return notices;
    }

    @Override
    public void removeNoticeById(String id) {
        noticeMapper.deleteById(id);
    }

    @Override
    public Notice findNoticeById(String id) {
        return noticeMapper.selectById(id);
    }

    @Override
    public void addNotice(Notice notice) {
        noticeMapper.save(notice);
    }

    @Override
    public void modifyNotice(Notice notice) {
        noticeMapper.update(notice);
    }
}
