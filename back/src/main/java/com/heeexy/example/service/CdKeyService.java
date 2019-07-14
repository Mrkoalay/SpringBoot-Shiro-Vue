package com.heeexy.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heeexy.example.entity.CdKey;
import com.heeexy.example.util.Response;
import com.heeexy.example.util.page.PageParam;

/**
 * @author:
 * @date: 2017/10/24 16:06
 */
public interface CdKeyService extends IService<CdKey> {


    Response myList(PageParam pageParam, CdKey cdKey);
}
