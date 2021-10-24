package io.github.msengbusch.murraycraft.cloud

import io.github.msengbusch.murraycraft.cloud.template.load.TemplateLoader

fun main(args: Array<String>) {
    val loader = TemplateLoader()
    val templates = loader.loadTemplates()

    //val cloud = Cloud()
    //cloud.run()
}