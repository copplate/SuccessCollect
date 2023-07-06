💎所用Android studio版本为2021.2.1 Patch2。



🐴ShortSlideModule是从showswoller的tiktok项目里分出来的视频上下滑动模块，但是没有预加载功能，上下滑动会有小绿条，上拉加载完也会有视频不复位的bug。

🐴PreLoadTry1是第一次成功预加载的尝试，取消了视频播放前的效小绿条，方法是在viewpager2的适配器里提前准备好SimpleExoPlayer，不过没法上拉加载更多。

🐴VideoTakePrac是短视频拍摄模块。

🐴FragmentPrac是2022/10模仿子林Android，一个很棒的fragment添加的展示，利用了FragmentContainerview。

🐴GuolinMVVM是第一行代码(2019年出版)MVVM部分的示例代码，每次找API很麻烦，存档一下，虽然有一些API是过时的。

🐴BottomSheetDiaPrac是根据@yechaoa的BottomSheetDialog做的练习，https://blog.csdn.net/yechaoa/article/details/110134991

🐴RecyTanTan是根据@史大拿的RecyclerView仿探探效果做的练习，[(91条消息) android 浅析RecyclerView回收复用机制及实战(仿探探效果)_史大拿的博客-CSDN博客](https://blog.csdn.net/weixin_44819566/article/details/121129195?spm=1001.2014.3001.5506)

🐴GuolinChatBubblePrac是仿照第一行代码写的聊天界面。

🐴SlidePreLoadPrac1是根据gpt的方法实现了视频的本地预缓存，采用了提前准备mediasource的方法，但是此demo只适配了前六个，再上拉加载更多会报bug。缓存之后依然有小绿条，看来时间都花在解码上了（这么说是因为我之前提前准备好SimpleExoplayer的时候是可以秒开没有小绿条的）。

​        而且经过进一步研究，SimpleExoPlayer只能在主线程prepare，而且好像只能同时prepare一个，这就很烦。

🐴ChartPrac1是模仿@路很长写的自定义图表，和一个随手势水平移动的小球，https://juejin.cn/post/6922365337119916040

🐴VideoFrameProgressbar是模仿绘影字幕，用视频帧当滚动轴，同时视频支持离线播放。

🐴SlidePreLoadPrac2通过监听SimpleExoplayer的加载进度，获得预加载下一个SimpleExoplayer的时机。

🐴SpinnerPrac1,问的gpt，通过补间动画，制作了一个旋转箭头弹出三个按钮的动画效果

🐴ConstraintLayoutFlowTest是谷歌官方的flexbox流式布局，很好用。还用了一个ConstraintLayout的flow模式，不过有bug。

🐴DiffUtilTest是采用gpt的方案来使用DiffUtil。

🐴SideMenuTest是子林讲的侧滑菜单，另外今天还重听了一遍网课，查缺补漏得复习了：1、DrawerLayout添加属性tools:openDrawer="start"，可以预览侧边栏效果。

🐴RoomTestguolin是仿照郭霖公众号，使用Kotlin创建了room数据库，并实现了升级数据库version并变换表结构的功能，但是升级的时候增加表失败了。
