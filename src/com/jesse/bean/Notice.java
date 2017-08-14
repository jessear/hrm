package com.jesse.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by public1 on 2017/6/21.
 */
@Entity
@Table(name="notice_inf")
public class Notice implements Serializable {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    private String id;//编号
    @Column(name = "title")
    private String title;//标题
    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name = "content",columnDefinition="CLOB")
    private String content;//内容
    @Column(name = "create_date" ,insertable = false,updatable = false)
    private Date create_date;//发布日期
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name ="user_id",referencedColumnName="id",updatable=false,insertable=false,nullable=true)
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
