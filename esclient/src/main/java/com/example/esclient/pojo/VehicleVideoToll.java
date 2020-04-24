package com.example.esclient.pojo;

public class VehicleVideoToll {
	// columns START
	private String id; // 18位设备编号+14位时间+4位序号
	private Integer isDeleted; // 删除标志, 1删除,0未删除
	private String creator; // creator
	private String modifior; // modifior
	private java.util.Date createDate; // createDate
	private java.util.Date modifyDate; // modifyDate
	private String platenumber; // 车牌号码
	private String platetype; // 车牌类型
	private java.util.Date trackdate; // 抓拍过车时间（20170101112659）
	private String trackdeviceid; // 抓拍设备编号
	private Integer brandcode; // 车辆大品牌编号
	private Integer subbrandcode; // 车辆子品牌编号
	private String vehicletype; // 车辆类型
	private Integer alychargetype; // 研判的收费类型
	private String servercode; // 图片服务器编号
	private String imgcode; // 图片编号
	private String roadsegid; // 路段编号
	private String stationid; // 站点编号
	private String stakeno; // 桩号
	private String direction; // 方向
	private Long trackid; // 过车记录id
	private Integer hwchargetype; // 高速收费类型(1,2,3,4,5类)
	private Integer vehicleseats; // 客车核载人数
	private Long hwpaymoney; // 高速收费金额
	private Integer hwpaytype; // 高速收费类型, 1MTC,2ETC
	private String hwcardcode; // 高速卡号
	private Integer hwcardtype; // 高速卡类型,1.MTC, 2.ETC
	private Long freemoney; // 免费金额
	private String obudeviceid; // obu设备编号
	private String obuplatenumber; // obu车牌号码
	private String obuplatetype; // obu车牌类型
	private Integer axlenum; // 车轴数
	private Integer truckcapacity; // 货车核载重量
	private Integer carryingcapacity; // 货车称重重量
	private Integer busortruck; // 客货标识, 1客车, 2货车
	private String firstpicpath; // 图片地址1
	private Integer abateflag; // 减免标识
	private Long payment; // 支付方式
	private java.util.Date tolldate; // 收费过车时间（20170101112659）
	private String deviceid; // 收费设备编号
	private Long tollid; // 收费记录id
	private String laneid; // 车道编号
	private Integer lanetype; // 车道类型
	private String platerec; // 车牌在图片中的位置
	private String secondpicpath; // 图片地址2
	private String carscolor; // 车身颜色
	private Integer yearcode; // 年代款编号
	private String vehiclebrandcode; // 车辆品牌完整编号
	private String secondplate; // 第二车牌号码
	private String orgid; // 高速收费流水号
	private String orgplatenumber; // 前端识别车牌
	private String orgplatetype; // 前端识别车牌类型
	private String orgcarcolor; // 前端识别车身颜色
	private String orgbrandcode; // 前端识别车辆品牌
	private String driverinfo; // 驾驶员信息
	private String passengerinfo; // 乘客信息
	private Integer credit; // 信用值
	private String additionstr; // 附加字段, 保存json格式字符串
	private String describe1; // 保留字段1
	private String describe2; // 保留字段2
	private String routingpath; // 路由路径
	private String regionId; // 区域编号
	private Integer status; // 状态
	private Integer plateconfidence; // 车牌置信度
	private Integer brandconfidence; // 品牌置信度
	private String factorydeviceid; // 厂家设备编号
	private Long factoryid; // 厂家编号
	private Integer sourcetype; // 数据来源
	private String thirdpicpath; // 第三方图片路径
	private String trackplatenumber; // 识别车牌号码
	private String trackplatetype; // 识别车牌类型
	private Integer obusingldouble; // OBU单双片标识, 1单,2双
	private Integer travelspeed; // 行驶速度
	private Integer plateOcclusion; // 是否遮挡号牌
	private String multiplate; // 多个车牌
	private Integer tollmatchvideo; // 流水是否匹配视频 0,否 1,是,精确匹配 11,模糊一位匹配 12,模糊两位匹配 13,模糊三位匹配
	private Integer tollmatchnextvideo; // 流水是否匹配下一个门架的视频, 1,是,0,否
	private String tripcode; // 行程编号,车牌号码+车牌类型+首个站点经过时间, 用md5处理
	private Long prevtollid; // 上一个流水编号,没有用0代替
	private Long nexttollid; // 下一个流水编号,没有用0代替
	private Integer oppositecharge; // 是否反向收费, 1是,0否
	private Integer missingtoll; // 是否缺失前后流水, 0,不缺失, 1缺失前流水, 2缺失后流水
	private String passid; // 通行介质ID(OBU序号编码/CPC卡 编 码 )+ 入 口 时 间 （YYYYMMDDHHmmss）
	private Integer completetrip; // 是否完整行程, 1,是,0,否,9,未知
	private Integer videomatchtoll; // 视频是否匹配流水, 0,否 1,是,精确匹配 11,模糊一位匹配 12,模糊两位匹配 13,模糊三位匹配
	private Integer mergeddata; // 是否经过数据校验后合并的数据, 1.是, 0.否
	private Integer vehicleclass; // 车种
	private Integer traderesult; // 交易结果
	private Integer matchstatus; // 匹配状态
	private String transtype; // 交易类型标识
	private String specialtype; // 特情类型
	private Integer validstatus; // 去重状态
	private Integer checkeddata; //是否校验数据, 0.否, 1.是
	private Integer recordtype;  //记录类型, 1.流水,2.视频,3.流水视频合并
	private Integer repeattoll; //流水是否重复, 0.否, 1.是
	private String prestationid; //上一个站点编号
	private String nextstationid; //下一个站点编号
	private String audit; //审核状态,0.未审核,1.已审核


