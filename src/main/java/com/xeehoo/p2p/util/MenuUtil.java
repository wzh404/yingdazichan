package com.xeehoo.p2p.util;

import com.xeehoo.p2p.po.LoanPermission;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/11/3.
 */
public class MenuUtil {
    private static final Logger logger = Logger.getLogger(MenuUtil.class);

    public static boolean mainMenu(Map<String, Object> map, String code){
        logger.info(map + " - " + code);
        if (map == null || map.get(code) == null)
            return false;
        logger.info("--------------");
        return true;
    }

    public static boolean subMenu(Map<String, Object> map, String menuCode, String subMenuCode){
        if (map == null || menuCode == null || subMenuCode == null || map.get(menuCode) == null)
            return false;

        List<LoanPermission> permission = (List<LoanPermission>)map.get(menuCode);
        for (LoanPermission perm : permission){
            if (perm.getPermissionCode().equalsIgnoreCase(subMenuCode)){
                return true;
            }
        }

        return false;
    }
}
