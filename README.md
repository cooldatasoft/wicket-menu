Project aims to contain several dynamic css and javascript menu types to be used in wicket applications.


List of available css/javascript Menu's

* SunriseGlossDropDownMenu
* ChromeDropDownMenu
* SlideInMenu
* MultiLevelCssMenu


Only dependency is wicket itself 
Any recommendation, bug report or wish is very welcome.

For simplicity, I will keep the version number same as wicket framework itself and add wicket-menu version at the very end.

So if you are using **wicket 7.0.0** then you should get **wicket-menu 7.0.0.x** where x is the largest to get the latest.


For MultiLevelCssMenu to work properly, you need to strip the wicket tags.

eg. getMarkupSettings().setStripWicketTags(true);


Current Release version for maven users:

```
<dependency>
	<groupId>com.cooldatasoft</groupId>
	<artifactId>wicket-menu</artifactId>
	<version>7.0.0.0</version>
</dependency>
```



To compile it from source:
* You need to setup lombok framework for your IDE. https://projectlombok.org/download.html
