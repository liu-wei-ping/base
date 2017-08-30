package com.base.core.model;

import javax.persistence.Column;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Table(name = "car_info")
public class CarInfo extends BaseVo {
	/*** */
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌编码
	 */
    @Column(name = "brand_code")
	@ApiModelProperty(hidden = false, value = "品牌编码")
    private String brandCode;

    /**
     * 品牌名称
     */
    @Column(name = "brand_name")
	@ApiModelProperty(hidden = false, value = "品牌名称")
    private String brandName;

    /**
     * 主车型编码
     */
    @Column(name = "car_code")
	@ApiModelProperty(hidden = false, value = "主车型编码")
    private String carCode;

    /**
     * 主车型名称
     */
    @Column(name = "car_name")
	@ApiModelProperty(hidden = false, value = "主车型名称")
    private String carName;

    /**
     * 厂家编码
     */
    @Column(name = "factory_code")
	@ApiModelProperty(hidden = false, value = " 厂家编码")
    private String factoryCode;

    /**
     * 厂家名称
     */
    @Column(name = "factory_name")
	@ApiModelProperty(hidden = false, value = "厂家名称")
    private String factoryName;

    /**
     * 车型编码
     */
    @Column(name = "model_code")
	@ApiModelProperty(hidden = false, value = "车型编码")
    private String modelCode;

    /**
     * 车型名称
     */
    @Column(name = "model_name")
	@ApiModelProperty(hidden = false, value = "车型名称")
    private String modelName;

    /**
     * 排量编码
     */
    @Column(name = "opv_code")
	@ApiModelProperty(hidden = false, value = "排量编码")
    private String opvCode;

    /**
     * 排量名称
     */
    @Column(name = "opv_name")
	@ApiModelProperty(hidden = false, value = "排量名称")
    private String opvName;

    /**
     * 车系编码
     */
    @Column(name = "series_code")
	@ApiModelProperty(hidden = false, value = "车系编码")
    private String seriesCode;

    /**
     * 车系名称
     */
    @Column(name = "series_name")
	@ApiModelProperty(hidden = false, value = "车系名称")
    private String seriesName;

    /**
     * 获取品牌编码
     *
     * @return brand_code - 品牌编码
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * 获取品牌名称
     *
     * @return brand_name - 品牌名称
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 获取主车型编码
     *
     * @return car_code - 主车型编码
     */
    public String getCarCode() {
        return carCode;
    }

    /**
     * 获取主车型名称
     *
     * @return car_name - 主车型名称
     */
    public String getCarName() {
        return carName;
    }

    /**
     * 获取厂家编码
     *
     * @return factory_code - 厂家编码
     */
    public String getFactoryCode() {
        return factoryCode;
    }

    /**
     * 获取厂家名称
     *
     * @return factory_name - 厂家名称
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * 获取车型编码
     *
     * @return model_code - 车型编码
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * 获取车型名称
     *
     * @return model_name - 车型名称
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * 获取排量编码
     *
     * @return opv_code - 排量编码
     */
    public String getOpvCode() {
        return opvCode;
    }

    /**
     * 获取排量名称
     *
     * @return opv_name - 排量名称
     */
    public String getOpvName() {
        return opvName;
    }

    /**
     * 获取车系编码
     *
     * @return series_code - 车系编码
     */
    public String getSeriesCode() {
        return seriesCode;
    }

    /**
     * 获取车系名称
     *
     * @return series_name - 车系名称
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * 设置品牌编码
     *
     * @param brandCode 品牌编码
     */
    public void setBrandCode(final String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    /**
     * 设置品牌名称
     *
     * @param brandName 品牌名称
     */
    public void setBrandName(final String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * 设置主车型编码
     *
     * @param carCode 主车型编码
     */
    public void setCarCode(final String carCode) {
        this.carCode = carCode == null ? null : carCode.trim();
    }

    /**
     * 设置主车型名称
     *
     * @param carName 主车型名称
     */
    public void setCarName(final String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    /**
     * 设置厂家编码
     *
     * @param factoryCode 厂家编码
     */
    public void setFactoryCode(final String factoryCode) {
        this.factoryCode = factoryCode == null ? null : factoryCode.trim();
    }

    /**
     * 设置厂家名称
     *
     * @param factoryName 厂家名称
     */
    public void setFactoryName(final String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    /**
     * 设置车型编码
     *
     * @param modelCode 车型编码
     */
    public void setModelCode(final String modelCode) {
        this.modelCode = modelCode == null ? null : modelCode.trim();
    }

    /**
     * 设置车型名称
     *
     * @param modelName 车型名称
     */
    public void setModelName(final String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    /**
     * 设置排量编码
     *
     * @param opvCode 排量编码
     */
    public void setOpvCode(final String opvCode) {
        this.opvCode = opvCode == null ? null : opvCode.trim();
    }

    /**
     * 设置排量名称
     *
     * @param opvName 排量名称
     */
    public void setOpvName(final String opvName) {
        this.opvName = opvName == null ? null : opvName.trim();
    }

    /**
     * 设置车系编码
     *
     * @param seriesCode 车系编码
     */
    public void setSeriesCode(final String seriesCode) {
        this.seriesCode = seriesCode == null ? null : seriesCode.trim();
    }

    /**
     * 设置车系名称
     *
     * @param seriesName 车系名称
     */
    public void setSeriesName(final String seriesName) {
        this.seriesName = seriesName == null ? null : seriesName.trim();
    }
}