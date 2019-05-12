package com.example.drivermanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drivermanager.MyApplication;
import com.example.drivermanager.R;
import com.example.drivermanager.bean.UserBean;
import com.example.drivermanager.greendao.UserBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserManagerActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_phone)
    EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        ButterKnife.bind(this);
        setTitle("用户管理");

        etUsername.setText(MyApplication.userBean.userName);
        etPassword.setText(MyApplication.userBean.password);
        etPhone.setText(MyApplication.userBean.phoneNumber);
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
        String strName = etUsername.getText().toString();
        String strPass = etPassword.getText().toString();
        String strPhone = etPhone.getText().toString();

        if (TextUtils.isEmpty(strName)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strPass)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strPhone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        UserBeanDao userBeanDao = MyApplication.getMyApplication().getDaoSession().getUserBeanDao();
        List<UserBean> list = userBeanDao.queryBuilder()
                .where(UserBeanDao.Properties.UserName.eq(strName))
                .list();
        if (!MyApplication.userBean.userName.equals(strName) && list != null && list.size() > 0) {
            Toast.makeText(this, "该用户名已存在，请更换", Toast.LENGTH_SHORT).show();
            return;
        }
        MyApplication.userBean.userName = strName;
        MyApplication.userBean.password = strPass;
        MyApplication.userBean.phoneNumber = strPhone;
        try {
            userBeanDao.update(MyApplication.userBean);
            Toast.makeText(this, "修改用户信息成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
