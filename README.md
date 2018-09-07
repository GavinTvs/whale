# Gavin Source


# 组件化日志

[TOC]



<!-- toc -->

## 项目分层

### 原则

项目分层的原则是便于代码的复用和维护，保证低耦合和高内聚的思想。即低层级模块不依赖于高等级的模块，高等级模块的更改不会影响到低层级模块。

### 层级目录

link: WPS_excel

## 组件化准备

### Module Application

### Module Activity/Fragment

### Module Gradle优化

## 组件化编程

### 模块单独打包策略

## 坑

### annotationProcessor 编译时注解（ARouter/ButterKnife）

高层组件需要依赖低层组件的第三方时，如需要使用编译时注解annotationProcessor，则必须放在使用的组件的build.gradle中声明，在低层声明高层并不能直接引用。ButterKnife会出现View绑定不成功，ARouter会出现找不到Path。

### 第三方库 implementation/api的使用

如果在低层组件中需要把第三方库暴露给高层组件，则用api来引用第三方，如果只在本级Module使用，则只需要使用implementation，能有效的降低编译时间。

### ButterKnife R2的使用

ButterKnife在Module中使用时，需要把R.id.xxviewid改为R2.id.xxxviewid，Module下ButterKnife会生成一个R2文件来标记资源id，但是该资源id不是final类型，在条件判断时不可使用switch，需改为if&else来进行条件判断。

### 组件的BuildConfig





## 待办优化

### 使用AndResGuard进行资源混淆





## 新建Module注意事项

### 如果Module需要在Application做初始化操作

步骤：

1. 建立[ModuleName]InitLogic类，继承BaseAppLogic
2. 在AppAppcation的initLogic方法中注册组件的BaseAppLogic。
3. 重写BaseAppLogic的父类方法，方法名就等同于Application的生命周期的方法名。

### 如果Module需要单独打包调试

步骤：

1. 在需要单独编译的Module src目录下建立debug目录(与mian的目录结构一致)，新建应用启动的Activity

2. 在debug的AndroidManifest配置启动的Activity

   ```xml
    <application
           android:allowBackup="true"
           android:label="@string/app_name"
           android:supportsRtl="true">
           <activity
               android:name=".[ModuleName]DebugActivity"
               android:theme="@style/CommonAppTheme">
               <intent-filter>
                   <action android:name="android.intent.action.MAIN" />

                   <category android:name="android.intent.category.LAUNCHER" />
                   <!-- <action android:name="material.com.settings" /> -->
                   <!-- <category android:name="android.intent.category.DEFAULT"/> -->
               </intent-filter>
           </activity>
       </application>
   ```

3. 修改basic_config.gradle下新增开关参数is[ModuleName]Run，代表该组件单独编译

4. 在application（app）下的build.gradle根据开关参数is[ModuleName]Run判断是否依赖组件。

   ```groovy
   if(!project.ext.is[ModuleName]Run){
           implementation project(':module_xxx')
   }
   ```

5. 在Module下的build.gradle配置SourceSet，根据开关参数is[ModuleName]Run选择不同的项目目录

   ```groovy
   sourceSets {
           debug {
               if (project.ext.is[ModuleName]Run) {
                   manifest.srcFile 'src/debug/AndroidManifest.xml'
                   res.srcDirs = ['src/debug/res', 'src/main/res']
               } else {
                   manifest.srcFile 'src/main/AndroidManifest.xml'
                   resources {
                       //排除java/debug文件夹下的所有文件
                       exclude 'src/debug/*'
                   }
               }
           }
           release {
               resources {
                   //排除java/debug文件夹下的所有文件
                   exclude 'src/debug/*,src/androidTest/*,src/test/*'
               }
           }
       }
   ```

