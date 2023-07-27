package com.codebaron.sharedlogc.models.userprofile

/**
 * I want to be able to get as much user data as possible
 * Profile Info,
 * Favourite movies,
 * Disliked movies,
 * Subscription start and end date
 */
data class ProfileData(
    val first_name: String,
    val last_name: String,
    val phone_number: Int,
    val email_address: String,
    val dat_of_birth: String,
    val marital_status: String,
    val subscribed: Boolean,
    val sign_up_date: String,
    val sign_up_time: String,
    val last_seen: String,
    val profile_picture: String,
    val subscription_start_date: String,
    val subscription_end_date: String,
)