	// columns END

	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public void setIsDeleted(Integer value) {
		this.isDeleted = value;
	}

	public Integer getIsDeleted() {
		return this.isDeleted;
	}

	public void setCreator(String value) {
		this.creator = value;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setModifior(String value) {
		this.modifior = value;
	}

	public String getModifior() {
		return this.modifior;
	}

	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}

	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setModifyDate(java.util.Date value) {
		this.modifyDate = value;
	}

	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

	public void setPlatenumber(String value) {
		this.platenumber = value;
	}

	public String getPlatenumber() {
		return this.platenumber;
	}

	public void setPlatetype(String value) {
		this.platetype = value;
	}

	public String getPlatetype() {
		return this.platetype;
	}

	public void setTrackdate(java.util.Date value) {
		this.trackdate = value;
	}

	public java.util.Date getTrackdate() {
		return this.trackdate;
	}

	public void setTrackdeviceid(String value) {
		this.trackdeviceid = value;
	}

	public String getTrackdeviceid() {
		return this.trackdeviceid;
	}

	public void setBrandcode(Integer value) {
		this.brandcode = value;
	}

	public Integer getBrandcode() {
		return this.brandcode;
	}

	public void setSubbrandcode(Integer value) {
		this.subbrandcode = value;
	}

	public Integer getSubbrandcode() {
		return this.subbrandcode;
	}

	public void setVehicletype(String value) {
		this.vehicletype = value;
	}

	public String getVehicletype() {
		return this.vehicletype;
	}

	public void setAlychargetype(Integer value) {
		this.alychargetype = value;
	}

	public Integer getAlychargetype() {
		return this.alychargetype;
	}

	public void setServercode(String value) {
		this.servercode = value;
	}

	public String getServercode() {
		return this.servercode;
	}

	public void setImgcode(String value) {
		this.imgcode = value;
	}

	public String getImgcode() {
		return this.imgcode;
	}

	public void setRoadsegid(String value) {
		this.roadsegid = value;
	}

	public String getRoadsegid() {
		return this.roadsegid;
	}

