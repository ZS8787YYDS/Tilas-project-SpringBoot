package com.zisheng.Service.imple;

import com.zisheng.Mapper.LoginMapper;
import com.zisheng.Service.LoginService;
import com.zisheng.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public Emp login(Emp emp) {
        return loginMapper.login(emp);
    }
}
