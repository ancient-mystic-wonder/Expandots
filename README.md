# Expandots
A customizable cascading dots progress indicator.  Originally designed to port the progress indicator at http://roadmob.ph (shameless plug) for Android, now extended to be more flexible.

*gif here*

## Installation
Add the following to your app level ```build.gradle``` file:
```
repositories {
    maven {
        url 'https://dl.bintray.com/ancient-mystic-wonder/maven/'
    }
}
```
and then add to the dependencies:
```
compile 'com.dtlim.expandots:expandots:0.0.2'
```

## Usage
For a sample usage with sliders, see the ```app/``` folder.

#### XML
```
<com.dtlim.expandots.Expandots
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:count="3"
        app:minWidth="10dp"
        app:maxWidth="50dp"
        app:duration="1000"
        app:nextStartDelay="500"
        app:waitUntilFinish="true"
        app:color="@color/colorAccent" />
```

### Java
```
Expandots expandots = (Expandots) findViewById(R.id.expandots);
expandots.setCount(3);
expandots.setMinWidth(minWidth); // minWidth is in pixels
expandots.setMaxWidth(maxWidth); // maxWidth is in pixels
expandots.setDuration(1000);
expandots.setNextStartDelay(500);
expandots.setWaitUntilFinish(true);
```

## License
Library falls under Apache 2.0