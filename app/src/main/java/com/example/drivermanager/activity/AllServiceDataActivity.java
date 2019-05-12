package com.example.drivermanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drivermanager.MyApplication;
import com.example.drivermanager.R;
import com.example.drivermanager.adapter.ServiceAdapter;
import com.example.drivermanager.bean.ServiceDataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 所有营运数据
 */
public class AllServiceDataActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.alert)
    TextView tvAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_service_data);
        ButterKnife.bind(this);
        setTitle("所有营运数据");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取所有营运数据
        List<ServiceDataBean> list = MyApplication.getMyApplication().getDaoSession().getServiceDataBeanDao().loadAll();
        if (list == null || list.size() == 0) {
            tvAlert.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            return;
        }
        tvAlert.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        ServiceAdapter meetingAdapter = new ServiceAdapter(this);
        recyclerView.setAdapter(meetingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        meetingAdapter.setData(list);
    }
}
