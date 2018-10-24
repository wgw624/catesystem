package com.wgw.cateringsystem.util;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2018/10/24 15:23
 *
 * @Author: weiguangwei
 */
public class RequestStreamParamUtil {
    public static JSONObject getJsonObj(HttpServletRequest request) throws IOException {
        InputStream is = request.getInputStream();
        byte[]buffer =new byte[1024];
        int len = 0;
        StringBuffer sb = new StringBuffer();
        while(-1 !=(len=is.read(buffer))){
            String res = new String(buffer,0,len,"utf-8");
            sb.append(res);
        }
        return JSONObject.fromObject(sb.toString());
    }
}
