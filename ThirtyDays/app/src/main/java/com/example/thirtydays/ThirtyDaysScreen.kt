package com.example.thirtydays

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydays.model.Day
import com.example.thirtydays.model.DaysRepository
import com.example.thirtydays.ui.theme.ThirtyDaysTheme

@Composable
fun DayCard(day: Day, dayNumber: Int, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        Column(
            modifier = Modifier
                .clickable(
                    onClickLabel = stringResource(if (expanded) R.string.collapse else R.string.expand)
                ) { expanded = !expanded }
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.day_number, dayNumber),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(day.title),
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Anh trang tri: tieu de da mo ta noi dung nen khong can contentDescription
            Image(
                painter = painterResource(day.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            AnimatedVisibility(visible = expanded) {
                Text(
                    text = stringResource(day.description),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

@Composable
fun DaysList(
    days: List<Day>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(days) { index, day ->
            DayCard(
                day = day,
                dayNumber = index + 1,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayCardPreview() {
    ThirtyDaysTheme(darkTheme = false) {
        DayCard(day = DaysRepository.days[0], dayNumber = 1)
    }
}