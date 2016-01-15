/**
 * 
 */
package com.medical.manager.common;

/**
 * 枚举类
 * @author jason
 *
 * 2015-12-1 下午3:33:33
 * 
 * @version 1.0.0
 */
public class CommonEnums {
	
	public static enum ReturnKey {
		SUCCESS("success", "操作成功标识"), DATA("data", "返回数据"), MSG("msg", "返回消息");
		public String id, value;
		private ReturnKey(String id, String value) {
			this.id = id;
			this.value = value;
		}
	}

	public static enum CityProvince {
		COUNTY("0", "市级"),CITY("1", "市级"), PROVINCE("2", "省级");
		public String id, value;

		private CityProvince(String id, String value) {
			this.id = id;
			this.value = value;
		}
	}
    
	/**
	 * 账户类型（0-支付宝，1-财付通，3-微信，4银行卡）
	 */
	public static enum FundWithdrawAccType {
		ALIPAY("0", "支付宝"), TENPAY("1", "财付通"), WECHAT("3", "微信"), BANK_CARD("4", "银行卡");
		public String id, value;

		private FundWithdrawAccType(String id, String value) {
			this.id = id;
			this.value = value;
		}
	}

	/**
	 * 提现状态（00-未提现01-已提现）
	 */
	public static enum FundWithdrawStatus {
		NOT("00", "未提现"),ALREADY("01", "已提现");
		public String id, value;

		private FundWithdrawStatus(String id, String value) {
			this.id = id;
			this.value = value;
		}
	}
	
    /**
     * 系统设置参数类型
     * @author jason
     *
     * 2015-12-1 下午3:36:36
     * 
     * @version 1.0.0
     */
    public static enum SystemParamType{
        OLD_PERSON_REGISTER("a01", "老人端注册送"), 
        OLD_PERSON_INVITE("a02", "老人端邀请送"),
        RELITIVE_REGISTER("a03", "亲属端注册送"),
        RELITIVE_INVITE("a04", "亲属端邀请送"),
        AMBULANCE_REGISTER("a05", "急救车注册送"),
        AMBULANCE_INVITE("a06", "急救车邀请送"),
        REGISTER_120("a07", "120中心注册送"),
        INVITE_120("a08", "120中心邀请送"),
        OLD_BACK_CASH("a09", "普通用户被邀请人购买多少个产品给邀请人返现"),
        RELITIVE_BACK_CASH("a10", "亲属端被邀请人购买多少个产品给邀请人返现"),
        AMBULANCE_BACK_CASH("a11", "急救车被邀请人充值多少给邀请人返现"),
        A120_BACK_CASH("a12", "120中心被邀请人充值多少给邀请人返现"),
        AMBULANCE_MONTH_USE_COST("a13", "急救车月使用费用"),
        A120_PROVINCE_MONTH_USE_COST("a14", "120中心省级月使用费"),
        A120_CITY_MONTH_USE_COST("a15", "120中心市级月使用费"),
        A120_COUNTY_MONTH_USE_COST("a16", "120中心县级月使用费"),
        HOSPITAL_MONTH_USE_COST("a17", "医院使用费"),
        LOWEST_CASH("a18", "最低提现金额"),
        NEW_CUSTOMER_SEND("a19", "新用户注册送"),
        COST_DUE_NOTICE("a20", "费用到期通知"),
        OLD_MONTH_USE_COST("a21", "老人端月使用费"),
        OLD_REGISTER_SEND("a22", "老人端注册送");
        public String id, value;

        private SystemParamType(String id, String value) {
            this.id = id;
            this.value = value;
        }
        
        public static String keyOf(String id) {
            for (SystemParamType e : SystemParamType.values()) {
                if (e.id.equals(id))
                    return e.value;
            }
            return null;
        }
    }
    
    /**
     * 审核状态
     * @author jason
     *
     * 2015-12-1 下午3:58:48
     * 
     * @version 1.0.0
     */
    public static enum CheckStatus{
        AUDIT("00","待审核"),
        CHECK_PASS("01","审核通过"),
        CHECK_REJECT("02","审核拒绝");
        public String id, value;

        private CheckStatus(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }
    
    /**
     * 审核状态
     * @author jason
     *
     * 2015-12-1 下午3:58:48
     * 
     * @version 1.0.0
     */
    public static enum TaskStatus{
        AUDIT("00","待接"),
        RECEIVED("01","已接"),
        COMPLETE("02","已完成"),
        NO_COMPLETE("03","未完成");
        public String id, value;

        private TaskStatus(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }
    
    public static enum PoliceType{
        MANAGER("01","管理员"),
        SUPER_MANAGER("00","超级管理员"),
        POLICE("02","接警员");
        public String id, value;

