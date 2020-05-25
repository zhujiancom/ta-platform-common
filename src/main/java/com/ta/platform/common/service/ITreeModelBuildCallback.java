package com.ta.platform.common.service;

import com.ta.platform.common.system.model.TreeModel;

import java.util.Map;

/**
 * Creator: zhuji
 * Date: 5/7/2020
 * Time: 2:50 PM
 * Description:
 */
public interface ITreeModelBuildCallback<S, T extends TreeModel> {
    boolean filter(S src, String parentId);

    T build(S src, Map<String,Object> parameter);
}
