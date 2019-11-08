package com.alvis.exam.configuration.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


/**
 * @author alvis
 */
@ConfigurationProperties(prefix = "system")
@Data
public class SystemConfig {
    private PasswordKeyConfig pwdKey;
    private List<String> securityIgnoreUrls;
    private WxConfig wx;
    private QnConfig qn;
    private UrlConfig url;

	public UrlConfig getUrl() {
		return url;
	}

	public void setUrl(UrlConfig url) {
		this.url = url;
	}

	public PasswordKeyConfig getPwdKey() {
		return pwdKey;
	}
	public void setPwdKey(PasswordKeyConfig pwdKey) {
		this.pwdKey = pwdKey;
	}
	public List<String> getSecurityIgnoreUrls() {
		return securityIgnoreUrls;
	}
	public void setSecurityIgnoreUrls(List<String> securityIgnoreUrls) {
		this.securityIgnoreUrls = securityIgnoreUrls;
	}
	public WxConfig getWx() {
		return wx;
	}
	public void setWx(WxConfig wx) {
		this.wx = wx;
	}
	public QnConfig getQn() {
		return qn;
	}
	public void setQn(QnConfig qn) {
		this.qn = qn;
	}
}
