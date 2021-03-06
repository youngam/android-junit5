package de.mannodermaus.gradle.plugins.junit5

import com.android.annotations.NonNull
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.variant.BaseVariantData
import org.junit.platform.gradle.plugin.FiltersExtension
import org.junit.platform.gradle.plugin.SelectorsExtension

import static java.util.Collections.emptyList

/**
 * Utility functions exposed to Kotlin consumers
 * that can't safely access Groovy members otherwise.*/
class GroovyInterop {
  // No instances
  private GroovyInterop() { throw new AssertionError() }

  /**
   * Obtains the VariantData of the provided Variant.
   * BaseVariant#variantData is protected
   * and can't be accessed through Kotlin directly.
   *
   * @param variant Variant to retrieve the scope of
   * @return The scope
   */
  @NonNull
  static BaseVariantData baseVariant_variantData(BaseVariant variant) {
    return variant.variantData
  }

  /**
   * Obtains the "includeClassNamePatterns" property off a FiltersExtension.
   * This property doesn't have a visibility modifier in
   * the main Gradle plugin, and therefore needs
   * to be accessed in this fashion.
   *
   * @param extension Extension to access
   * @return The list of "include class name patterns"
   */
  @NonNull
  static List<String> filtersExtension_includeClassNamePatterns(FiltersExtension extension) {
    return extension.includeClassNamePatterns ?: emptyList()
  }

  /**
   * Obtains the "excludeClassNamePatterns" property off a FiltersExtension.
   * This property doesn't have a visibility modifier in
   * the main Gradle plugin, and therefore needs
   * to be accessed in this fashion.
   * ?: Collections.emptyList()
   * @param extension Extension to access
   * @return The list of "exclude class name patterns"
   */
  @NonNull
  static List<String> filtersExtension_excludeClassNamePatterns(FiltersExtension extension) {
    return extension.excludeClassNamePatterns ?: emptyList()
  }

  /**
   * Obtains the "isEmpty" property off a SelectorsExtension.
   * This property is protected in Groovy
   * and can't be accessed directly through Kotlin.
   *
   * @param extension Extension to access
   * @return Whether or not the Selectors are empty
   */
  static boolean selectorsExtension_isEmpty(SelectorsExtension extension) {
    return extension.empty
  }
}
