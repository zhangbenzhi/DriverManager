package com.example.drivermanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.drivermanager.MyApplication;
import com.example.drivermanager.R;
import com.example.drivermanager.bean.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.self_service_data)
    Button selfService;
    @BindView(R.id.all_service_data)
    Button allService;
    @BindView(R.id.user_info)
    Button btnUserInfo;
    @BindView(R.id.system_manager)
    Button btnSystemManager;
    @BindView(R.id.logout)
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        UserBean userBean = MyApplication.userBean;
        switch (userBean.type) {
            case 0:
                selfService.setVisibility(View.VISIBLE);
                allService.setVisibility(View.GONE);
                btnUserInfo.setVisibility(View.VISIBLE);
                btnSystemManager.setVisibility(View.GONE);
                break;
            case 1:
                selfService.setVisibility(View.GONE);
                allService.setVisibility(View.VISIBLE);
                btnUserInfo.setVisibility(View.GONE);
                btnSystemManager.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.self_service_data, R.id.user_info, R.id.system_manager, R.id.all_service_data, R.id.logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.self_service_data:
                startActivity(new Intent(this, SelfServiceDataActivity.class));
                break;
            case R.id.user_info:
                startActivity(new Intent(this, UserManagerActivity.class));
                break;
            case R.id.system_manager:
                startActivity(new Intent(this, SystemActivity.class));
                break;
            case R.id.all_service_data:
                startActivity(new Intent(this, AllServiceDataActivity.class));
                break;
            case R.id.logout:
                MyApplication.userBean = null;
                Toast.makeText(this, "退出登录成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    /**
     * 营运数据：
     * id，载客里程，营运收入，服务次数，司机id
     * 司机：
     * id，姓名，性别，手机号，驾龄
     * 管理员：
     * id，姓名，性别
     */

}
