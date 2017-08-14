package com.jesse.bean;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by public1 on 2017/6/21.
 */
@Entity
@Table(name="employee_inf")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    private String id;//id
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name ="dept_id")
    private Dept dept;//员工关联的部门对象
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name ="job_id")
    private Job job;//员工关联的职位对象
    @Column(name = "name")
    private String name;//名称
    @Column(name = "card_id")
    private String card_id;//身份证 注解必须与字段名相同
    @Column(name = "address")
    private String address;//地址
    @Column(name = "post_code")
    private String postCode;//邮政编码
    @Column(name = "tel")
    private String tel;//电话
    @Column(name = "phone")
    private String phone;//手机
    @Column(name = "qq_num")
    private String qqNum;//qq
    @Column(name = "email")
    private String email;//邮箱
    @Column(name = "sex")
    private String sex;//性别
    @Column(name = "party")
    private String party;//政治面貌
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private Date birthday;//生日
    @Column(name = "race")
    private String race;//名族
    @Column(name = "education")
    private String education;//学历
    @Column(name = "speciality")
    private String speciality;//专业
    @Column(name = "hobby")
    private String hobby;//爱好
    @Column(name = "remark")
    private String remark;//备注
    @Column(name = "create_date",insertable = false,updatable = false)
    private Date create_date;//建档日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
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
}
