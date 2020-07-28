package com.thoughtworks.springbootemployee.model;

public class Company {
    private int companyId;
    private int page;
    private int pageSize;

    public Company(int companyId, int page, int pageSize) {
        this.companyId = companyId;
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
