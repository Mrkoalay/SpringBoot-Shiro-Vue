package com.heeexy.example.service.impl;

import com.heeexy.example.entity.WechatUser;
import com.heeexy.example.dao.WechatUserDao;
import com.heeexy.example.service.WechatUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangtengcan
 * @since 2019-07-19
 */
@Service
public class WechatUserServiceImpl extends ServiceImpl<WechatUserDao, WechatUser> implements WechatUserService {

}
