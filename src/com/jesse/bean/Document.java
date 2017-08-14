package com.jesse.bean;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by public1 on 2017/6/21.
 */
@Entity
@Table(name="document_inf")
public class Document implements Serializable {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    private String id;//编号
    @Column(name = "title")
    private String title;//标题
    @Column(name = "filename")
    private String filename;//文件名
    @Transient
    private MultipartFile file;//文件
    @Column(name = "remark")
    private String remark;//描述
    @Column(name = "create_date" ,insertable = false,updatable = false)
    private Date create_date;//上传时间
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name ="user_id")
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
