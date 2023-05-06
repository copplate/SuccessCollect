💎所用Android studio版本为2021.2.1 Patch2。



🐴ShortSlideModule是从showswoller的tiktok项目里分出来的视频上下滑动模块，但是没有预加载功能，上下滑动会有小绿条，上拉加载完也会有视频不复位的bug。



🐴PreLoadTry1是第一次成功预加载的尝试，方法是在viewpager2的适配器里提前准备好SimpleExoPlayer，不过没法上拉加载。