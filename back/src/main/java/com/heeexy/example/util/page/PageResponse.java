package com.heeexy.example.util.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author yangtc
 * @Description //分页参数类-返回结果
 * @Date 13:28 2018/12/27
 * @Param
 * @return
 **/
@Data
public class PageResponse implements Serializable{

    private long totalPage;          // 每页显示个数

    private long totalCount;         // 页数

    private List list;       // 排序方式

    private boolean isFilter = false;

    public PageResponse(){}

    public PageResponse(Page page){
        this.totalCount = page.getTotal();
        this.totalPage = page.getPages();
        this.list = page.getRecords();
    }

}
