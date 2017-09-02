package com.base.core.model;

import java.io.Serializable;

import com.base.core.constant.SysConstant;

import io.swagger.annotations.ApiModelProperty;
/**
 * rest 接口统一返回对象
 *
 * @author chongwenjun
 *
 */
public class RestReturnObject implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 错误返回
	 *
	 * @param msg
	 * @param obj
	 * @return
	 */
	public static RestReturnObject generateFailedObject(String msg, Object obj) {
		RestReturnObject returnObject = new RestReturnObject();
		returnObject.setCode(SysConstant.REST_RETURN_CODE_FAIL);
		returnObject.setMessage(msg);
		returnObject.setObj(obj);
		return returnObject;
	}
	/**
	 * 成功返回
	 *
	 * @param obj
	 * @return
	 */
	public static RestReturnObject generateSuccessObject(Object obj) {
		RestReturnObject returnObject = new RestReturnObject();
		returnObject.setCode(SysConstant.REST_RETURN_SUCCESS_CODE);
		returnObject.setMessage("success");
		returnObject.setObj(obj);
		return returnObject;
	}
	public static RestReturnObject getReturnMsg(String code, String message) {
		RestReturnObject returnObject = new RestReturnObject();
		returnObject.setCode(code);
		returnObject.setMessage(message);
		return returnObject;
	}

	public static RestReturnObject respSuccess() {
		RestReturnObject returnObject = new RestReturnObject();
		returnObject.setCode(SysConstant.REST_RETURN_SUCCESS_CODE);
		returnObject.setMessage("success");
		return returnObject;
	}

	// 返回状态
	@ApiModelProperty(value = "返回状态")
	private String code;

	@ApiModelProperty(value = "返回入参对象")
	private Object conditions;

	// 返回提示信息
	@ApiModelProperty(value = "返回提示信息")
	private String message;

	// 返回结果对象
	@ApiModelProperty(value = "返回结果对象")
	private Object obj;

	public RestReturnObject() {
		this.code = SysConstant.REST_RETURN_CODE_FAIL;
		this.message = SysConstant.REST_RETURN_FAIL_MESSAGE;
	}

	public RestReturnObject(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public Object getConditions() {
		return conditions;
	}

	public String getMessage() {
		return message;
	}

	public Object getObj() {
		return obj;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setConditions(Object conditions) {
		this.conditions = conditions;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public RestReturnObject withConditions(String conditions){
		this.conditions = conditions;
		return this;
	}

}
