package com.ta.platform.common.system.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
  * 树形列表
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class TreeModel<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String key;
	
	private String title;
	
	private String slotTitle;
	
	private boolean leaf;
	
	private String icon;
	
	private Integer ruleFlag;
	
	private Map<String,String> scopedSlots;
	
	private List<T> children;

	 private String parentId;
		
	private String label;
	
	private String value;
	
}
