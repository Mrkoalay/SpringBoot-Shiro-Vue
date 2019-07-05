package com.heeexy.example.util.page.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heeexy.example.util.page.PageResponse;

public class EarningsHistoryPageResponse extends PageResponse {
    private String totalIncome;

    public EarningsHistoryPageResponse(Page page, String totalIncome) {
        super(page);
        this.totalIncome = totalIncome;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
}
