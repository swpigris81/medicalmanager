/**
 * 
 */
package com.medical.manager.product.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.manager.common.dao.IBaseDao;
import com.medical.manager.common.util.DateUtil;
import com.medical.manager.product.web.dao.ITblBrandDao;
import com.medical.manager.product.web.dao.ITblProductDao;
import com.medical.manager.product.web.model.TblBrand;
import com.medical.manager.product.web.model.TblProduct;
import com.medical.manager.product.web.query.TblBrandExample;
import com.medical.manager.product.web.query.TblBrandExample.Criteria;
import com.medical.manager.product.web.service.IProductService;

/**
 * @author Jason
 *
 * 2015-12-2 上午9:37:58
 * 
 * @version 1.0.0
 */
@Transactional
@Service("productService")
@SuppressWarnings("unchecked")
public class ProductServiceImpl implements IProductService {
    
    @Resource
    private ITblBrandDao tblBrandDao;
    
    @Resource
    private ITblProductDao tblProductDao;
    
    @SuppressWarnings("rawtypes")
    @Resource
    private IBaseDao baseDao;

    @Override
    public void saveBrand(TblBrand tblBrand) {
        tblBrand.setCreateTime(DateUtil.getNowTimes());
        tblBrandDao.insert(tblBrand);
    }

    @Override
    public void updateBrand(TblBrand tblBrand) {
        tblBrand.setCreateTime(DateUtil.getNowTimes());
        tblBrandDao.updateByPrimaryKey(tblBrand);
    }

    @Override
    public void deleteBrand(Long no) {
        tblBrandDao.deleteByPrimaryKey(no);
    }

    @Override
    public List<TblBrand> queryBrandList(String brandName, Integer pageNum, Integer pageSize) {
        Map<String, String> paramMap=new HashMap<String, String>();
        paramMap.put("brandName", brandName);
        return this.baseDao.queryWithPage(paramMap, pageNum, pageSize, "queryBrandList");
    }

    @Override
    public void saveProduct(TblProduct tblProduct) {
        tblProduct.setAddTime(DateUtil.getNowTimes());
        this.tblProductDao.insert(tblProduct);
    }

    @Override
    public void updateProduct(TblProduct tblProduct) {
        tblProduct.setAddTime(DateUtil.getNowTimes());
        this.tblProductDao.updateByPrimaryKeySelective(tblProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        this.tblProductDao.deleteByPrimaryKey(productId);
    }

    @Override
    public List<TblProduct> queryProductList(String productName, String productNo, Integer pageNum, Integer pageSize) {
        Map<String, String> paramMap=new HashMap<String, String>();
        paramMap.put("brandNo", productName);
        paramMap.put("productNo", productNo);
        return this.baseDao.queryWithPage(paramMap, pageNum, pageSize, "queryProductList");
    }

    @Override
    public List<TblBrand> queryAllBrandList() {
        return this.tblBrandDao.selectByExample(null);
    }

    @Override
    public List<TblBrand> queryBrandByBrand(TblBrand tblBrand) {
        TblBrandExample example=new TblBrandExample();
        Criteria criteria=example.createCriteria();
        criteria.andBrandNameEqualTo(tblBrand.getBrandName());
        if (tblBrand.getNo()!=null) {
            criteria.andNoNotEqualTo(tblBrand.getNo());
        }
        return this.tblBrandDao.selectByExample(example);
    }

}
