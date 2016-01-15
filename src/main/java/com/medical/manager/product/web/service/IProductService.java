/**
 * 
 */
package com.medical.manager.product.web.service;

import java.util.List;

import com.medical.manager.product.web.model.TblBrand;
import com.medical.manager.product.web.model.TblProduct;

/**
 * @author Jason
 *
 * 2015-12-2 上午9:35:52
 * 
 * @version 1.0.0
 */
public interface IProductService {

    /**
     * 保存品牌信息
     * (这里描述这个方法适用条件 – 可选)
     * @param tblBrand 品牌pojo
     * @exception
     * @since 1.0.0
     */
    void saveBrand(TblBrand tblBrand);
    
    /**
     * 更新品牌信息
     * (这里描述这个方法适用条件 – 可选)
     * @param tblBrand 品牌pojo
     * @exception
     * @since 1.0.0
     */
    void updateBrand(TblBrand tblBrand);

    /**
     * 删除品牌
     * (这里描述这个方法适用条件 – 可选)
     * @param no 品牌编号
     * @exception
     * @since 1.0.0
     */
    void deleteBrand(Long no);

    /**
     * 查询品牌
     * (这里描述这个方法适用条件 – 可选)
     * @param response
     * @param brandName 品牌名称
     * @param pageNum 当前页数
     * @param pageSize 显示个数
     * @exception
     * @since 1.0.0
     */
    List<TblBrand> queryBrandList(String brandName, Integer pageNum, Integer pageSize);

    /**
     * 新增商品
     * (这里描述这个方法适用条件 – 可选)
     * @param tblProduct
     * @exception
     * @since 1.0.0
     */
    void saveProduct(TblProduct tblProduct);

    /**
     * 修改商品
     * (这里描述这个方法适用条件 – 可选)
     * @param tblProduct
     * @exception
     * @since 1.0.0
     */
    void updateProduct(TblProduct tblProduct);

    /**
     * 删除商品失败
     * (这里描述这个方法适用条件 – 可选)
     * @param productId
     * @exception
     * @since 1.0.0
     */
    void deleteProduct(Long productId);

    /**
     * 查询商品信息
     * (这里描述这个方法适用条件 – 可选)
     * @param productName
     * @param productNo
     * @param pageNum
     * @param pageSize
     * @return
     * @exception
     * @since 1.0.0 
     */
    List<TblProduct> queryProductList(String productName, String productNo, Integer pageNum, Integer pageSize);

    /**
     * 查询所有品牌
     * (这里描述这个方法适用条件 – 可选)
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblBrand> queryAllBrandList();

    /**
     * 通过条件查询品牌
     * (这里描述这个方法适用条件 – 可选)
     * @param tblBrand
     * @return
     * @exception
     * @since 1.0.0
     */
    List<TblBrand> queryBrandByBrand(TblBrand tblBrand);

}
