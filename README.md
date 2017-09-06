# ZCalenderViewExtended
its ZCalenderView with WeekView and DayView

its extended code of [ZCalenderView](https://github.com/Zulqurnain/ZCalenderView)

# What are Feature Exhancements?
- Have Three Different View For **Day** , **Month** , **Year**
- Support for API Level 14+


## ScreenShots

<img src="https://github.com/Zulqurnain/ZCalenderViewExtended/raw/master/screenshots/11.png" width="200"> <img src="https://github.com/Zulqurnain/ZCalenderViewExtended/raw/master/screenshots/22.png" width="200"> <img src="https://github.com/Zulqurnain/ZCalenderViewExtended/raw/master/screenshots/33.png" width="200">

### Gradle [![](https://jitpack.io/v/Zulqurnain/ZCalenderViewExtended.svg)](https://jitpack.io/#Zulqurnain/ZCalenderViewExtended)

**Step 1** Add the JitPack repository to your build file. Add it in your build.gradle at the end of repositories.

```java
  repositories {
    maven { url "https://jitpack.io" }
  }
```

**Step-2** Add the dependency in the form

```java
dependencies {
    compile 'com.github.Zulqurnain:ZCalenderViewExtended:v1.0'
}
```
### Maven
```xml
<repository>
     <id>jitpack.io</id>
     <url>https://jitpack.io</url>
</repository>
```
**Step 2** Add the dependency in the form
```xml
<dependency>
	    <groupId>com.github.Zulqurnain</groupId>
	    <artifactId>ZCalenderViewExtended</artifactId>
	    <version>v1.0</version>
</dependency>
```
### Sbt
**Step-1** Add it in your build.sbt at the end of resolvers:
```java
resolvers += "jitpack" at "https://jitpack.io"
```
**Step-2** Add the dependency in the form
```java
	libraryDependencies += "com.github.Zulqurnain" % "ZCalenderViewExtended" % "v1.0"	
```

### Usage

i highly recommend you to check our sample app , because it is too much to explain here ;p

### Acknowledgements

Thanks to:
- [Robin Chutaux](https://github.com/traex) for his [CalendarListview](https://github.com/traex/CalendarListview).
- [JunGuan Zhu](https://github.com/NLMartian) for further improving Robin's work as [SilkCal](https://github.com/NLMartian/SilkCal)
- [Alam Kanak](https://github.com/alamkanak) for his excelent implementation for week day view his [Android-Week-View](https://github.com/alamkanak/Android-Week-View)

### MIT License

```
    The MIT License (MIT)
    
    Copyright (c) 2014 Junguan Zhu
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
```
