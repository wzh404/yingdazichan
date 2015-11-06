package com.xeehoo.p2p.po;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzunhui on 2015/11/4.
 */
public class StaffSessionObject {
    private Integer staffId;
    private String staffName;

    private List<LoanPermission> permissions;
    private Map<String, List<LoanPermission>> menus;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public List<LoanPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<LoanPermission> permissions) {
        this.permissions = permissions;
    }

    public Map<String, List<LoanPermission>> getMenus() {
        return menus;
    }

    public void setMenus(Map<String, List<LoanPermission>> menus) {
        this.menus = menus;
    }

    public boolean mainMenu(String code){
        if (menus == null || !menus.containsKey(code))
            return false;

        return true;
    }

    public boolean subMenu(String menuCode, String subMenuCode){
        if (menus == null || menuCode == null || subMenuCode == null || !menus.containsKey(menuCode))
            return false;

        List<LoanPermission> permission = (List<LoanPermission>)menus.get(menuCode);
        for (LoanPermission perm : permission){
            if (perm.getPermissionCode().equalsIgnoreCase(subMenuCode)){
                return true;
            }
        }

        return false;
    }
}
