# bone-android | [![Build Status](https://travis-ci.org/zhangchaoxu/bone-android.png?branch=master)](https://travis-ci.org/zhangchaoxu/bone-android)

## About
![Logo with Title]()
在Android开发的过程中,或多或少会以来一些云服务商提供的SDK,比如小米推送、百度定位、支付宝支付、微信登录等。
本Repo的目的是为了解决在引入这些sdk的过程中可能会存在以下问题：
1. sdk未发布到中央仓库,需要手动加入jar、so，甚至一些资源文件;
2. 需要侵入AndroidManifest.xml,做权限声明,activity/service,id/key等的定义;

## Module Version
各个sdk版本定义依据[sdkversion]_r[buildversion]的方式定义，如
小米消息推送服务SDK，sdk原始版本是v3.2.2,是这个model的第4次build,因此按本号是v3.2.2_r4

## Module Libs

### app
TODO demo app
 
### bone
this lib is @deprecated.

```gradle
dependencies {
    compile 'com.idogfooding.bone:bone:1.0.3@aar'
}
```

### Alipay v15.3.3(20170309)
[支付宝App支付SDK](https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.5LlDVj&treeId=193&articleId=105051&docType=1)
1. 引入jar
2. Android中声明权限和service

```gradle
dependencies {
    compile 'com.idogfooding.bone:Alipay:v15.3.3_r4@aar'
}
```

### WxCommon
[微信分享、登录、收藏、支付等功](https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419319167&token=&lang=zh_CN)
version: v3.1.1

```gradle
dependencies {
    compile 'com.idogfooding.bone:WxCommon:v3.1.1_r2@aar'
}
```

### MiPush v3.2.2
[小米消息推送服务](http://dev.xiaomi.com/console/appservice/push.html)
1. 引入jar
2. Android中声明权限和activity
3. 引入logo资源文件

```gradle
dependencies {
    compile 'com.idogfooding.bone:MiPush:v3.2.2_r4@aar'
}
```

### EaseUi
[环信即时通讯云](http://docs.easemob.com/im/start)
version: v3.2.3

```gradle
dependencies {
    compile 'com.idogfooding.bone:EaseUi:v3.2.3_r5@aar'
}
```

### ShareSDK
[第三方登录和社会化分享](http://sharesdk.mob.com/downloadDetail/ShareSDK/android)
version: v2.8.1

```gradle
dependencies {
    compile 'com.idogfooding.bone:ShareSDK:v2.8.1_r1'
}
```

集成了onesharesdk和一些资源，只包含了QQ、微信好友、微信朋友圈，若需要其他的，请自行加入libs。

### TBS v3.1.1
[腾讯浏览服务](http://x5.tencent.com/tbs/index.html)
1. 集成Android SDK（完整版）
2. Android中声明权限

```gradle
dependencies {
    compile 'com.idogfooding.bone:TBS:v3.1.1_r1@aar'
}
```

## Thanks
[AndPlug](https://github.com/ourbeehive/AndPlug)

## [How to Publish](https://github.com/zhangchaoxu/bone-android/blob/master/PUBLISH.md)

## License

    Copyright (C) 2016 iDogFooding(http://idogfooding.com/)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.