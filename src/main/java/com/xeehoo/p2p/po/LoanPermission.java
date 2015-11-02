package com.xeehoo.p2p.po;

/**
 * Created by wangzunhui on 2015/11/2.
 */
public class LoanPermission {
    private Integer permissionId;
    private String permissionName;
    private String permissionCode;
    private Integer permissionPid;
    private String menuCode;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public Integer getPermissionPid() {
        return permissionPid;
    }

    public void setPermissionPid(Integer permissionPid) {
        this.permissionPid = permissionPid;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
