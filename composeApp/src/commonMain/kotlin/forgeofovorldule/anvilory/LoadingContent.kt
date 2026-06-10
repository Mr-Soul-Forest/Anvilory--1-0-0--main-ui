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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import anvilory.composeapp.generated.resources.Res
import anvilory.composeapp.generated.resources.app_icon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoadingContent(viewModel: AppViewModel) {
    LaunchedEffect(Unit) {
        loading(viewModel)
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(UIC_light),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.4.dp)
        ) {
            Text(
                text = "-> $ts_loading <-",
                color = UIC_extra_light,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = JetBrainsFont(),
                fontSize = 14.4.sp
            )
            Image(
                painter = painterResource(Res.drawable.app_icon),
                contentDescription = ts_app_icon,
                modifier = Modifier.size(60.dp)
                    .clip(RoundedCornerShape(12.4.dp))
            )
            Text(
                text = "Anvilory",
                color = UIC_extra_dark,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = JetBrainsFont(),
                fontSize = 19.2.sp
            )
        }
    }
}

var loadingIsGood = false

private suspend fun loading(viewModel: AppViewModel) = withContext(Dispatchers.Default) {
    changeLanguage()
    SaveManager.save()
    loadingIsGood = true
    viewModel.setStatus(AppStatus.PLOTS)
}