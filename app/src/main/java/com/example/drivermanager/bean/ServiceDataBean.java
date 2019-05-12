package com.example.drivermanager.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 营运数据
 */
@Entity
public class ServiceDataBean implements Serializable {

    private static final long serialVersionUID = -2940196742313994741L;

    @Id(autoincrement = true)
    public Long id;

    public String mileage;//营运里程
    public String money;//营运金额
    public String times;//营运次数
    public Long driverId;//司机id
    @Generated(hash = 967977993)
    public ServiceDataBean(Long id, String mileage, String money, String times,
            Long driverId) {
        this.id = id;
        this.mileage = mileage;
        this.money = money;
        this.times = times;
        this.driverId = driverId;
    }
    @Generated(hash = 74316550)
    public ServiceDataBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMileage() {
        return this.mileage;
    }
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
    public String getMoney() {
        return this.money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public String getTimes() {
        return this.times;
    }
    public void setTimes(String times) {
        this.times = times;
    }
    public Long getDriverId() {
        return this.driverId;
    }
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

}
