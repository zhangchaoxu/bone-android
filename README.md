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

## How to Publish

### Publish to maven and jcente
*  add local.properties with bintray.user = yourusername and bintray.apikey = yourapikey under root folder
*  edit module gradle build file add publish setting 
*  run `gradlew bintrayUpload` for global project upload or `gradlew :ModuleName:bintrayUpload` for subModule publish
*  the jar/doc will be publish in maven, and add to jcenter if you want

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


