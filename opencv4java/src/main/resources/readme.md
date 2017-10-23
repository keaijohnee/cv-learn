#文件使用说明

### *安装*
1.把x64或x86下的dll文件拷贝到工程使用的JDK版本中的jre中bin文件夹里
,比如我的是 *D:\Program Files\Java\jdk1.8.0_144\jre\bin*
2.把opencv-330.jar引入项目,建议用Maven安装在本地后再使用,安装命令为:
*mvn install:install-file -Dfile=opencv-330.jar -DgroupId=com.huawei.l00379880 -DartifactId=opencv -Dversion=3.3.0 -Dpackaging=jar*