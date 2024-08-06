package com.example.fitfoood.data.pref

import com.google.gson.annotations.SerializedName

/**
 * Profile model
 *
 * This data class is used to define profile model.
 *
 * @property birthDate User birth date
 * @property gender User gender (male or female)
 * @property height User height
 * @property weight User weight
 * @property bmiIndex User BMI index
 * @property bmiLabel User BMI label
 */
data class ProfileModel(

    @field:SerializedName("birth_date")
    val birthDate : String?,
    val gender : String?, // only 'male' or 'female'
    val height : Int?,
    val weight : Int?,

    @field:SerializedName("bmi_index")
    val bmiIndex : Float?,

    @field:SerializedName("bmi_label")
    val bmiLabel : String? // 'underweight', 'ideal', 'overweight', 'obesity'

)