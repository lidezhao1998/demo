package com.ruoyi.web.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *功能描述  静态方法引用yml
 * @author sunlei
 * @date 2020/7/22
 * @param
 * @return
 */

@Component
@ConfigurationProperties(prefix = "datagaea.geoserver")
public class GeoServerProperties {
//    @Value("$(datagaea.geoserver.urlYml)")
    private String urlYml;
//    @Value("$(datagaea.geoserver.usernameYml)")
    private String usernameYml;
//    @Value("$(datagaea.geoserver.passwordYml)")
    private String passwordYml;
//    @Value("$(datagaea.geoserver.workspaceYml)")
    private String workspaceYml;
//    @Value("$(datagaea.geoserver.srsYml)")
    private String srsYml;

    public String getUrlYml() {
        return urlYml;
    }

    public void setUrlYml(String urlYml) {
        this.urlYml = urlYml;
    }

    public String getUsernameYml() {
        return usernameYml;
    }

    public void setUsernameYml(String usernameYml) {
        this.usernameYml = usernameYml;
    }

    public String getPasswordYml() {
        return passwordYml;
    }

    public void setPasswordYml(String passwordYml) {
        this.passwordYml = passwordYml;
    }

    public String getWorkspaceYml() {
        return workspaceYml;
    }

    public void setWorkspaceYml(String workspaceYml) {
        this.workspaceYml = workspaceYml;
    }

    public String getSrsYml() {
        return srsYml;
    }

    public void setSrsYml(String srsYml) {
        this.srsYml = srsYml;
    }
    /* private static String url;

    private static String username;

    private static String password;

    private static String workspace;

    private static String srs;*/

  /*  @PostConstruct
    public void setUrlyml() {
        url = urlYml;
    }

    @PostConstruct
    public void setUsernameYml() {
        username = usernameYml;
    }

    @PostConstruct
    public void setPasswordYml() {
        password = passwordYml;
    }

    @PostConstruct
    public void setWorkspaceYml() {
        workspace = workspaceYml;
    }

    @PostConstruct
    public void setSrsYml() {
        srs = srsYml;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getWorkspace() {
        return workspace;
    }

    public static String getSrs() {
        return srs;
    }*/
}
