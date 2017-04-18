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
*  the jar/doc will be publish in maven, and add to jcenter if you want in bintray page

