import dependencies.gson
import dependencies.serialization
import gradle_plugins.DataGradlePlugin
import modules.searchDomain

apply<DataGradlePlugin>()

dependencies {
    serialization()
    gson()

    searchDomain()
}