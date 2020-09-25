package com.juggist.baselibrary

data class PriceBean(val timestamp:String,val data:Data) {
}
data class Data(val id:String,val rank:String,val symbol:String,val name:String,val supply:String,val maxSupply:String,val marketCapUsd:String,val volumeUsd24Hr:String,val priceUsd:String,val changePercent24Hr:String,val vwap24Hr:String){

}