package gts.bitfinex.data.model

import com.squareup.moshi.Json

data class JsonResponse(
    @Json(name = "event")
    val event: String,
    @Json(name = "channel")
    val channel: String,
    @Json(name = "chanId")
    val chanId: String,
    @Json(name = "pair")
    val pair: String
)