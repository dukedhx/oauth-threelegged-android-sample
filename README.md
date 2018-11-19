# oauth-threelegged-android-sample

[![Android](https://img.shields.io/badge/android-7.1.1-brightgreen.svg)](https://www.android.com/versions/nougat-7-0/)
[![Appcompat](https://img.shields.io/badge/appcompat-7.26-green.svg)](https://developer.android.com/reference/android/support/v7/appcompat/package-summary)
[![Java](https://img.shields.io/badge/java-7-green.svg)](https://docs.oracle.com/javase/7/docs/api/)  
[![Build Status](https://travis-ci.org/dukedhx/oauth-threelegged-android-sample.svg?branch=master)](https://travis-ci.org/dukedhx/oauth-threelegged-android-sample)
[![codebeat badge](https://codebeat.co/badges/0aed9e10-f37f-421f-9c60-0540f6d540f7)](https://codebeat.co/projects/github-com-dukedhx-oauth-threelegged-android-sample-master)
[![Codestyle](https://img.shields.io/badge/CodingStyle-IntelliJStandard-green.svg)](https://www.jetbrains.com/help/idea/configuring-code-style.html)

# Description

Simple Android native sample demonstrating three legged OAuth with Autodesk Forge.

# Thumbnail
![thumbnail](/thumbnail.gif)

# Setup
<!-- Environment and IDE setup goes here -->

``./app/src/main/res/values/strings.xml``

```
<string name="FORGE_CLIENT_ID">YOUR_FORGE_APP_CLIENT_ID</string>
<string name="FORGE_CLIENT_SECRET">YOUR_FORGE_APP_SECRET</string>
<string name="FORGE_CALLBACK_SCHEME">YOUR_ANDROID_APP_SCHEME</string>
<string name="FORGE_CALLBACK_HOST">YOUR_ANDROID_APP_DOMAIN</string>
```

# Run locally

- Put the callback url of your Forge app as ``yourOwnAndroidAppScheme://yourOwnAndoirdAppDomainName``

# Tips & tricks
<!-- ProGuard tips goes here -->

# Troubleshooting
<!-- Redirecting issues -->
- In compiler options the language level is set at 1.8 but the badge on this page says 1.7? None of the 1.8 features were used. Those settings are there to make build pass on TravisCI.

# License

This sample is licensed under the terms of the [MIT License](http://opensource.org/licenses/MIT).
Please see the [LICENSE](LICENSE) file for full details.

# Written by

Bryan Huang (Forge Partner Development)  
http://forge.autodesk.com
