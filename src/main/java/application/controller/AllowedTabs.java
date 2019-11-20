package application.controller;

import application.model.Permission;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AllowedTabs {
    public static AllowedTabs fromPermissions(List<Permission> permissions) {
        AllowedTabs allowedTabs = new AllowedTabs();

        Set<String> permissionSet = permissions.stream().map(p -> p.getName()).collect(Collectors.toSet());

        allowedTabs.hasViewOrder = permissionSet.contains("VIEW_ORDER");
        allowedTabs.hasCreateOrder = permissionSet.contains("CREATE_ORDER");
        allowedTabs.hasExport = permissionSet.contains("EXPORT");
        allowedTabs.hasImport = permissionSet.contains("IMPORT");
        allowedTabs.hasViewListStaff = permissionSet.contains("VIEW_STAFF");
        allowedTabs.hasCreateStaff = permissionSet.contains("CREATE_STAFF");
        allowedTabs.hasViewSalary = permissionSet.contains("VIEW_SALARY");
        allowedTabs.hasListReceipt = permissionSet.contains("VIEW_RECEIPT");
        allowedTabs.hasPay = permissionSet.contains("PAY");
        allowedTabs.hasAddRoleUser = permissionSet.contains("ADD_USER_ROLE");
        allowedTabs.hasAddPermissionRole = permissionSet.contains("ADD_PERMISSION_ROLE");


        return allowedTabs;
    }

    public boolean hasCreateOrder;
    public boolean hasViewOrder;
    public boolean hasImport;
    public boolean hasExport;
    public boolean hasViewListStaff;
    public boolean hasCreateStaff;
    public boolean hasViewSalary;
    public boolean hasListReceipt;
    public boolean hasListOrder;
    public boolean hasPay;
    public boolean hasAddRoleUser;
    public boolean hasAddPermissionRole;

    public boolean hasManageSale() {
        return hasCreateOrder || hasViewOrder;
    }

    public boolean hasManageInventory() {
        return hasImport || hasExport;
    }

    public boolean hasHumanResource() {
        return hasViewListStaff || hasCreateStaff || hasViewSalary;
    }

    public boolean hasManageAccount() {
        return hasListReceipt || hasListOrder || hasPay;
    }

    public boolean hasManageAuthorize() {
        return hasAddRoleUser || hasAddPermissionRole;
    }

    public boolean hasStaff() {
        return hasManageSale() || hasManageInventory() || hasHumanResource() || hasManageAccount();
    }

    public boolean hasAdmin() {
        return hasManageAuthorize();
    }


}
