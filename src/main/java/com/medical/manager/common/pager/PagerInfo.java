/**
 * 系统项目名称
 * com.supermarket.common
 * PagerInfo.java
 * 
 * 2015年7月7日-下午2:50:18
 * 2015版权所有
 * 
 */

package com.medical.manager.common.pager;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.github.pagehelper.Page;

/**
 * @description 解决自带Page不带分页数据的BUG
 * 
 * @time 2015年7月7日 下午2:50:18
 * @author Jason
 * @version 1.0.0
 * 
 */

public class PagerInfo implements Serializable {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = 1L;
    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 起始行
     */
    private int startRow;
    /**
     * 末行
     */
    private int endRow;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 分页合理化
     */
    private Boolean reasonable;
    /**
     * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
     */
    private Boolean pageSizeZero;

    private List rows;
    
    private List footer;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Boolean getReasonable() {
        return reasonable;
    }

    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
    }

    public Boolean getPageSizeZero() {
        return pageSizeZero;
    }

    public void setPageSizeZero(Boolean pageSizeZero) {
        this.pageSizeZero = pageSizeZero;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public List getFooter() {
        return footer;
    }

    public void setFooter(List footer) {
        this.footer = footer;
    }

    public static PagerInfo converFromPage(Page page) {
        PagerInfo pageInfo = new PagerInfo();
        try {
            PropertyUtils.copyProperties(pageInfo, page);
        } catch (Exception e) {

        }
        pageInfo.setRows(page);
        return pageInfo;
    }
}
