package wu.seal.jsontokotlin

import com.google.gson.Gson
import wu.seal.jsontokotlin.bean.jsonschema.JsonSchema

//TODO Methods from this class should be moved to KotlinCodeMaker
object KotlinCodeMakerJsonSchema {
    private fun parseJSONSchemaOrNull(className: String, json: String) = try {
        parseJSONSchema(className, json)
    } catch (t: Throwable) {
        null
    }

    @Throws
    fun parseJSONSchema(className: String?, json: String): String {
        val jsonSchema = Gson().fromJson<JsonSchema>(json, JsonSchema::class.java)
        val classes = JsonSchemaDataClassGenerator(jsonSchema, if (className.isNullOrBlank()) null else className).generate()
        //`classes` can be also saved into separated files since it's a map
        return classes.values.joinToString("\n") { it.toString() }
    }
}
