package com.example.exam2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et_balance)
    EditText etBalance;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_banlce)
    TextView tvBanlce;
    @BindView(R.id.btn_run)
    Button btnRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolBar.setTitle("");
        setSupportActionBar(toolBar);

        tvBanlce.setVisibility(View.GONE);
        tvOk.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_run)
    public void onViewClicked() {
        String banlace = etBalance.getText().toString();
        Intent intent = new Intent(this, ConversionActivity.class);
        intent.putExtra("banlace", banlace);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            String ye = data.getStringExtra("ye");
            tvBanlce.setText("余额为:" + ye);

            tvBanlce.setVisibility(View.VISIBLE);
            tvOk.setVisibility(View.VISIBLE);

            tv.setVisibility(View.GONE);
            etBalance.setVisibility(View.GONE);
            btnRun.setVisibility(View.GONE);
        }
    }
}
