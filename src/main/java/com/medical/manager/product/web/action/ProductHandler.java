/**
 * 
 */
package com.medical.manager.product.web.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.medical.manager.common.pager.PagerInfo;
import com.medical.manager.common.web.action.BaseHandler;
import com.medical.manager.product.web.model.TblBrand;
import com.medical.manager.product.web.model.TblProduct;
import com.medical.manager.product.web.service.IProductService;

/**
 * @author jason
 *
 * 2015-12-2 上午9:45:36
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping("/product")
public class ProductHandler extends BaseHandler{
    
    private  Logger logger=Logger.getLogger(ProductHandler.class);
    
    @Resource
    private IProductService productService;
    
    /**
     * 跳转到品牌主页
     * (这里描述这个方法适用条件 – 可选)
     * @param session
     * @return
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/brandIndex.do")
    public String brandIndex(HttpSession session){
        String timeout=logginTimeOut(session);
        if (timeout!=null) {
            return timeout;
        }
        return "product/brand/index";
    }
    
    /**
     * 跳转到品牌主页
     * (这里描述这个方法适用条件 – 可选)
     * @param session
     * @return
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/productIndex.do")
    public String productIndex(HttpSession session,Model model){
        String timeout=logginTimeOut(session);
        if (timeout!=null) {
            return timeout;
        }
        List<TblBrand> tblBrands=this.productService.queryAllBrandList();
        model.addAttribute("tblBrands", tblBrands);
        return "product/product/productList";
    }
    
    /**
     * 查询品牌
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param brandName 品牌名称
     * @param page 当前页数
     * @param rows 显示个数
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/queryBrandList.do")
    public void queryBrandList(HttpServletResponse response,String brandName,Integer page,Integer rows){
        logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
        try {
            List<TblBrand> tblBrands=this.productService.queryBrandList(brandName,page,rows);
            writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblBrand>) tblBrands)));
        } catch (Exception e) {
            logger.error("查询失败", e);
            writeFailMsg(response, "查询失败");
        }
    }
    
    /**
     * 保存品牌
     * (这里描述这个方法适用条件 – 可选)
     * @param session
     * @param response
     * @param tblBrand
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/saveOrUpdateBrand.do")
    public void saveOrUpdateBrand(HttpSession session,HttpServletResponse response,TblBrand tblBrand){
        if (tblBrand!=null) {
            List<TblBrand> tblBrands=this.productService.queryBrandByBrand(tblBrand);
            if (tblBrands!=null && tblBrands.size()>0) {
                writeFailMsg(response, "品牌已经存在！");
                return;
            }
            try {
                if (tblBrand.getNo()==null) {
                    logger.info("新增品牌.....");
                    productService.saveBrand(tblBrand);
                    writeSuccessMsg(response, "新增品牌成功！");
                }else {
                    logger.info("更新品牌....");
                    productService.updateBrand(tblBrand);
                    writeSuccessMsg(response, "更新品牌成功！");
                }
            } catch (Exception e) {
                logger.error("操作品牌失败!", e);
            }
        }
        
    }
    
    /**
     * 删除品牌
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param no
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/deleteBrand.do")
    public void deleteBrand(HttpServletResponse response,Long no){
        logger.info("删除品牌...");
        try {
            this.productService.deleteBrand(no);
            writeSuccessMsg(response, "删除成功！");
        } catch (Exception e) {
            logger.error("删除失败", e);
            writeFailMsg(response, "删除失败！");
        }
        
    }
    
    /**
     * 新增或者更新商品
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param tblProduct
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/saveOrUpdateProduct.do")
    public void saveOrUpdateProduct(HttpServletResponse response,TblProduct tblProduct){
        if (tblProduct.getProductId()==null) {
            logger.info("新增商品...");
            try {
                this.productService.saveProduct(tblProduct);
                writeSuccessMsg(response, "新增商品成功");
            } catch (Exception e) {
                logger.error("新增商品失败！", e);
                writeFailMsg(response, "新增商品失败！");
            }
        }else {
            logger.info("修改商品...");
            try {
                this.productService.updateProduct(tblProduct);
                writeSuccessMsg(response, "修改商品成功！");
            } catch (Exception e) {
                logger.error("修改商品失败！", e);
                writeFailMsg(response, "修改商品失败！");
            }
        }
        
    }
    
    /**
     * 删除商品
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param productId 商品id
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/deleteProduct.do")
    public void deleteProduct(HttpServletResponse response,Long productId){
        logger.info("删除商品");
        try {
            this.productService.deleteProduct(productId);
            writeSuccessMsg(response, "删除商品成功！");
        } catch (Exception e) {
            logger.error("删除商品失败", e);
            writeFailMsg(response, "删除商品失败");
        }
    }
    
    /**
     * 查询商品信息
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param brandName 品牌名称
     * @param page 当前页数
     * @param rows 显示个数
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/queryProductList.do")
    public void queryProductList(HttpServletResponse response,String productName,String productNo,Integer page,Integer rows){
        logger.info("请求第：" + page + " 页，每页：" + rows + " 条记录！");
        try {
            List<TblProduct> tblProducts=this.productService.queryProductList(productName,productNo,page,rows);
            writeMsg(response, JSON.toJSONString(PagerInfo.converFromPage((Page<TblProduct>) tblProducts)));
        } catch (Exception e) {
            logger.error("查询数据失败", e);
            writeFailMsg(response, "查询数据失败");
        }
    }
    
    /**
     * 查询所有品牌
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @exception
     * @since 1.0.0
     */
    @RequestMapping("/queryAllBrandList.do")
    public void queryAllBrandList(HttpServletResponse response){
        logger.info("查询所有品牌");
        try {
            List<TblBrand> tblBrands=this.productService.queryAllBrandList();
            writeMsg(response, JSON.toJSONString(tblBrands));
        } catch (Exception e) {
            logger.error("查询品牌失败",e);
            writeFailMsg(response, "查询品牌失败");
        }
    }
    
    /**
     * 导出商品
     * (这里描述这个方法适用条件 – 可选)
     * @param response 响应
     * @param searchVo 查询条件
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping("/productExport.do")
    public void productExport(HttpServletResponse response, String brandNo,String productNo){
        logger.info("导出数据。");
        List<TblProduct> tblProducts=this.productService.queryProductList(brandNo, productNo, 1, 999999999);
        Map<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("brandName", "品名");
        headMap.put("productNo", "货号");
        headMap.put("marketPrice", "市场价格");
        headMap.put("discountPrice", "优惠价格");
        headMap.put("inventoryNum", "库存数量");
        headMap.put("freight", "运费");
        response.setContentType("octets/stream");
        response.addHeader("Content-Disposition", "attachment;filename=productExport.xlsx");
        export(response, headMap, tblProducts);
    }
    
}
