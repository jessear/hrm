package com.jesse.mapper;

import com.jesse.bean.Notice;
import java.util.List;
import java.util.Map;

/**
 * Created by public1 on 2017/6/21.
 */
public interface NoticeMapper {
    /**
     * 分页查询公告
     * @param params
     * @return
     */
    List<Notice> selectByPage(Map<String, Object> params);

    /**
     * 查询公告数目
     * @param params
     * @return
     */
    Integer count(Map<String, Object> params);

    /**
     * 根据id查询公告
     * @param id
     * @return
     */
    Notice selectById(String id);

    /**
     * 删除公告
     * @param id
     */
    void deleteById(String id);

    /**
     * 保存公告
     * @param notice
     */
    void save(Notice notice);

    /**
     * 更新公告
     * @param notice
     */
    void update(Notice notice);
}
