package daniel.base.application.core.model

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("avatar") val avatar: String
)
