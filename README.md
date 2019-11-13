# New stuff

This is a fork of a fork of ... a fork from https://github.com/dwickern/sbt-classloader-leak-prevention.

This fork is compiled against Play Framework 2.8.0-RC1 and Scala 2.12, 2.13 and published to my bintray repository. (The sbt plugin
is only compiled against Scala 2.12 as sbt 1.x itself is compiled against 2.12).

See Usage section on how to include my published version or clone this repository and run `sbt publishLocal`.

# sbt-classloader-leak-prevention

An sbt plugin to fix `java.lang.OutOfMemoryError: Metaspace/PermGen` errors during interactive sbt usage

# Features

1. Hooks into `sbt test` in jvm projects, and `sbt run` in PlayFramework projects
1. Automatically fixes known leaks using [mjiderhamn/classloader-leak-prevention](https://github.com/mjiderhamn/classloader-leak-prevention)
1. Detects and warns if classloader leaks if still occur, and optionally generates a heap dump

# Usage

Add the following line to your `project/plugins.sbt`:
```
resolvers ++= Seq(
  "skress@Bintray" at "https://dl.bintray.com/skress/maven/"
)
addSbtPlugin("de.skress" % "sbt-classloader-leak-prevention" % "0.8")
```

# Configuration

| Key                                                   | Type            | Default    | Description
| ----------------------------------------------------- | --------------- | ---------- | ------------------------------------------------------------------- |
| ClassLoaderLeakPreventor.enableLeakPrevention         | Boolean         | true       | Whether to use automatic ClassLoader leak prevention
| ClassLoaderLeakPreventor.enableLeakDetection          | Boolean         | true       | Whether to check for a leaking ClassLoader
| ClassLoaderLeakPreventor.leakDetectionAttempts        | Int             | 5          | Maximum number of leak detection attempts
| ClassLoaderLeakPreventor.leakDetectionInterval        | FiniteDuration  | 2.seconds  | How long to wait between leak detection attempts
| ClassLoaderLeakPreventor.enableLeakDetectionHeapDump  | Boolean         | false      | Whether to create a heap dump when a ClassLoader leak is detected
| ClassLoaderLeakPreventor.stopThreads                  | Boolean         | true       | Whether to force threads to stop
| ClassLoaderLeakPreventor.stopTimerThreads             | Boolean         | true       | Whether to force timer threads to stop
| ClassLoaderLeakPreventor.executeShutdownHooks         | Boolean         | true       | Whether to execute shutdown hooks
| ClassLoaderLeakPreventor.startOracleTimeoutThread     | Boolean         | true       | Whether to force the Oracle JDBC timer thread to start
| ClassLoaderLeakPreventor.threadWait                   | FiniteDuration  | 5.seconds  | How long to wait for threads to finish before stopping them
| ClassLoaderLeakPreventor.shutdownHookWait             | FiniteDuration  | 10.seconds | How long to wait for shutdown hooks to finish before stopping them

# Development

To run tests:

    sbt scripted

To publish locally:

    sbt "+ publishLocal"

To publish to Bintray (requires authorization):

    sbt "+ publish"
    
