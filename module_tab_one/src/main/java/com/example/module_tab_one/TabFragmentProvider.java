package com.example.module_tab_one;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.example.basic.constant.ProviderPath;

/**
 * @author : gavin
 * @date 2018/9/6.
 */
@Route(path = ProviderPath.TAB_ONE_PROVIDER)
public interface TabFragmentProvider extends IProvider{

    public TabFragment newInstance(String params1,String params2);
}
