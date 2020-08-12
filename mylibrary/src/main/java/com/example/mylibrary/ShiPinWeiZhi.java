package com.example.mylibrary;

public class ShiPinWeiZhi {
    public Integer WeiZhiXuanZhe(String ChooseWeiZhi){
        Integer weizhi = null;
        switch (ChooseWeiZhi){
            case "java环境变量win10":
                weizhi = 0;
                break;
            case "eclipse安装教程":
                weizhi = 1;
                break;
            case "MySQL下载与安装教程视频":
                weizhi = 2;
                break;
            case "MySQL环境变量配置":
                weizhi = 3;
                break;
            case "SQL server下载与安装":
                weizhi = 4;
                break;
            case "Android Studio下载网址与安装教程":
                weizhi = 5;
                break;
            case "PS下载网址与安装教程":
                weizhi = 6;
                break;
            case "IDEA下载与安装":
                weizhi = 7;
                break;
            case "AI下载网址与安装教程":
                weizhi = 8;
                break;
            case "Matlab下载网址与安装教程":
                weizhi = 9;
                break;
            default:
                break;
        }
        return weizhi;
    }
}
