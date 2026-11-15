package com.example.thirtydays.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.thirtydays.R

data class Day(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)

object DaysRepository {
    val days = listOf(
        Day(R.string.day1_title, R.string.day1_desc, R.drawable.day1),
        Day(R.string.day2_title, R.string.day2_desc, R.drawable.day2),
        Day(R.string.day3_title, R.string.day3_desc, R.drawable.day3),
        Day(R.string.day4_title, R.string.day4_desc, R.drawable.day4),
        Day(R.string.day5_title, R.string.day5_desc, R.drawable.day5),
        Day(R.string.day6_title, R.string.day6_desc, R.drawable.day6),
        Day(R.string.day7_title, R.string.day7_desc, R.drawable.day7),
        Day(R.string.day8_title, R.string.day8_desc, R.drawable.day8),
        Day(R.string.day9_title, R.string.day9_desc, R.drawable.day9),
        Day(R.string.day10_title, R.string.day10_desc, R.drawable.day10),
        Day(R.string.day11_title, R.string.day11_desc, R.drawable.day11),
        Day(R.string.day12_title, R.string.day12_desc, R.drawable.day12),
        Day(R.string.day13_title, R.string.day13_desc, R.drawable.day13),
        Day(R.string.day14_title, R.string.day14_desc, R.drawable.day14),
        Day(R.string.day15_title, R.string.day15_desc, R.drawable.day15),
        Day(R.string.day16_title, R.string.day16_desc, R.drawable.day16),
        Day(R.string.day17_title, R.string.day17_desc, R.drawable.day17),
        Day(R.string.day18_title, R.string.day18_desc, R.drawable.day18),
        Day(R.string.day19_title, R.string.day19_desc, R.drawable.day19),
        Day(R.string.day20_title, R.string.day20_desc, R.drawable.day20),
        Day(R.string.day21_title, R.string.day21_desc, R.drawable.day21),
        Day(R.string.day22_title, R.string.day22_desc, R.drawable.day22),
        Day(R.string.day23_title, R.string.day23_desc, R.drawable.day23),
        Day(R.string.day24_title, R.string.day24_desc, R.drawable.day24),
        Day(R.string.day25_title, R.string.day25_desc, R.drawable.day25),
        Day(R.string.day26_title, R.string.day26_desc, R.drawable.day26),
        Day(R.string.day27_title, R.string.day27_desc, R.drawable.day27),
        Day(R.string.day28_title, R.string.day28_desc, R.drawable.day28),
        Day(R.string.day29_title, R.string.day29_desc, R.drawable.day29),
        Day(R.string.day30_title, R.string.day30_desc, R.drawable.day30)
    )
}