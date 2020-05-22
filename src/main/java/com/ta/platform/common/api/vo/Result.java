package com.ta.platform.common.api.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.ey.tax.toolset.core.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ta.platform.common.api.ApiCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *   接口返回数据格式
 */
@Data
@Builder
@AllArgsConstructor
@ApiModel(value="接口返回对象", description="接口返回对象")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	@ApiModelProperty(value = "成功标志")
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	@ApiModelProperty(value = "返回处理消息")
	private String message = "OK!";

	/**
	 * 响应代码
	 */
	@ApiModelProperty(value = "响应代码", example = "200: ok; 500：error")
	private Integer code = 0;
	
	/**
	 * 返回数据对象 data
	 */
	@ApiModelProperty(value = "返回数据对象")
	private T data;
	
	/**
	 * 响应时间
	 */
	@ApiModelProperty(value = "响应时间")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;

	public Result() {
		time  = new Date();
	}

	public static Result<Boolean>result(boolean flag){
		if(flag){
			return ok();
		}
		return error();
	}

	public static Result<Boolean> ok(){
		return ok(true);
	}

	public static Result<Boolean> ok(String message){
		return result(ApiCode.SUCCESS, message, true);
	}

	public static <T> Result<T> ok(T data){
		return result(ApiCode.SUCCESS,data);
	}

	public static <T> Result<T> ok(T data,String message){
		return result(ApiCode.SUCCESS,message,data);
	}

	public static Result<Boolean> error(){
		return error(false);
	}

	public static <T> Result<T> error(T data){
		return result(ApiCode.FAIL,data);
	}

	public static Result<Boolean> error(String message){
		return result(ApiCode.FAIL,message,false);
	}

	public static Result<Boolean> error(ApiCode apiCode){
		return result(apiCode,null);
	}

	public static <T> Result<T> error(ApiCode apiCode, T data){
		if (ApiCode.SUCCESS == apiCode){
			throw new RuntimeException("失败结果状态码不能为" + ApiCode.SUCCESS.getCode());
		}
		return result(apiCode,data);
	}

	public static Result<Object> error(int code, String msg) {
		return Result.builder()
				.code(code)
				.message(msg)
				.success(false)
				.build();
	}

	public static Result<Map<String,Object>> error(String key, Object value){
		Map<String,Object> map = new HashMap<>(1);
		map.put(key,value);
		return result(ApiCode.FAIL,map);
	}

	public static <T> Result<T> result(ApiCode apiCode,T data){
		return result(apiCode,null,data);
	}

	public static <T> Result<T> result(ApiCode apiCode,String message,T data){
		boolean success = false;
		if (apiCode.getCode() == ApiCode.SUCCESS.getCode()){
			success = true;
		}
		String apiMessage = apiCode.getMessage();
		if (StrUtil.isBlank(message)){
			message = apiMessage;
		}
		return (Result<T>) Result.builder()
				.code(apiCode.getCode())
				.message(message)
				.data(data)
				.success(success)
				.time(new Date())
				.build();
	}
}