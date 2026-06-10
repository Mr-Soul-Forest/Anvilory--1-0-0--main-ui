/**Copyright 2025 Forge-of-Ovorldule (https://github.com/Forge-of-Ovorldule) and Mr-Soul-Forest (https://github.com/Mr-Soul-Forest)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package forgeofovorldule.anvilory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import anvilory.composeapp.generated.resources.Res
import anvilory.composeapp.generated.resources.export
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource

@Composable
fun PlotsContent(viewModel: AppViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(UIC_light)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.4.dp)
                .padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(29.6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(UIC, RoundedCornerShape(47.6.dp))
                    .padding(start = 19.6.dp, end = 9.2.dp)
                    .height(48.4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = ts_Your_plots,
                    color = UIC_extra_dark,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = JetBrainsFont(),
                    fontSize = 19.2.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(7.2.dp)
                ) {
                    IconButton(onClick = {
                        SaveManager.save()
                        //export()
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.export),
                            contentDescription = ts_Export,
                            modifier = Modifier.size(28.dp),
                            colorFilter = ColorFilter.tint(UIC_light)
                        )
                    }
                    IconButton(onClick = {
                        SaveManager.save()
                        //import {
                        //    viewModel.setStatus(AppStatus.LOADING)
                        //}
                    }) {
                        Image(
                            painter = painterResource(Res.drawable.export),
                            contentDescription = ts_Import,
                            modifier = Modifier.size(28.dp)
                                .rotate(180f),
                            colorFilter = ColorFilter.tint(UIC_light)
                        )
                    }
                    var expended by remember { mutableStateOf(false) }
                    Box(
                        modifier = Modifier.size(35.6.dp)
                            .background(UIC_extra_light, RoundedCornerShape(35.6.dp))
                            .clickable {
                                expended = true
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = SaveManager.data.language.toString(),
                            color = UIC_light,
                            fontWeight = FontWeight.Normal,
                            fontFamily = JetBrainsFont(),
                            fontSize = 21.2.sp
                        )
                    }
                    DropdownMenu(
                        expended,
                        { expended = false },
                        shape = RoundedCornerShape(24.2.dp),
                        containerColor = UIC,
                        modifier = Modifier.padding(horizontal = 9.2.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(7.2.dp)
                        ) {
                            for (languageItem in Languages.entries) {
                                Box(
                                    modifier = Modifier.size(35.6.dp)
                                        .background(UIC_extra_light, RoundedCornerShape(35.6.dp))
                                        .clickable {
                                            SaveManager.data.language = languageItem
                                            SaveManager.save()
                                            expended = false
                                            viewModel.setStatus(AppStatus.LOADING)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = languageItem.toString(),
                                        color = UIC_light,
                                        fontWeight = FontWeight.Normal,
                                        fontFamily = JetBrainsFont(),
                                        fontSize = 21.2.sp
                                    )
                                }
                            }
                        }
                    }
                    CreatePlotDialog(viewModel)
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.8.dp),
                modifier = Modifier.verticalScroll(rememberScrollState())
                    .padding(horizontal = 19.6.dp)
            ) {
                for (item in SaveManager.data.plots.indices) {
                    OnePlotBlock(item, viewModel)
                }
            }
        }
    }
}

@Composable
private fun OnePlotBlock(index: Int, viewModel: AppViewModel) {
    val plot = SaveManager.data.plots[index]
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.4.dp),
        modifier = Modifier.height(201.6.dp)
            .fillMaxWidth()
            .background(UIC_extra_light, RoundedCornerShape(18.8.dp))
            .clip(RoundedCornerShape(18.8.dp))
            .clickable {
                edit_plot = index
                viewModel.setStatus(AppStatus.CHAPTERS)
            }
            .padding(18.8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.weight(0.66f)
            ) {
                Text(
                    text = plot.title + " ",
                    color = UIC,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = JetBrainsFont(),
                    fontSize = 19.2.sp,
                )
                Text(
                    text = "-> " + if (plot.typeOfPlot == TypeOfPlot.STORY) ts_story else ts_movie,
                    color = UIC,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = JetBrainsFont(),
                    fontSize = 14.4.sp,
                )
            }

            Box(
                modifier = Modifier.height(25.2.dp)
                    .background(UIC_light, RoundedCornerShape(12.6.dp))
                    .weight(0.33f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${
                        Clock.System.now()
                            .toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays() - plot.lastEdit.toEpochDays()
                    } $ts_days_ago",
                    color = UIC_extra_light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal,
                    fontFamily = JetBrainsFont(),
                    fontSize = 12.8.sp,
                    modifier = Modifier.padding(horizontal = 12.6.dp)
                )
            }

        }
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "$ts_Description:",
                color = UIC_light,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = JetBrainsFont(),
                fontSize = 13.2.sp
            )
            Text(
                text = plot.description,
                color = UIC_light,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Normal,
                fontFamily = JetBrainsFont(),
                fontSize = 12.8.sp
            )
            for (chapter in plot.chapters) {
                Text(
                    text = "${chapter.title}:",
                    color = UIC_light,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = JetBrainsFont(),
                    fontSize = 13.2.sp
                )
                for (part in chapter.parts) {
                    Text(
                        text = part.text,
                        color = UIC_light,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Normal,
                        fontFamily = JetBrainsFont(),
                        fontSize = 12.8.sp
                    )
                }
            }
        }
    }
}

private enum class TypesOfChangedParams {
    TITLE,
    DESCRIPTION,
    TYPE,
    NONE
}

@Composable
private fun CreatePlotDialog(viewModel: AppViewModel) {
    var show by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.size(35.6.dp)
            .background(UIC_extra_light, RoundedCornerShape(35.6.dp))
            .clickable { show = true },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "+",
            color = UIC_light,
            fontWeight = FontWeight.Normal,
            fontFamily = JetBrainsFont(),
            fontSize = 30.sp
        )
    }

    var plot by remember { mutableStateOf(Plot()) }
    var plotTitle by remember { mutableStateOf(plot.title) }
    var plotDescription by remember { mutableStateOf(plot.description) }
    var plotType by remember { mutableStateOf(plot.typeOfPlot) }
    var editNow by remember { mutableStateOf(TypesOfChangedParams.NONE) }

    if (show) {
        AlertDialog(
            containerColor = UIC_light,
            onDismissRequest = { show = false },
            title = {
                Text(
                    text = ts_Create_a_plot,
                    color = UIC,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = JetBrainsFont(),
                    fontSize = 19.2.sp
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.4.dp),
                        modifier = Modifier.height(201.6.dp)
                            .fillMaxWidth()
                            .background(UIC_extra_light, RoundedCornerShape(18.8.dp))
                            .padding(18.8.dp)
                            .clickable { editNow = TypesOfChangedParams.NONE }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                modifier = Modifier.weight(0.66f)
                            ) {
                                Text(
                                    text = plot.title + " ",
                                    color = UIC,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = JetBrainsFont(),
                                    fontSize = 19.2.sp,
                                    modifier = Modifier.weight(1f)
                                        .clickable { editNow = TypesOfChangedParams.TITLE }
                                )
                                Text(
                                    text = "-> " + if (plot.typeOfPlot == TypeOfPlot.STORY) ts_story else ts_movie,
                                    color = UIC,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.ExtraLight,
                                    fontFamily = JetBrainsFont(),
                                    fontSize = 14.4.sp,
                                    modifier = Modifier.clickable { editNow = TypesOfChangedParams.TYPE }
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier.clickable { editNow = TypesOfChangedParams.DESCRIPTION }
                        ) {
                            Text(
                                text = "$ts_Description:",
                                color = UIC_light,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = JetBrainsFont(),
                                fontSize = 13.2.sp
                            )
                            Text(
                                text = plot.description,
                                color = UIC_light,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Normal,
                                fontFamily = JetBrainsFont(),
                                fontSize = 12.8.sp
                            )
                            for (chapter in plot.chapters) {
                                Text(
                                    text = "${chapter.title}:",
                                    color = UIC_light,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = JetBrainsFont(),
                                    fontSize = 13.2.sp
                                )
                                for (pair in chapter.parts) {
                                    Text(
                                        text = pair.text,
                                        color = UIC_light,
                                        overflow = TextOverflow.Ellipsis,
                                        fontWeight = FontWeight.Normal,
                                        fontFamily = JetBrainsFont(),
                                        fontSize = 12.8.sp
                                    )
                                }
                            }
                        }
                    }
                    Text(
                        text = "(i) $ts_To_change_an_item__click_",
                        color = UIC,
                        fontWeight = FontWeight.Normal,
                        fontFamily = JetBrainsFont(),
                        fontSize = 13.2.sp,
                        textAlign = TextAlign.Center
                    )
                    if (editNow != TypesOfChangedParams.NONE) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .background(UIC_extra_light, RoundedCornerShape(18.8.dp))
                                .padding(18.8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            when (editNow) {
                                TypesOfChangedParams.TITLE -> {
                                    BasicTextField(
                                        value = plotTitle,
                                        onValueChange = {
                                            plotTitle = it
                                            plot.title = it
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        textStyle = TextStyle(
                                            color = UIC,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontFamily = JetBrainsFont(),
                                            fontSize = 19.2.sp
                                        ),
                                        singleLine = true,
                                        decorationBox = {
                                            Box(
                                                modifier = Modifier.fillMaxWidth(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                it()
                                            }
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                                    )
                                }

                                TypesOfChangedParams.DESCRIPTION -> {
                                    BasicTextField(
                                        value = plotDescription,
                                        onValueChange = {
                                            plotDescription = it
                                            plot.description = it
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        textStyle = TextStyle(
                                            color = UIC_light,
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = JetBrainsFont(),
                                            fontSize = 12.8.sp
                                        ),
                                        singleLine = true,
                                        decorationBox = {
                                            Box(
                                                modifier = Modifier.fillMaxWidth(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                it()
                                            }
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                                    )
                                }

                                TypesOfChangedParams.TYPE -> {
                                    Column {
                                        Box(
                                            modifier = Modifier.background(
                                                if (plotType == TypeOfPlot.STORY) UIC_light else Color.Transparent,
                                                RoundedCornerShape(18.8.dp)
                                            ).fillMaxWidth().clickable {
                                                plotType = TypeOfPlot.STORY
                                                plot.typeOfPlot = TypeOfPlot.STORY
                                            },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = ts_story,
                                                color = if (plotType == TypeOfPlot.STORY) UIC_extra_light else UIC,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                fontWeight = FontWeight.ExtraLight,
                                                fontFamily = JetBrainsFont(),
                                                fontSize = 14.4.sp
                                            )
                                        }
                                        Box(
                                            modifier = Modifier.background(
                                                if (plotType == TypeOfPlot.MOVIE) UIC_light else Color.Transparent,
                                                RoundedCornerShape(18.8.dp)
                                            ).fillMaxWidth().clickable {
                                                plotType = TypeOfPlot.MOVIE
                                                plot.typeOfPlot = TypeOfPlot.MOVIE
                                            },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = ts_movie,
                                                color = if (plotType == TypeOfPlot.MOVIE) UIC_extra_light else UIC,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                fontWeight = FontWeight.ExtraLight,
                                                fontFamily = JetBrainsFont(),
                                                fontSize = 14.4.sp
                                            )
                                        }
                                    }
                                }

                                else -> {}
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Row {
                    Box(
                        Modifier.clickable {
                            show = false
                            SaveManager.data.plots.add(plot)
                            SaveManager.save()
                            viewModel.setStatus(AppStatus.PLOTS_UPDATER)
                        }
                            .background(UIC, RoundedCornerShape(18.8.dp))
                            .weight(0.5f)
                            .padding(5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = ts_Create,
                            color = UIC_light,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Normal,
                            fontFamily = JetBrainsFont(),
                            fontSize = 16.sp
                        )
                    }
                    Box(
                        Modifier.clickable {
                            show = false
                        }
                            .background(UIC, RoundedCornerShape(18.8.dp))
                            .weight(0.5f)
                            .padding(5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = ts_Cancel,
                            color = UIC_light,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Normal,
                            fontFamily = JetBrainsFont(),
                            fontSize = 16.sp
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}