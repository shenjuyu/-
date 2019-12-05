BBS论坛的模仿项目
=================

## 功能

<b>前端页面</b>

* 基础的帖子、板块显示
* 用户的登陆和注册
* 用户查看自己的信息
* 发表帖子/回复
* 删除帖子/回复
* 举报帖子/回复(举报信息会在后台处理)

	在发表帖子/回复时使用了百度的```ueditor```文本编辑器

<b>后台页面</b>

* 板块的增、查、改
* 用户状态的查看和修改(是否被禁用)
* 管理员的状态的查看和修改(是否被禁用/是否通过管理员注册请求)
* 用户举报信息的查看和处理

-------

## 项目环境

	jdk 1.8
	
	tomcat 8.0.53
	
	mysql 5.7 
	
	服务器 阿里云服务器 系统：CentOS 7.6 64位

<b>注意：</b>

由于mysql5.7版本默认设置ONLY_FULL_GROUP_BY限制，此项目中的某些sql查询语句将无法执行[点击此处寻求解决方法](https://www.cnblogs.com/fswhq/p/9729761.html "针对mysql5.7的解决方法")
	
在进行项目部署之前要将[context.xml](https://github.com/shenjuyu/BBS/blob/master/WebContent/META-INF/context.xml)中的username与password的值进行相应的修改
	
##### 关于运行视频

	实例视频过大，已取消上传