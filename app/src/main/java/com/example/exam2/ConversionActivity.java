package com.example.exam2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exam2.adapter.RlvAdapter;
import com.example.exam2.base.BaseActivity;
import com.example.exam2.bean.PayBean;
import com.example.exam2.presenter.ConversionPresenter;
import com.example.exam2.view.ConversionView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConversionActivity extends BaseActivity<ConversionPresenter> implements ConversionView {

    @BindView(R.id.tool_bar_conversion)
    Toolbar toolBarConversion;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_phone_sure)
    EditText etPhoneSure;
    @BindView(R.id.tv_banlce_conversion)
    TextView tvBanlceConversion;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_conversion)
    TextView tvConversion;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.btn_canlse)
    Button btnCanlse;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    private ArrayList<PayBean.DataBean.ListBean> datas;
    private RlvAdapter adapter;
    private String banlace;
    private int service;

    @Override
    protected int getLayout() {
        return R.layout.activity_conversion;
    }

    @Override
    protected void initPresenter() {
        presenter = new ConversionPresenter();
    }

    @Override
    protected void initView() {
        toolBarConversion.setTitle("");
        setSupportActionBar(toolBarConversion);

        banlace = getIntent().getStringExtra("banlace");
        tvBanlceConversion.setText("账户余额:" + banlace);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        datas = new ArrayList<>();

        adapter = new RlvAdapter(this, datas);
        rv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setData(PayBean payBean) {
        datas.addAll(payBean.getData().getList());

        tvPay.setText("兑换手续费" + payBean.getData().getService() + ".00元");

        //手续费
        service = payBean.getData().getService();
        adapter.setShouxu(service);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.btn_canlse, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_canlse:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btn_sure:
                exchange();//确认兑换
                break;
        }
    }

    private void exchange() {
        //获得手机号
        String phone = etPhone.getText().toString().trim();
        String surePhone = etPhone.getText().toString().trim();

        //a)	两次输入的手机号必须输入一致 b)	账户余额必须大于所选item 需要的金额 c)	库存数量必须大于0
        PayBean.DataBean.ListBean listBean = datas.get(adapter.index);

        int stockCount = listBean.getStockCount();//获得库存
        int price = listBean.getPrice();//获得条目所需的价格

        int p = service + price;//所需价格
        int m = Integer.parseInt(banlace);

        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(surePhone) && phone.equals(surePhone) && stockCount > 0 && m >p) {
            Toast.makeText(this, "兑换成功", Toast.LENGTH_SHORT).show();
            int s = m - p;
            Intent intent = new Intent();
            intent.putExtra("ye", s + "");
            setResult(200, intent);
            finish();
        } else {
            Toast.makeText(this, "兑换失败", Toast.LENGTH_SHORT).show();
        }
    }
}
