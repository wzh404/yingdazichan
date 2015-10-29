package com.xeehoo.p2p.filter;

import org.apache.catalina.ssi.ByteArrayServletOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by wangzunhui on 2015/10/29.
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
    private PrintWriter cachedWriter;
    private CharArrayWriter bufferedWriter;
    private ByteArrayServletOutputStream byteArrayServletOutputStream;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public ResponseWrapper(HttpServletResponse response) {
        super(response);

        // 这个是我们保存返回结果的地方
        bufferedWriter = new CharArrayWriter();
        byteArrayServletOutputStream = new ByteArrayServletOutputStream();

        // 让所有结果通过这个PrintWriter写入到bufferedWriter
        cachedWriter = new PrintWriter(bufferedWriter);
    }

    @Override
    public ServletOutputStream getOutputStream(){
        return byteArrayServletOutputStream;
    }

    @Override
    public PrintWriter getWriter() {
        return cachedWriter;
    }

    /**
     * 获取原始的HTML页面内容。
     *
     * @return
     */
    public String getResult() {
        if (bufferedWriter.size() == 0){
            return new String(byteArrayServletOutputStream.toByteArray());
        }
        else{
            return bufferedWriter.toString();
        }
    }
}
