package com.example.a2021_androidproject.model

import com.google.gson.annotations.SerializedName
//파싱된 결과를 저장할 데이터 클래스.
data class Restaurant(
    @SerializedName("UC_SEQ") val id : Long,
    @SerializedName("MAIN_TITLE") val name :String,
    @SerializedName("GUGUN_NM") val gun : String,
    @SerializedName("LAT") val lat: Double,
    @SerializedName("LNG") val lng : Double,
    @SerializedName("ADDR1") val add : String,
    @SerializedName("CNTCT_TEL") val call: String,
    @SerializedName("RPRSNTV_MENU") val menu : String,
    @SerializedName("ITEMCNTNTS") val text : String,
    @SerializedName("MAIN_IMG_NORMAL") val img : String
){}