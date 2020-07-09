package com.bihe0832

import org.gradle.api.Project

class GradleDependenciesCheckPluginExtension {

    public static final int SHOW_WARNING_WITH_RESULT = 1
    public static final int SHOW_EXCEPTION_WITH_RESULT = 2
    int showResultType = SHOW_EXCEPTION_WITH_RESULT
    String excludePackage = ""
}


