package com.ta.platform.common.system.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
  * 树形下拉框
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TreeSelectModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String key;
	
	private String title;

	private String label;

	private String slotTitle;

	private boolean leaf;
	
	private String icon;

	private Integer ruleFlag;
	
	private String parentId;
	
	private String value;
	
	private String code;

	private Map<String,String> scopedSlots;
	
	private List<TreeSelectModel> children;


}
