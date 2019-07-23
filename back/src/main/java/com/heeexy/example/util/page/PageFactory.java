package com.heeexy.example.util.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> buildPage(PageParam pageParam) {
        Page page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageRow());
        /*if (Order.ASC.getDes().equals(pageParam.getOrder())) {
            page.setAsc(Order.ASC.getDes());
        } else {
            page.setAsc(Order.DESC.getDes());
        }*/
        return page;
    }


}
