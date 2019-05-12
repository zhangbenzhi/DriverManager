package com.example.drivermanager.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.drivermanager.MyApplication;
import com.example.drivermanager.R;
import com.example.drivermanager.activity.ServiceDataDetailActivity;
import com.example.drivermanager.bean.ServiceDataBean;
import com.example.drivermanager.bean.UserBean;
import com.example.drivermanager.greendao.UserBeanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 营运数据适配器
 */
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<ServiceDataBean> serviceDataBeans = new ArrayList<>();
    private Activity activity;

    public ServiceAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ServiceViewHolder(activity.getLayoutInflater().inflate(R.layout.item_meeting, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder meetingViewHolder, int i) {
        ServiceDataBean serviceDataBean = serviceDataBeans.get(i);
        UserBean userBean = MyApplication.getMyApplication().getDaoSession().getUserBeanDao().queryBuilder().where(UserBeanDao.Properties.Id.eq(serviceDataBean.driverId)).unique();
        meetingViewHolder.name.setText("司机师傅名称：" + userBean.userName);
        meetingViewHolder.mileage.setText("营运里程：" + serviceDataBean.mileage);
        meetingViewHolder.money.setText("营运金额：" + serviceDataBean.money);
        meetingViewHolder.times.setText("营运次数：" + serviceDataBean.times);
        meetingViewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ServiceDataDetailActivity.class);
                intent.putExtra("data", serviceDataBean);
                activity.startActivity(intent);
            }
        });
    }

    public void setData(List<ServiceDataBean> list) {
        this.serviceDataBeans.clear();
        if (list != null && list.size() > 0) {
            this.serviceDataBeans.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return serviceDataBeans.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView mileage;
        private TextView money;
        private TextView times;
        private View item;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            mileage = itemView.findViewById(R.id.mileage);
            money = itemView.findViewById(R.id.money);
            times = itemView.findViewById(R.id.times);
            item = itemView.findViewById(R.id.item);
        }
    }
}
