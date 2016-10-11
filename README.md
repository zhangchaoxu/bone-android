# bone-android | [![Build Status](https://travis-ci.org/zhangchaoxu/bone-android.png?branch=master)](https://travis-ci.org/zhangchaoxu/bone-android)

## About
![Logo with Title]()

bone-android is forked from [AndPlug](https://github.com/ourbeehive/AndPlug).  

## Module Libs

### app
App is the demo Android Application for AndPlug, show how to use the module libs and how to setup an App with the modules. [more](https://github.com/ourbeehive/AndPlug/App/blob/master/README_CN.md)

[![Google Play](https://developer.android.com/images/brand/en_app_rgb_wo_60.png)](https://play.google.com/store/apps/details?id=)

[![fir.im](http://ourbeehive.github.io/AndPlug/qrcode.png)](http://fir.im/bone-android)
 
### bone
bone is the base lib

```gradle
dependencies {
    compile ''
}
```

### WxCommon
[使用微信分享、登录、收藏、支付等功能需要的库以及文件](https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419319167&token=&lang=zh_CN)

## How to Publish

### Publish library into maven and jcenter
*  add local.properties with bintray.user = yourusername and bintray.apikey = yourapikey under root folder
*  add maven gradle plugin into root build.gradle
```
classpath 'com.github.dcendents:android-maven-gradle-plugin:1.4.1'
classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.7'
```
*  add jcenter cfg into subModule's build.gradle
```
def siteUrl = 'https://github.com/zhangchaoxu/bone-android'
def gitUrl = 'https://github.com/zhangchaoxu/bone-android.git'
// group name do not need match package name
// will be display in the compile path
def reponame = 'BaiduMap'
group = 'com.idogfooding.bone'

Properties properties = new Properties()
properties.load(project.rootProject.file('local.properties').newDataInputStream())

install {
    repositories.mavenInstaller {
        // This generates POM.xml with proper parameters
        pom {
            project {
                packaging 'aar'
                name reponame
                url siteUrl
                licenses {
                    license {
                        name 'The Apache Software License, Version 2.0'
                        url 'http://www.apache.org/licenses/LICENSE-2.0.txt'
                    }
                }
                developers {
                    developer {
                        id properties.getProperty("developer.id")
                        name properties.getProperty("developer.name")
                        email properties.getProperty("developer.email")
                    }
                }
                scm {
                    connection gitUrl
                    developerConnection gitUrl
                    url siteUrl
                }
            }
        }
    }
}

task sourcesJar(type: Jar) {
    from android.sourceSets.main.java.srcDirs
    classifier = 'sources'
}
task javadoc(type: Javadoc) {
    source = android.sourceSets.main.java.srcDirs
    classpath += project.files(android.getBootClasspath().join(File.pathSeparator))
    options {
        encoding = "UTF-8"
        charSet "UTF-8"
    }
}
task javadocJar(type: Jar, dependsOn: javadoc) {
    classifier = 'javadoc'
    from javadoc.destinationDir
}
artifacts {
    archives javadocJar
    archives sourcesJar
}

bintray {
    user = properties.getProperty("bintray.user")
    key = properties.getProperty("bintray.apikey")
    configurations = ['archives']
    pkg {
        repo = "maven"
        name = reponame
        websiteUrl = siteUrl
        vcsUrl = gitUrl
        licenses = ["Apache-2.0"]
        publish = true
    }
}
```
*  run `gradlew bintrayUpload` for global project upload or `gradlew :ModuleName:bintrayUpload` for subModule publish
*  the jar/doc will be publish in maven, and add to jcenter if you want in bintrayy page()

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


