#include "JFunc.hpp"
#include "Save.hpp"
#include "Lua.hpp"
#include <string>
#include <jni.h>

extern "C" JNIEXPORT void JNICALL
Java_org_firstinspires_ftc_teamcode_modules_lua_LuaRoadRunner_callDisplacement(JNIEnv* env, jobject thiz)
{
  callNextDispMarker();
}

extern "C" JNIEXPORT void JNICALL Java_org_firstinspires_ftc_teamcode_modules_lua_LuaRoadRunner_buildPath(JNIEnv* env,
																										  jobject thiz,
																										  jstring name)
{
	FuncStat::setVals(env, thiz);
	Save::makeBuilder.init("makeBuilder", "(DDD)V");
	Save::lineTo.init("lineTo", "(DD)V");
	Save::splineTo.init("splineTo", "(DDD)V");
	Save::lineToLinearHeading.init("lineToLinearHeading", "(DDD)V");
	Save::splineToLinearHeading.init("splineToLinearHeading", "(DDDD)V");
	Save::lineToConstantHeading.init("lineToConstantHeading", "(DD)V");
	Save::splineToConstantHeading.init("splineToConstantHeading", "(DDD)V");
	Save::lineToSplineHeading.init("lineToSplineHeading", "(DDD)V");
	Save::splineToSplineHeading.init("splineToSplineHeading", "(DDDD)V");
	Save::marker.init("marker", "()V");
	Save::wait.init("wait", "(D)V");
	Save::rotate.init("turn", "(D)V");
  print("found functions");

	NodeGrid grid = NodeGrid();
  std::string str = env->GetStringUTFChars(name, NULL);
  std::string path = getPathName(str);
  print(path.c_str());
	Save::load(&grid, (FuncStat::storageDir + '/' + path));
	Save::exp(&grid);
}
