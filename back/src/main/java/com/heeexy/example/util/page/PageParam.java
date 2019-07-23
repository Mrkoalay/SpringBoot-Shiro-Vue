package com.heeexy.example.util.page;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author yangtc
 * @Description //分页参数类-请求
 * @Date 13:28 2018/12/27
 * @Param
 * @return
 **/
@Data
public class PageParam implements Serializable{

    private int pageRow = 10;          // 每页显示个数

    private int pageNum = 1;         // 页数

    private String order;       // 排序方式

    private static int maxPageRow = 100; //允许最大的查询条数

    /**
     * 设置条数 限制最大条数
     * @author lhq
     * @param pageRow
     * @return
     */
    public PageParam setPageRow(int pageRow) {
        if (pageRow >= maxPageRow) {
            pageRow = maxPageRow;
        }
        System.out.println(pageRow);
        this.pageRow = pageRow;
        return this;
    }


}
