package com.example.a2021_androidproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.a2021_androidproject.databinding.ActivityDetailBinding
import com.example.a2021_androidproject.model.Restaurant
import com.example.a2021_androidproject.model.Review
import net.daum.android.map.coord.MapCoordLatLng
import net.daum.mf.map.api.MapCurrentLocationMarker
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class DetailActivity :AppCompatActivity(){

    private lateinit var binding: ActivityDetailBinding
    private lateinit var db :AppDataBase

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "ResSearchDB"
        ).build()



        val model = intent.getParcelableExtra<Restaurant>("ResModel")
        binding.nameTextview.text = model?.name.orEmpty()
        binding.descTextview.text = model?.text.orEmpty()

        val latitude = model!!.lat
        val longitude = model!!.lng


        //var container = document.getElementById(R.id.map_view)
        //var options = {
        //    MapPoint.GeoCoordinate(latitude,longitude)
        //}
        val mapP = MapPoint.mapPointWithGeoCoord(latitude,longitude)
        val marker = MapPOIItem()
        marker.mapPoint = mapP
        marker.markerType = MapPOIItem.MarkerType.BluePin
        marker.itemName = model!!.name
        marker.isShowCalloutBalloonOnTouch=false
        binding.mapView.setMapCenterPoint(mapP,true)
        binding.mapView.addPOIItem(marker)




        val gotokakao :String = "https://map.kakao.com/link/map/"+latitude.toString()+longitude.toString()



        Glide.with(binding.imgImgview.context)
            .load(model?.img.orEmpty())
            .into(binding.imgImgview)

        Thread{
            val review = db.reviewDao().getOnReview(model?.id?.toInt()?:0)
            runOnUiThread{
                binding.reviewEdittext.setText(review?.review.orEmpty())
            }
        }.start()

        binding.saveBtn.setOnClickListener{
            Thread{
                db.reviewDao().saveReview(
                    Review(model?.id?.toInt()?:0,
                        binding.reviewEdittext.text.toString())
                )
            }.start()
        }

        binding.phoneBtn.setOnClickListener{
            val callnum = model?.call.toString()
            val myUri = Uri.parse("tel:${callnum}")
            val myIntent = Intent(Intent.ACTION_DIAL, myUri)
            startActivity(myIntent)
        }

        binding.mapBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(gotokakao))
            startActivity(intent)
        }




    }
}