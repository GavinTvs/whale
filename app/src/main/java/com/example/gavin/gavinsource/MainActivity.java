package com.example.gavin.gavinsource;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basic.constant.ActivityPath;
import com.example.common.UrlUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author gavin
 * 首页
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final String url = "http://test-static.beritaqu.aimodou.net/detail/2018/08/30/71832.html?content_id=71832&key=df06lDYZcPYhW7DDn4JT9_j1Vj2_TCqRV7hd_po5G8VqICtjjO71oROlUD-ONwWUhGTrQ5Inb-LWa1vFxGYK32BnQBYdJFJ9GL5Np9K6DDqkuwhPnXYns1_8jWyc57pqwOnBLIrS&pv_id=%7B9140E941-C2E9-9F89-1C18-65A8F03177E7%7D&cid=256&cat=15&rss_source=liputan6&fr=5&hj=0&deep=1&show_position=content_rec&skip_ad=0&v=10049&lat=31.2111506&lon=121.6222892&network=wifi&dc=357536080405717&dtu=google&showCoinTips=1&uuid=f82933c5326546edbff7eee5510c630d&vn=1.4.9_debug&isOutSide=false&fontSize=normal&cid=256&show_position=content_rec&android_channel=google&pack_channel=google&tk=ACHzP-ujAGpEvbfdS-c95eS_y1z97cGYBf5xdHRodw&dev=1#613bP7drJ0-P2FVFd43Fpso4Ej_553_dXudvt56NgQ4kaTK1CSfk8-8vjyA00nhhSkVlcAe_uqQwBQ";
        tvText.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Map<String,String> queryMap = UrlUtils.splitQuery(url);
                    StringBuilder result = new StringBuilder();
                    for (Map.Entry<String, String> entry:queryMap.entrySet()){
                        result.append("key:"+entry.getKey()+"  value:"+entry.getValue()+"\n");
                    }
                    tvText.setText(result);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @OnClick({R.id.tv_one, R.id.tv_two, R.id.tv_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_one:
                ARouter.getInstance()
                        .build(ActivityPath.NATIVE_MAIN)
                        .navigation(this);

                break;
            case R.id.tv_two:
                break;
            case R.id.tv_three:
                break;
            default:
                break;
        }
    }
}
