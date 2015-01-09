package cn.com.pengyue.vo;

/**
 * SearchengineConfig entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SearchengineConfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private String engineName;
	private Integer useProxy;
	private String proxyIp;
	private Integer proxyPort;
	private Integer onceWaitTime;
	private Integer status;
	private String encode;
	private Integer oneDayMax;
	private Integer hotPageNum;

	// Constructors

	/** default constructor */
	public SearchengineConfig() {
	}

	/** minimal constructor */
	public SearchengineConfig(String engineName, Integer onceWaitTime,
			Integer status, String encode) {
		this.engineName = engineName;
		this.onceWaitTime = onceWaitTime;
		this.status = status;
		this.encode = encode;
	}

	/** full constructor */
	public SearchengineConfig(String engineName, Integer useProxy,
			String proxyIp, Integer proxyPort, Integer onceWaitTime,
			Integer status, String encode, Integer oneDayMax, Integer hotPageNum) {
		this.engineName = engineName;
		this.useProxy = useProxy;
		this.proxyIp = proxyIp;
		this.proxyPort = proxyPort;
		this.onceWaitTime = onceWaitTime;
		this.status = status;
		this.encode = encode;
		this.oneDayMax = oneDayMax;
		this.hotPageNum = hotPageNum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEngineName() {
		return this.engineName;
	}

	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	public Integer getUseProxy() {
		return this.useProxy;
	}

	public void setUseProxy(Integer useProxy) {
		this.useProxy = useProxy;
	}

	public String getProxyIp() {
		return this.proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public Integer getProxyPort() {
		return this.proxyPort;
	}

	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}

	public Integer getOnceWaitTime() {
		return this.onceWaitTime;
	}

	public void setOnceWaitTime(Integer onceWaitTime) {
		this.onceWaitTime = onceWaitTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEncode() {
		return this.encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public Integer getOneDayMax() {
		return this.oneDayMax;
	}

	public void setOneDayMax(Integer oneDayMax) {
		this.oneDayMax = oneDayMax;
	}

	public Integer getHotPageNum() {
		return this.hotPageNum;
	}

	public void setHotPageNum(Integer hotPageNum) {
		this.hotPageNum = hotPageNum;
	}

}