        private PoliceType(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }
    
    public static enum AlarmStatus{
        WAITING("00","等待处理"),
        HUNTINGUP("01","报警自挂"),
        HANDLING("02","出警反馈");
        public String id, value;

        private AlarmStatus(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }
    
    /**
     * 收费级别
     * @author jason
     *
     * 2015-12-1 下午3:58:48
     * 
     * @version 1.0.0
     */
    public static enum TollLevel{
        PROVINCE("00","省级"),
        CITY("01","市级"),
        COUNTY("02","县级");
        public String id, value;

        private TollLevel(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }
    
    /**
     * 客户类型
     * @author jason
     *
     * 2015-12-1 下午3:58:48
     * 
     * @version 1.0.0
     */
    public static enum CustomType{
        OLD_PERSON("01","老人端"),
        RELITIVE("02","亲属端"),
        AMBULANCE("03","急救车"),
        A120("04","120中心");
        public String id, value;
        private static int length = 4;
        
        private CustomType(String id, String value) {
            this.id = id;
            this.value = value;
        }
        
        /**
         * @description 获取id集合
         * @return String[]
         * @author Jason
         * @since  1.0.0
         */
        public static String[] getIds() {
        	String[] values = new String[length];
        	for (int i = 0; i < length; i++) {
        		values[i] = CustomType.values()[i].id;
			}
        	return values;
        }
        /**
         * @description 根据id获取value
         * @param id
         * @return String
         * @author Jason
         * @since  1.0.0
         */
        public static String keyOf(String id) {
            for (CustomType e : CustomType.values()) {
                if (e.id.equals(id))
                    return e.value;
            }
            return null;
        }
    }
    
    /**
     * 充值类型：00-急救车；01-120中心；02-其他
     * @author jason
     *
     * 2015-12-1 下午3:58:48
     * 
     * @version 1.0.0
     */
    public static enum RechargeType{
    	AMBULANCE("00","急救车"),
    	CENTER120("01","120中心"),
    	OTHERS("02","其他");
    	public String id, value;
    	private static int length = 3;
    	
    	private RechargeType(String id, String value) {
    		this.id = id;
    		this.value = value;
    	}
    	
    	/**
    	 * @description 获取id集合
    	 * @return String[]
    	 * @author Jason
    	 * @since  1.0.0
    	 */
    	public static String[] getIds() {
    		String[] values = new String[length];
    		for (int i = 0; i < length; i++) {
    			values[i] = RechargeType.values()[i].id;
    		}
    		return values;
    	}
    	/**
    	 * @description 根据id获取value
    	 * @param id
    	 * @return String
    	 * @author Jason
    	 * @since  1.0.0
    	 */
    	public static String keyOf(String id) {
    		for (RechargeType e : RechargeType.values()) {
    			if (e.id.equals(id))
    				return e.value;
    		}
    		return null;
    	}
    }
    
    /**
     * 客户类型
     * @author jason
     *
     * 2015-12-1 下午3:58:48
     * 
     * @version 1.0.0
     */
    public static enum YesOrNo{
    	YES("Y","是"),
    	NO("N","否");
    	public String id, value;
    	
    	private YesOrNo(String id, String value) {
    		this.id = id;
    		this.value = value;
    	}
    }
    
    public static enum YesOrNop{
        YES("1","是"),
        NO("0","否");
        public String id, value;
        
        private YesOrNop(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }
    
    /**
     * 举报类型：急救车00、120省级01、120市级02、120县级03、医院04、其他机构05、普通用户（老人端和亲属端）06
     * @author jason
     *
     * 2015-12-1 下午3:58:48
     * 
     * @version 1.0.0
     */
	public static enum ReportType {
		AMBULANCE("00", "急救车"), PROVINCE120("01", "120省级"), 
		CITY120("02", "120市级"), COUNTY120("03", "120县级"),
		HOSPITAL("04", "医院"), OTHERS("05", "其他机构"), 
		COMMON("06", "普通用户（老人端和亲属端）");
		public String id, value;

		private ReportType(String id, String value) {
			this.id = id;
			this.value = value;
		}
	}
    
    /**
     * 客户类型
     * @author jason
     *
     * 2015-12-1 下午3:58:48
     * 
     * @version 1.0.0
     */
    public static enum TblUserStatus{
        VALID("00","正常"),
        DELETED("09","禁用");
        public String id, value;

        private TblUserStatus(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }
    /**
     * 充值状态
     * @author jason
     *
     * 2015-12-1 下午3:58:48
     * 
     * @version 1.0.0
     */
    public static enum RechargeStatus{
        SUCCESS("00","成功"),
        FAIL("01","失败");
        public String id, value;

        private RechargeStatus(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }
}
