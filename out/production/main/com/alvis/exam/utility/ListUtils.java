package com.alvis.exam.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtils {
    /**
     * 把两个list里边相同的数据取出
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<Integer> getXiangTong(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        for (Integer integer : list2) {
            if (list1.contains(integer)) {
                result.add(integer);
            }
        }
        return result;
    }


    /**
     * 把两个list里边不同的数据取出
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<Integer> getDiffrent(List<Integer> list1, List<Integer> list2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(list1.size() + list2.size());
        List<Integer> diff = new ArrayList<Integer>();
        List<Integer> maxList = list1;
        List<Integer> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }

        for (Integer string : maxList) {
            map.put(string, 1);
        }

        for (Integer string : minList) {
            Integer cc = map.get(string);
            if (cc != null) {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        return diff;
    }


    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

}
