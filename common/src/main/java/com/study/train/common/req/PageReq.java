package com.study.train.common.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PageReq {
    @Min(value=1,message = "页码不能为空")
    private int page;
    @Min(value=1,message = "每页条数不能为空")
    @Max(value = 100,message = "每页条数不能超过100")
    private int pageSize;

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
