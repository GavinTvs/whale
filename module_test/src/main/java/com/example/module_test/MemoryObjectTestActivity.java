package com.example.module_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basic.constant.ActivityPath;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author gavin
 * @date 2018-09-05
 * 对象存储在内存的指针 测试
 */
@Route(path = ActivityPath.TEST_TEST)
public class MemoryObjectTestActivity extends AppCompatActivity {

    @BindView(R2.id.tv_verify)
    Button tvVerify;
    @BindView(R2.id.tv_age)
    TextView tvAge;
    @BindView(R2.id.tv_name)
    TextView tvName;

    PersonEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        entity = new PersonEntity("Nike", 18);
        tvName.setText(entity.getName());
        tvAge.setText(entity.getAge()+"");
    }

    private void verifyPerson(PersonEntity personEntity) {
        PersonEntity personEntity2 = new PersonEntity("Tom",99);
        personEntity = personEntity2;

    }

    @OnClick(R2.id.tv_verify)
    public void onViewClicked(View view) {
        if(view.getId() == R.id.tv_verify){
            verifyPerson(entity);
            tvName.setText(entity.getName());
            tvAge.setText(entity.getAge()+"");
        }
    }
}
