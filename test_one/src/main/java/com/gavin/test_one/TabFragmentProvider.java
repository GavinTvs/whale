package com.gavin.test_one;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.gavin.lib_link.constant.ProviderPath;

/**
 * @author : com.gavin
 * @date 2018/9/6.
 */
@Route(path = ProviderPath.TAB_ONE_PROVIDER)
public interface TabFragmentProvider extends IProvider{

    public TabFragment newInstance(String params1,String params2);
}
