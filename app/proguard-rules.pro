# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep BlackBox classes
-keep class top.niunaijun.blackbox.** { *; }
-keepclassmembers class top.niunaijun.blackbox.** { *; }

# Keep Pine Xposed classes
-keep class top.canyie.pine.** { *; }
-keep class de.robv.android.xposed.** { *; }

# Keep Application class
-keep class com.spidy.engine.SpidyEngineApplication { *; }

# Keep data binding
-keepclassmembers class * implements androidx.viewbinding.ViewBinding {
    public static *** bind(android.view.View);
    public static *** inflate(android.view.LayoutInflater);
}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
