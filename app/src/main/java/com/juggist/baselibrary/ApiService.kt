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
    suspend fun getCourseScheduleByDate(@Body body: RequestCourseScheduleByDate, @HeaderMap headerMap: HashMap<String,Any>):Response2<CourseDayBean>

    /**获取指定周有课的教师信息**/
    @POST("$BASE_URL_PATH/DataSearch/GetWeekCourseSchedule")
    @FormUrlEncoded
    suspend fun getWeekScheduleTeacherInfo2(@Field("startDate")  startDate : String,@Field("endDate") endDate:String, @HeaderMap headerMap: HashMap<String,Any>):Response<Any?>
}

data class RequestCourseScheduleByDate(
    val schoolIds: IntArray = intArrayOf(),
    val classDate: String = "2020-11-11",
    val platform: Int = 2
)

open class Response<T> : Serializable {
    var code = 0
    var msg: String? = null
}
open class Response2<T> :Serializable {
    var code = 0
    var status: String? = null
    var msg: String? = null
    var data: T? = null
    override fun toString(): String {
        return "Response(code=$code, status=$status, msg=$msg, data=$data)"
    }

}
data class ResultTest( val curTime: String,
                       val dangweiList: List<DangweiDetail>? = null):Serializable

data class CourseDayBean( val curTime: String,
                       val dangweiList: List<DangweiDetail>? = null):Serializable
data class DangweiDetail(

    val dangwei:Int = 0,
    val dangwei_name:String ="" ,
    val start_time:String  = "",
    val end_time:String = "",
    val courseList:List<CourseDetail>? = null
)
data class CourseDetail(
    var type :Int = 0,//自定义添加,课次状态
    var type_lock :Int = 1,//自定义添加，校区锁定
    var weekDate:Int = 0,//自定义添加，周几
    val attendanceState: Int = 0 ,
    val attendanceStateName: String = "",
    val courseType: Int = 0,
    val courseTypeName: String = "",
    val createTime: String = "",
    val dangwei: Int = 0,
    val dangwei_name: String = "",
    val date: String = "",
    val end_time: String = "",
    val gradeName: String = "",
    val id: Int = 0,
    val isConfirm: Boolean = false,
    val isOnline: Int = 0,
    val isVip: Boolean = false,
    val schoolId: Int = 0,
    val schoolName: String = "",
    val start_time: String = "",
    val studentId: Int = 0,
    val studentName: String = "",
    val subjectName: String = "",
    val teacherId: Int = 0,
    val teacherName: String = ""
)