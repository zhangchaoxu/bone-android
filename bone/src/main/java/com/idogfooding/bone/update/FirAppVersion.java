package com.idogfooding.bone.update;

import android.os.Environment;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Charles on 16/08/29.
 */
public class FirAppVersion implements Serializable {

    /**
     * name : 渔友记
     * version : 31
     * changelog :
     * updated_at : 1472431581
     * versionShort : 0.3.1
     * build : 31
     * installUrl : http://download.fir.im/v2/app/install/577da0fbf2fc424cb600001a?download_token=8c6a38c8a2a08f5710ed125d409264aa
     * install_url : http://download.fir.im/v2/app/install/577da0fbf2fc424cb600001a?download_token=8c6a38c8a2a08f5710ed125d409264aa
     * direct_install_url : http://download.fir.im/v2/app/install/577da0fbf2fc424cb600001a?download_token=8c6a38c8a2a08f5710ed125d409264aa
     * update_url : http://fir.im/fishing
     * binary : {"fsize":13963036}
     */
    private String name;
    private int version;
    private String changelog;
    private int updated_at;
    private String versionShort;
    private String build;
    private String install_url;
    private String direct_install_url;
    private String update_url;
    /**
     * fsize : 13963036
     */
    private BinaryBean binary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getInstall_url() {
        return install_url;
    }

    public void setInstall_url(String install_url) {
        this.install_url = install_url;
    }

    public String getDirect_install_url() {
        return direct_install_url;
    }

    public void setDirect_install_url(String direct_install_url) {
        this.direct_install_url = direct_install_url;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public BinaryBean getBinary() {
        return binary;
    }

    public void setBinary(BinaryBean binary) {
        this.binary = binary;
    }

    public static class BinaryBean {
        private int fsize;

        public int getFsize() {
            return fsize;
        }

        public void setFsize(int fsize) {
            this.fsize = fsize;
        }
    }

    public static String makeFileName(int version) {
        return "bone-" + version + ".apk";
    }

    public String apkName() {
        return makeFileName(version);
    }

    public File apkFile() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), apkName());
    }

}
