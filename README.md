# CCSExampleApi1
Example Api1. This supports the following URLs

* /greeting
* /greeting?name=Albert
* /systeminfo
* /systeminfo?detail=all
* /systeminfo?detail=properties
* /systeminfo?detail=environment


Note that this will expose a lot of information about the system on which the application is running.

The maven file include the Spotify Docker plugin. The docker images can be build with:

mvn install dockerfile:build


