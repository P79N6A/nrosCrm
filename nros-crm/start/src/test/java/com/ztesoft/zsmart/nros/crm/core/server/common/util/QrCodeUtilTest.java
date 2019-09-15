package com.ztesoft.zsmart.nros.crm.core.server.common.util;

import com.alibaba.fastjson.JSONArray;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * @author zhou.xiaofeng
 * @description 二维码工具类单元测试
 * @date 2019-06-10
 */
@Slf4j
public class QrCodeUtilTest extends MockitoTest {

    @Test
    public void QrCodeTest() {
        log.info("QrCode createQrCode start ");
        try {
            System.out.println("test running");
            String url = "http://www.qq.com";
            //生成二维码存放位置
            String path = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "testQrcode";
            String fileName = "temp.jpg";
            String code = QrCodeUtil.createQrCode(url, path, fileName);
            log.info("QrCode createQrCode  result ->. " + JSONArray.toJSON(code));

        } catch (Exception e) {
            log.error("QrCode createQrCode  fail ->. ", e);
            e.printStackTrace();
        }
        log.info("QrCode createQrCode  end ");
    }


}
