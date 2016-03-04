package com.binaydevkota.xposedcodeapp;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class RemoveRoot implements IXposedHookLoadPackage{
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.nordea.mobiletoken")) {
            return;
        }
        XposedBridge.log("Mobile Token Package found");
        try {
            findAndHookMethod("com.gemalto.android.root.internal.root.MedlJni", lpparam.classLoader, "goMedlJni", String.class, String.class, XC_MethodReplacement.returnConstant(false));
        } catch (Throwable t) {
            XposedBridge.log(t);
        }
    }
}