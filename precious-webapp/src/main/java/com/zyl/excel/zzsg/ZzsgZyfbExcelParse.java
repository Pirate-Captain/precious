/**
 * Created on 2016-4-26
 */
package com.zyl.excel.zzsg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ZzsgZyfbExcelParse {
    private static Map<String, String> provMap = new HashMap<String, String>();
    
    static {
      provMap.put("山西", "ss14");
      provMap.put("北京", "ss11");
      provMap.put("天津", "ss12");
      provMap.put("河北", "ss13");
      provMap.put("内蒙古", "ss15");
      provMap.put("辽宁", "ss21");
      provMap.put("上海", "ss31");
      provMap.put("江苏", "ss32");
      provMap.put("吉林", "ss22");
      provMap.put("黑龙江", "ss23");
      provMap.put("浙江", "ss33");
      provMap.put("安徽", "ss34");
      provMap.put("福建", "ss35");
      provMap.put("江西", "ss36");
      provMap.put("山东", "ss37");
      provMap.put("湖北", "ss42");
      provMap.put("河南", "ss41");
      provMap.put("湖南", "ss43");
      provMap.put("广东", "ss44");
      provMap.put("四川", "ss51");
      provMap.put("广西", "ss45");
      provMap.put("海南", "ss46");
      provMap.put("重庆", "ss50");
      provMap.put("云南", "ss53");
      provMap.put("贵州", "ss52");
      provMap.put("西藏", "ss54");
      provMap.put("陕西", "ss61");
      provMap.put("甘肃", "ss62");
      provMap.put("青海", "ss63");
      provMap.put("宁夏", "ss64");
      provMap.put("新疆", "ss65");
    }
}