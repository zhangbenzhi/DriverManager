package com.example.drivermanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drivermanager.MyApplication;
import com.example.drivermanager.R;
import com.example.drivermanager.bean.ServiceDataBean;
import com.example.drivermanager.bean.UserBean;
import com.example.drivermanager.greendao.ServiceDataBeanDao;
import com.example.drivermanager.greendao.UserBeanDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 营运数据详情
 */
public class ServiceDataDetailActivity extends AppCompatActivity {

    @BindView(R.id.et_mileage)
    TextView etMileage;
    @BindView(R.id.et_money)
    TextView etMoney;
    @BindView(R.id.et_times)
    TextView etTimes;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    ServiceDataBean serviceDataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);
        ButterKnife.bind(this);
        setTitle("营运数据详情");
        serviceDataBean = (ServiceDataBean) getIntent().getSerializableExtra("data");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (serviceDataBean == null) {
            return;
        }
        UserBean userBean = MyApplication.getMyApplication().getDaoSession().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Id.eq(serviceDataBean.driverId)).unique();
        name.setText(userBean.userName);
        phone.setText(userBean.phoneNumber);
        etMileage.setText(serviceDataBean.mileage);
        etMoney.setText(serviceDataBean.money);
        etTimes.setText(serviceDataBean.times);
    }

    public void goBack(View view) {
        finish();
    }
}
