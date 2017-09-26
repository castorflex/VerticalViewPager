## Description

Small library allowing you to have a `VerticalViewPager`. It's just a copy paste from the v19 ViewPager available in the support lib, where I changed all the left/right into top/bottom and X into Y.

Nothing complicated here, but you can gain some time avoiding rewriting it. 

## Integration

The lib will be available soon on Maven Central. All you have to do is add it on your gradle build:

```xml
dependencies {
    compile 'com.github.castorflex.verticalviewpager:library:19.0.1'
}
```

## Note

`PageAdapter#getPageWidth()` is used as a `getPageHeight()` in the lib.

## License

```
"THE BEER-WARE LICENSE" (Revision 42):
You can do whatever you want with this stuff.
If we meet some day, and you think this stuff is worth it, you can buy me a beer in return.
```

#### Badges

[![Analytics](https://ga-beacon.appspot.com/UA-32954204-2/VerticalViewPager/readme)](https://github.com/igrigorik/ga-beacon)

[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/castorflex/verticalviewpager/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

