package com.juggist.baselibrary

import okhttp3.RequestBody
import retrofit2.http.*
import java.io.Serializable

const val BASE_URL_PATH = "/upc-api/citycourse/api"
interface ApiService {
    @GET("assets/bitcoin")
    suspend fun getName():PriceBean

    /**获取指定周有课的教师信息**/
    @POST("$BASE_URL_PATH/DataSearch/GetWeekCourseSchedule")
    suspend fun getWeekScheduleTeacherInfo(@Body body: RequestBody, @HeaderMap headerMap: HashMap<String,Any>):ResultTest

    /**获取指定周有课的教师信息**/
    @POST("$BASE_URL_PATH/DataSearch/GetCourseScheduleByDate")
    suspend fun getCourseScheduleByDate(@Body body: RequestBody, @HeaderMap headerMap: HashMap<String,Any>):ResultTest

    /**获取指定周有课的教师信息**/
    @POST("$BASE_URL_PATH/DataSearch/GetWeekCourseSchedule")
    @FormUrlEncoded
    suspend fun getWeekScheduleTeacherInfo2(@Field("startDate")  startDate : String,@Field("endDate") endDate:String, @HeaderMap headerMap: HashMap<String,Any>):Response<Any?>
}
open class Response<T> : Serializable {
    var code = 0
    var msg: String? = null
}
data class ResultTest(val code : Int = 0,val msg :String = ""):Serializable