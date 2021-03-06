package com.imory.shop.controller;

import com.imory.annotation.SessionCheck;
import com.imory.base.ResultBean;
import com.imory.user.bean.UserBase;
import com.imory.utils.UnirestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * <p>名称</p>
 * <p/>
 * <p>wikiURL</p>
 *
 * @author zb.jiang
 * @version 1.0
 * @Date 2017/9/30
 */
@Controller
@RequestMapping("/shop")
public class ShopCartController {

    @RequestMapping("/cartDetail")
    @SessionCheck
    public String cartDetail(HttpSession session, Model model)
    {
        UserBase userBase = (UserBase) session.getAttribute(UserBase.USER_SESSION_ID);

        ResultBean resultBean = (ResultBean) UnirestUtils
                .postObjectResponse("/shop/cartDetail?userId=" + userBase.getUser_id(), ResultBean.class);
        if (resultBean.getSuccess())
        {
            model.addAttribute("shopPingCartList", resultBean.getResultMap().get("shopPingCartList"));
        }
        return "/shop/shopcart";
    }

}
