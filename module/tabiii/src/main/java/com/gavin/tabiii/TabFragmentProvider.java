package com.gavin.tabiii;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.gavin.common.constants.ProviderPath;

/**
 * @author : com.gavin
 * @date 2018/9/6.
 */
@Route(path = ProviderPath.TAB_ONE_PROVIDER)
public interface TabFragmentProvider extends IProvider{

    public TabIIIFragment newInstance(String params1, String params2);
}
