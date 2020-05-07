package com.ta.platform.common.tool;

import com.ey.tax.toolset.core.BeanUtil;
import com.ey.tax.toolset.core.collection.CollectionUtil;
import com.ta.platform.common.service.ITreeModelBuildCallback;
import com.ta.platform.common.system.model.TreeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>
 * 树形数据构造工具类
 * <P>
 * 
 */
public class TreeModelUtil {
    public static <S,T extends TreeModel> List<T> getTreeModelList(List<S> recordList, String parentId, ITreeModelBuildCallback<S, T> buildCallback){
        List<T> tree = new ArrayList<>();
        recordList.stream().filter(p-> buildCallback.filter(p, parentId)).forEach(p->{
            T treeModel = buildCallback.build(p);
            treeModel.setParentId(parentId);
            List<T> children = getTreeModelList(recordList, treeModel.getKey(), buildCallback);
            if(CollectionUtil.isNotEmpty(children)){
                treeModel.setChildren(children);
                treeModel.setLeaf(false);
            }else{
                treeModel.setLeaf(true);
            }
            BeanUtil.copyProperties(p, treeModel, new String[]{"children"});
            tree.add(treeModel);
        });
        return tree;
    }
}
