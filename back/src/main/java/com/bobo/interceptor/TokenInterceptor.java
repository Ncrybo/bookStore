package com.bobo.interceptor;

import com.alibaba.fastjson.JSON;
import com.bobo.entity.Result;
import com.bobo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.sqlite.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//这是一个请求的拦截器，验证token
public class TokenInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception{
        String token = req.getHeader("token");
        System.out.println("当前的token为： "+token);
        if (token == null) {    //token为空
            this.writerErrorMsg(302,"请登录",resp);
            return false;
        }
        //检验并解析token，如果token过期返回null
        String claims = JwtUtils.verifyToken(token);
        if (claims == null) {
            this.writerErrorMsg(303,"失效，请重新登录",resp);
            return false;
        }
        //走到这里，说明校验通过，设置用户 信息到request理，在controller中从request域中获取用户信息
        req.setAttribute("username",claims);
        return true;
    }

    //利用response直接输出错误信息
    private void writerErrorMsg(Integer state, String msg, HttpServletResponse response) throws IOException {
        Result result = new Result();
        result.setCode(state);
        result.setMessage(msg);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
