/**Copyright 2025 Forge-of-Ovorldule (https://github.com/Forge-of-Ovorldule) and Mr-Soul-Forest (https://github.com/Mr-Soul-Forest)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package forgeofovorldule.anvilory

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
class Plot(
    var title: String = ts_New_plot,
    var description: String = ts_This_is_an_example_story,
    var typeOfPlot: TypeOfPlot = TypeOfPlot.STORY
) {
    var chapters = mutableListOf(Chapter())
    var lastEdit: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
}