
/**
 * 系统项目名称
 * com.supermarket.common.util
 * ImageThumbnailator.java
 * 
 * 2015年11月23日-上午11:42:43
 * 2015
 * 
 */
 
package com.medical.manager.common.util;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


/**
 * 
 * ImageThumbnailator 图像处理工具类，主要用来生成缩略图
 * 
 * 2015年11月23日 上午11:42:43
 * 
 * @version 1.0.0
 * 
 */
public class ImageThumbnailatorUtil {
    /**
     * compressThumbsBySize 按大小压缩图片
     * (这里描述这个方法适用条件 – 可选)
     * @param orgImgPath 原图路径
     * @param newImgPath 新图路径
     * @param width 新图宽
     * @param high 新图高
     * @throws IOException 
     * @since  1.0.0
     */
    public static void compressThumbsBySize(String orgImgPath, 
            String newImgPath, int width, int high) throws IOException {
        /*
        * size(width,height) 若图片横比200小，高比300小，不变
        * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
        * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
        */
        Thumbnails.of(orgImgPath).size(width, high).toFile(newImgPath);
    }
    /**
     * compressThumbsBySize 按大小压缩图片(不按照比例)
     * (这里描述这个方法适用条件 – 可选)
     * @param orgImgPath 原图路径
     * @param newImgPath 新图路径
     * @param width 新图宽
     * @param high 新图高
     * @throws IOException 
     * @since  1.0.0
     */
    public static void compressThumbsBySizeWithNoRate(String orgImgPath, 
            String newImgPath, int width, int high) throws IOException {
        Thumbnails.of(orgImgPath).size(width, high).keepAspectRatio(false).toFile(newImgPath);
    }
    /**
     * compressThumbsByRate 按比例缩放新图片
     * (这里描述这个方法适用条件 – 可选)
     * @param orgImgPath 原图片
     * @param newImgPath 新图片
     * @param rate 比例值（小于1，就缩小）
     * @throws IOException 
     * @exception 
     * @since  1.0.0
     */
    public static void compressThumbsByRate(String orgImgPath, 
            String newImgPath, double rate) throws IOException {
        Thumbnails.of(orgImgPath).scale(rate).toFile(newImgPath);
    }
    
    /**
     * rotateThumbs 旋转图片
     * (这里描述这个方法适用条件 – 可选)
     * @param orgImgPath 原图
     * @param newImgPath 新图
     * @param width 宽度
     * @param high 高度
     * @param angle 旋转度数
     * @throws IOException 
     * @exception 
     * @since  1.0.0
     */
    public static void rotateThumbs(String orgImgPath, 
            String newImgPath, int width, int high, int angle) throws IOException{
        Thumbnails.of(orgImgPath).size(width, high).rotate(angle).toFile(newImgPath);
    }
    /**
     * watermarkThumbs 在右下角添加水印
     * (这里描述这个方法适用条件 – 可选)
     * @param orgImgPath 原图
     * @param newImgPath 新图
     * @param watermarkImgPath 水印图
     * @param width 宽度
     * @param high 高度
     * @throws IOException 
     * @exception 
     * @since  1.0.0
     */
    public static void watermarkThumbs(String orgImgPath, String newImgPath, String watermarkImgPath, int width,
            int high) throws IOException {
        Thumbnails.of(orgImgPath).size(width, high)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(watermarkImgPath)), 0.5f).outputQuality(0.8f)
                .toFile(newImgPath);
    }
}
