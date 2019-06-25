package com.bihe0832

import org.gradle.api.GradleException

/**
 *
 * @author zixie code@bihe0832.com
 * Created on 2019/5/10.
 * Description: Description
 *
 */
class CheckResult {
    //保存所有的依赖关联到的版本号
    static HashMap<String,HashMap<String,ArrayList>> sGroupList = new HashMap<>()
    static ArrayList<String> sGroupsHasBeenShowed = new ArrayList<>()

    static boolean dependenciesIsOk(String groupAndId, String version, String source){
//        println("groupAndId:" + groupAndId + "version:" + version + " found from:" + source)
//        println("CheckResult:" + this.toString())
        //该依赖已经存在
        if(null != sGroupList && sGroupList.containsKey(groupAndId)){
            // 有相同版本的依赖已经存在
            if(null != sGroupList.get(groupAndId) && sGroupList.get(groupAndId).containsKey(version)){
                if(!sGroupList.get(groupAndId).get(version).contains(source)){
                    sGroupList.get(groupAndId).get(version).add(source)
                }
            }else{
                ArrayList sourceList = new ArrayList()
                sourceList.add(source)
                sGroupList.get(groupAndId).put(version,sourceList)
            }
        }else{
            ArrayList sourceList = new ArrayList()
            sourceList.add(source)
            HashMap versionMap = new HashMap<>()
            versionMap.put(version,sourceList)
            sGroupList.put(groupAndId,versionMap)
        }
        if(sGroupList.get(groupAndId) != null && sGroupList.get(groupAndId).size() > 1){
            return false
        }else{
            return true
        }
    }

    static String getErrorInfo(String groupAndId){
        try {
            HashMap versionList = sGroupList.get(groupAndId)
            String result  = groupAndId  + " has different version: \n"
            for(version in versionList){
                result = result + "\t"  + version.key + " found from: \n"
                ArrayList sourceList = version.value
                for (int i = 0; i < sourceList.size(); i++) {
                    result = result + "\t \t " + sourceList.get(i) + "\n"
                }
            }
            return result
        }catch(Exception e){
            e.printStackTrace()
            System.err.println("Error: " + e.getStackTrace())
            return ""
        }
    }

    static void doCheck(int showResultType, String groupAndId, String version, String source){
        if(!dependenciesIsOk(groupAndId,version,source)){
            if(sGroupsHasBeenShowed.contains(groupAndId)){
                return
            }
            sGroupsHasBeenShowed.add(groupAndId)
            if(showResultType == GradleDependenciesCheckPluginExtension.SHOW_WARNING_WITH_RESULT){
                printWarningMsg(groupAndId)
            }else{
                throwRunningException(groupAndId)
            }
        }
    }

    static void printWarningMsg(String groupAndId){
        System.err.println("WARNING: " + getErrorInfo(groupAndId))
    }

    static void throwRunningException(String groupAndId){
       throw new GradleException("ERROR: " + getErrorInfo(groupAndId))
    }
}