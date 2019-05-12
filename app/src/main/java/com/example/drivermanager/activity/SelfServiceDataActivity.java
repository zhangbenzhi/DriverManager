package com.example.drivermanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drivermanager.MyApplication;
import com.example.drivermanager.R;
import com.example.drivermanager.bean.ServiceDataBean;
import com.example.drivermanager.greendao.ServiceDataBeanDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人营运数据
 */
public class SelfServiceDataActivity extends AppCompatActivity {

    @BindView(R.id.et_mileage)
    EditText etMileage;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.et_times)
    EditText etTimes;
    ServiceDataBean serviceDataBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_service);
        ButterKnife.bind(this);
        setTitle("个人营运数据");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取该用户下的个人营运数据
        serviceDataBean = MyApplication.getMyApplication().getDaoSession().getServiceDataBeanDao().queryBuilder().where(ServiceDataBeanDao.Properties.DriverId.eq(MyApplication.userBean.id)).unique();
        if (serviceDataBean == null) {
            return;
        }
        etMileage.setText(serviceDataBean.mileage);
        etMoney.setText(serviceDataBean.money);
        etTimes.setText(serviceDataBean.times);
    }

    @OnClick({R.id.update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update:
                update();
                break;
        }
    }

    private void update() {
        String mileage = etMileage.getText().toString();
        String money = etMoney.getText().toString();
        String times = etTimes.getText().toString();
        if (TextUtils.isEmpty(mileage)) {
            Toast.makeText(this, "请输入营运里程", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(money)) {
            Toast.makeText(this, "请输入营运金额", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(times)) {
            Toast.makeText(this, "请输入营运次数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (serviceDataBean == null) {
            //添加：
            serviceDataBean = new ServiceDataBean();
            serviceDataBean.driverId = MyApplication.userBean.id;
            serviceDataBean.mileage = mileage;
            serviceDataBean.money = money;
            serviceDataBean.times = times;
            MyApplication.getMyApplication().getDaoSession().getServiceDataBeanDao().insert(serviceDataBean);
        } else {
            //修改：
            serviceDataBean.mileage = mileage;
            serviceDataBean.money = money;
            serviceDataBean.times = times;
            MyApplication.getMyApplication().getDaoSession().update(serviceDataBean);
        }
        Toast.makeText(this, "修改个人运营数据成功", Toast.LENGTH_SHORT).show();
    }

}
