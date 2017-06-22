package com.jesse.service;


import com.jesse.bean.Notice;
import com.jesse.util.PageModel;
import java.util.List;

/**
 * Created by public1 on 2017/6/22.
 */
public interface NoticeService {
    /**
     * 获取所有通知分页
     * @param notice
     * @param pageModel
     * @return
     */
    List<Notice> findNotice(Notice notice, PageModel pageModel);

    /**
     * 根据id删除通知
     * @param id
     */
    void removeNoticeById(Integer id);

    /**
     * 根据id查询通知
     * @param id
     * @return
     */
    Notice findNoticeById(Integer id);

    /**
     * 添加通知
     * @param notice
     */
    void addNotice(Notice notice);

    /**
     * 修改通知
     * @param notice
     */
    void modifyNotice(Notice notice);
}
