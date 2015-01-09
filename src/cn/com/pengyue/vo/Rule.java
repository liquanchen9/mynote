package cn.com.pengyue.vo;

import java.util.Date;

/**
 * Rule entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Rule implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer cateId;
	private String rule;
	private String ruleEnName;
	private String ruleInclude;
	private String enRuleInclude;
	private String ruleExclude;
	private String enRuleExclude;
	private Integer status;
	private Date addDate;

	// Constructors

	/** default constructor */
	public Rule() {
	}

	/** full constructor */
	public Rule(Integer cateId, String rule, String ruleEnName,
			String ruleInclude, String enRuleInclude, String ruleExclude,
			String enRuleExclude, Integer status, Date addDate) {
		this.cateId = cateId;
		this.rule = rule;
		this.ruleEnName = ruleEnName;
		this.ruleInclude = ruleInclude;
		this.enRuleInclude = enRuleInclude;
		this.ruleExclude = ruleExclude;
		this.enRuleExclude = enRuleExclude;
		this.status = status;
		this.addDate = addDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCateId() {
		return this.cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getRuleEnName() {
		return this.ruleEnName;
	}

	public void setRuleEnName(String ruleEnName) {
		this.ruleEnName = ruleEnName;
	}

	public String getRuleInclude() {
		return this.ruleInclude;
	}

	public void setRuleInclude(String ruleInclude) {
		this.ruleInclude = ruleInclude;
	}

	public String getEnRuleInclude() {
		return this.enRuleInclude;
	}

	public void setEnRuleInclude(String enRuleInclude) {
		this.enRuleInclude = enRuleInclude;
	}

	public String getRuleExclude() {
		return this.ruleExclude;
	}

	public void setRuleExclude(String ruleExclude) {
		this.ruleExclude = ruleExclude;
	}

	public String getEnRuleExclude() {
		return this.enRuleExclude;
	}

	public void setEnRuleExclude(String enRuleExclude) {
		this.enRuleExclude = enRuleExclude;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

}