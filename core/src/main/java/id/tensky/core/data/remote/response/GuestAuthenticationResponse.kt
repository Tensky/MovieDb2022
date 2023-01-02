package id.tensky.core.data.remote.response

import com.google.gson.annotations.SerializedName

class GuestAuthenticationResponse(
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("guest_session_id") var guestSessionId: String? = null,
    @SerializedName("expires_at") var expiresAt: String? = null,
)