package com.telecorp.dashqueue.utils;

import java.util.Collection;

/**
 * Created by Saran on 13/10/2560.
 */

public class SafeCollectionUtils {

    public static  boolean isAvailableData(Collection list,int position){
        return !isEmpty(list) && position >= 0 && getSize(list) > position;
    }

    public static int getSize(Collection list){
        return null != list ? list.size() : 0;
    }

    public static boolean isEmpty(Collection list){
        return null != list && list.isEmpty();
    }
}
