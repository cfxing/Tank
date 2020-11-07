package com.xnj.tank;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author chen xuanyi
 * @create 2020-11-06 22:16
 */
public class ImageTest {

    @Test
    void test(){

        try {
            BufferedImage image1 = ImageIO.read(new File(""));
            //判断是否为空
            //断言：判断条件是否成立，成立则测试通过
            //eg: assertNotNull: 判断是否为空
            assertNotNull(image1);

            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