	public void setStationid(String value) {
		this.stationid = value;
	}

	public String getStationid() {
		return this.stationid;
	}

	public void setStakeno(String value) {
		this.stakeno = value;
	}

	public String getStakeno() {
		return this.stakeno;
	}

	public void setDirection(String value) {
		this.direction = value;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setTrackid(Long value) {
		this.trackid = value;
	}

	public Long getTrackid() {
		return this.trackid;
	}

	public void setHwchargetype(Integer value) {
		this.hwchargetype = value;
	}

	public Integer getHwchargetype() {
		return this.hwchargetype;
	}

	public void setVehicleseats(Integer value) {
		this.vehicleseats = value;
	}

	public Integer getVehicleseats() {
		return this.vehicleseats;
	}

	public void setHwpaymoney(Long value) {
		this.hwpaymoney = value;
	}

	public Long getHwpaymoney() {
		return this.hwpaymoney;
	}

	public void setHwpaytype(Integer value) {
		this.hwpaytype = value;
	}

	public Integer getHwpaytype() {
		return this.hwpaytype;
	}

	public void setHwcardcode(String value) {
		this.hwcardcode = value;
	}

	public String getHwcardcode() {
		return this.hwcardcode;
	}

	public void setHwcardtype(Integer value) {
		this.hwcardtype = value;
	}

	public Integer getHwcardtype() {
		return this.hwcardtype;
	}

	public void setFreemoney(Long value) {
		this.freemoney = value;
	}

	public Long getFreemoney() {
		return this.freemoney;
	}

	public void setObudeviceid(String value) {
		this.obudeviceid = value;
	}

	public String getObudeviceid() {
		return this.obudeviceid;
	}

	public void setObuplatenumber(String value) {
		this.obuplatenumber = value;
	}

	public String getObuplatenumber() {
		return this.obuplatenumber;
	}

	public void setObuplatetype(String value) {
		this.obuplatetype = value;
	}

	public String getObuplatetype() {
		return this.obuplatetype;
	}

	public void setAxlenum(Integer value) {
		this.axlenum = value;
	}

	public Integer getAxlenum() {
		return this.axlenum;
	}

	public void setTruckcapacity(Integer value) {
		this.truckcapacity = value;
	}

	public Integer getTruckcapacity() {
		return this.truckcapacity;
	}

	public void setCarryingcapacity(Integer value) {
		this.carryingcapacity = value;
	}

	public Integer getCarryingcapacity() {
		return this.carryingcapacity;
	}

	public void setBusortruck(Integer value) {
		this.busortruck = value;
	}

	public Integer getBusortruck() {
		return this.busortruck;
	}

	public void setFirstpicpath(String value) {
		this.firstpicpath = value;
	}

	public String getFirstpicpath() {
		return this.firstpicpath;
	}

	public void setAbateflag(Integer value) {
		this.abateflag = value;
	}

	public Integer getAbateflag() {
		return this.abateflag;
	}

	public void setPayment(Long value) {
		this.payment = value;
	}

	public Long getPayment() {
		return this.payment;
	}

	public void setTolldate(java.util.Date value) {
		this.tolldate = value;
	}

	public java.util.Date getTolldate() {
		return this.tolldate;
	}

	public void setDeviceid(String value) {
		this.deviceid = value;
	}

	public String getDeviceid() {
		return this.deviceid;
	}

	public void setTollid(Long value) {
		this.tollid = value;
	}

	public Long getTollid() {
		return this.tollid;
	}

	public void setLaneid(String value) {
		this.laneid = value;
	}

	public String getLaneid() {
		return this.laneid;
	}

	public void setLanetype(Integer value) {
		this.lanetype = value;
	}

	public Integer getLanetype() {
		return this.lanetype;
	}

	public void setPlaterec(String value) {
		this.platerec = value;
	}

	public String getPlaterec() {
		return this.platerec;
	}

	public void setSecondpicpath(String value) {
		this.secondpicpath = value;
	}

	public String getSecondpicpath() {
		return this.secondpicpath;
	}

	public void setCarscolor(String value) {
		this.carscolor = value;
	}

	public String getCarscolor() {
		return this.carscolor;
	}

	public void setYearcode(Integer value) {
		this.yearcode = value;
	}

	public Integer getYearcode() {
		return this.yearcode;
	}

	public void setVehiclebrandcode(String value) {
		this.vehiclebrandcode = value;
	}

	public String getVehiclebrandcode() {
		return this.vehiclebrandcode;
	}

	public void setSecondplate(String value) {
		this.secondplate = value;
	}

	public String getSecondplate() {
		return this.secondplate;
	}

	public void setOrgid(String value) {
		this.orgid = value;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgplatenumber(String value) {
		this.orgplatenumber = value;
	}

	public String getOrgplatenumber() {
		return this.orgplatenumber;
	}

	public void setOrgplatetype(String value) {
		this.orgplatetype = value;
	}

	public String getOrgplatetype() {
		return this.orgplatetype;
	}

	public void setOrgcarcolor(String value) {
		this.orgcarcolor = value;
	}

	public String getOrgcarcolor() {
		return this.orgcarcolor;
	}

	public void setOrgbrandcode(String value) {
		this.orgbrandcode = value;
	}

	public String getOrgbrandcode() {
		return this.orgbrandcode;
	}

	public void setDriverinfo(String value) {
		this.driverinfo = value;
	}

	public String getDriverinfo() {
		return this.driverinfo;
	}

	public void setPassengerinfo(String value) {
		this.passengerinfo = value;
	}

	public String getPassengerinfo() {
		return this.passengerinfo;
	}

	public void setCredit(Integer value) {
		this.credit = value;
	}

	public Integer getCredit() {
		return this.credit;
	}

	public void setAdditionstr(String value) {
		this.additionstr = value;
	}

	public String getAdditionstr() {
		return this.additionstr;
	}

	public void setDescribe1(String value) {
		this.describe1 = value;
	}

	public String getDescribe1() {
		return this.describe1;
	}

	public void setDescribe2(String value) {
		this.describe2 = value;
	}

	public String getDescribe2() {
		return this.describe2;
	}

	public void setRoutingpath(String value) {
		this.routingpath = value;
	}

	public String getRoutingpath() {
		return this.routingpath;
	}

	public void setRegionId(String value) {
		this.regionId = value;
	}

	public String getRegionId() {
		return this.regionId;
	}

	public void setStatus(Integer value) {
		this.status = value;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setPlateconfidence(Integer value) {
		this.plateconfidence = value;
	}

	public Integer getPlateconfidence() {
		return this.plateconfidence;
	}

	public void setBrandconfidence(Integer value) {
		this.brandconfidence = value;
	}

	public Integer getBrandconfidence() {
		return this.brandconfidence;
	}

	public void setFactorydeviceid(String value) {
		this.factorydeviceid = value;
	}

	public String getFactorydeviceid() {
		return this.factorydeviceid;
	}

	public void setFactoryid(Long value) {
		this.factoryid = value;
	}

	public Long getFactoryid() {
		return this.factoryid;
	}

	public void setSourcetype(Integer value) {
		this.sourcetype = value;
	}

	public Integer getSourcetype() {
		return this.sourcetype;
	}

	public void setThirdpicpath(String value) {
		this.thirdpicpath = value;
	}

	public String getThirdpicpath() {
		return this.thirdpicpath;
	}

	public void setTrackplatenumber(String value) {
		this.trackplatenumber = value;
	}

	public String getTrackplatenumber() {
		return this.trackplatenumber;
	}

	public void setTrackplatetype(String value) {
		this.trackplatetype = value;
	}

	public String getTrackplatetype() {
		return this.trackplatetype;
	}

	public void setObusingldouble(Integer value) {
		this.obusingldouble = value;
	}

	public Integer getObusingldouble() {
		return this.obusingldouble;
	}

	public void setTravelspeed(Integer value) {
		this.travelspeed = value;
	}

	public Integer getTravelspeed() {
		return this.travelspeed;
	}

	public void setPlateOcclusion(Integer value) {
		this.plateOcclusion = value;
	}

	public Integer getPlateOcclusion() {
		return this.plateOcclusion;
	}

	public void setMultiplate(String value) {
		this.multiplate = value;
	}

	public String getMultiplate() {
		return this.multiplate;
	}

	public void setTollmatchvideo(Integer value) {
		this.tollmatchvideo = value;
	}

	public Integer getTollmatchvideo() {
		return this.tollmatchvideo;
	}

	public void setTollmatchnextvideo(Integer value) {
		this.tollmatchnextvideo = value;
	}

	public Integer getTollmatchnextvideo() {
		return this.tollmatchnextvideo;
	}

	public void setTripcode(String value) {
		this.tripcode = value;
	}

	public String getTripcode() {
		return this.tripcode;
	}

	public void setPrevtollid(Long value) {
		this.prevtollid = value;
	}

	public Long getPrevtollid() {
		return this.prevtollid;
	}

	public void setNexttollid(Long value) {
		this.nexttollid = value;
	}

	public Long getNexttollid() {
		return this.nexttollid;
	}

	public void setOppositecharge(Integer value) {
		this.oppositecharge = value;
	}

	public Integer getOppositecharge() {
		return this.oppositecharge;
	}

	public void setMissingtoll(Integer value) {
		this.missingtoll = value;
	}

	public Integer getMissingtoll() {
		return this.missingtoll;
	}

	public void setPassid(String value) {
		this.passid = value;
	}

	public String getPassid() {
		return this.passid;
	}

	public void setCompletetrip(Integer value) {
		this.completetrip = value;
	}

	public Integer getCompletetrip() {
		return this.completetrip;
	}

	public void setVideomatchtoll(Integer value) {
		this.videomatchtoll = value;
	}

	public Integer getVideomatchtoll() {
		return this.videomatchtoll;
	}

	public void setMergeddata(Integer value) {
		this.mergeddata = value;
	}

	public Integer getMergeddata() {
		return this.mergeddata;
	}

	public void setVehicleclass(Integer value) {
		this.vehicleclass = value;
	}

	public Integer getVehicleclass() {
		return this.vehicleclass;
	}

	public void setTraderesult(Integer value) {
		this.traderesult = value;
	}

	public Integer getTraderesult() {
		return this.traderesult;
	}

	public void setMatchstatus(Integer value) {
		this.matchstatus = value;
	}

	public Integer getMatchstatus() {
		return this.matchstatus;
	}

	public void setTranstype(String value) {
		this.transtype = value;
	}

	public String getTranstype() {
		return this.transtype;
	}

	public void setSpecialtype(String value) {
		this.specialtype = value;
	}

	public String getSpecialtype() {
		return this.specialtype;
	}

	public void setValidstatus(Integer value) {
		this.validstatus = value;
	}

	public Integer getValidstatus() {
		return this.validstatus;
	}

	public Integer getCheckeddata() {
		return checkeddata;
	}

	public void setCheckeddata(Integer checkeddata) {
		this.checkeddata = checkeddata;
	}

	public Integer getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(Integer recordtype) {
		this.recordtype = recordtype;
	}

	public Integer getRepeattoll() {
		return repeattoll;
	}

	public void setRepeattoll(Integer repeattoll) {
		this.repeattoll = repeattoll;
	}

	public String getPrestationid() {
		return prestationid;
	}

	public void setPrestationid(String prestationid) {
		this.prestationid = prestationid;
	}

	public String getNextstationid() {
		return nextstationid;
	}

	public void setNextstationid(String nextstationid) {
		this.nextstationid = nextstationid;
	}

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}
}
