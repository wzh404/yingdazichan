package com.xeehoo.p2p;

import com.sun.istack.internal.logging.Logger;
import com.xeehoo.p2p.mybatis.mapper.UserMapper;
import com.xeehoo.p2p.po.LoanUser;
import com.xeehoo.p2p.util.IDCard;
import com.xeehoo.p2p.util.InterestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = YdzcApplication.class)
@WebAppConfiguration
public class YingdazichanApplicationTests {
	Logger logger = Logger.getLogger(YingdazichanApplicationTests.class);

    @Autowired
    private UserMapper userMapper;

	@Test
	public void contextLoads() {
//		IDCard ic = new IDCard();
//		boolean a = ic.verify("610425197204042611");
//		logger.info(ic.getBirth() + " - " + ic.getSex() + " : " + a);
//		assert(a);
	}

	@Test
	public void saveUser(){
//        LoanUser userinfo = new LoanUser();
//
//        userinfo.setLoginName("abcd1");
//        java.util.Date regtime = new java.util.Date(System.currentTimeMillis());
//        userinfo.setRegisterTime(regtime);
//        userinfo.setRegisterIP("127.0.0.1");
//        userinfo.setMobile("13501185106");
//        userinfo.setLoginPwd(userinfo.encryptPwd("123456"));
//        userinfo.setUserStatus(0x01);
//        userMapper.saveUser(userinfo);
//        assert(userinfo.getUserId() > 0);
		Date d = new Date();
		Date c = new Date(116, 2, 7);
        System.out.println(c.toString());
		InterestUtil.getStages(d, c);
	}
}
