package com.example.exam2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.exam2.R;
import com.example.exam2.bean.PayBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvAdapter extends RecyclerView.Adapter<RlvAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PayBean.DataBean.ListBean> datas;
    public int index = -1;//刚进来一个商品都不选
    private int shouxu;

    public int getShouxu() {
        return shouxu;
    }

    public void setShouxu(int shouxu) {
        this.shouxu = shouxu;
    }

    public RlvAdapter(Context context, ArrayList<PayBean.DataBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PayBean.DataBean.ListBean listBean = datas.get(position);

        holder.tvName.setText(listBean.getName());
        holder.tvPrice.setText(listBean.getPrice() + getShouxu() + "元");
        holder.tvSellCount.setText("销售个数:" + listBean.getSellCount());
        holder.tvStockCount.setText("库存:" + listBean.getStockCount());
        Glide.with(context).load(listBean.getPic()).into(holder.ivImg);

        holder.rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    index = position;
                    listBean.setClick(true);
                    for (int i = 0; i < datas.size(); i++) {
                        PayBean.DataBean.ListBean bean = datas.get(i);
                        if (i != position) {
                            bean.setClick(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });
        //赋值语句
        holder.rb.setChecked(datas.get(position).isClick());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rb)
        RadioButton rb;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_stockCount)
        TextView tvStockCount;
        @BindView(R.id.tv_sellCount)
        TextView tvSellCount;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
