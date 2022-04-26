/*
 * Copyright (c) 2022. The Meowool Organization Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * In addition, if you modified the project, you must include the Meowool
 * organization URL in your code file: https://github.com/meowool
 *
 * 如果您修改了此项目，则必须确保源文件中包含 Meowool 组织 URL: https://github.com/meowool
 */
@file:Suppress("UnstableApiUsage")
@file:OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)

import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMultiplatformPlugin

val mobileSrcDir = "src/mobileMain/kotlin"

plugins {
  id(Plugins.Jetbrains.Compose)
}

androidLib {
  namespace = publication.data.groupId
  enableCompose()
  KotlinMultiplatformPlugin
}

jvmTarget()

commonTarget {
  main.dependencies {
    apiOf(
      compose.ui,
      compose.animation,
      compose.foundation,
      Libs.Meowool.Toolkit.Sweekt,
    )
    // TODO: Just for reference, so remove these dependencies after release
    implementationOf(
      compose.material,
      compose.material3,
    )
  }
}

androidTarget {
  main {
    dependsOn(jvmMainSourceSet)
    kotlin.srcDirs(mobileSrcDir)
  }
  test.dependencies {
    implementationOf(compose.uiTestJUnit4)
  }
}
