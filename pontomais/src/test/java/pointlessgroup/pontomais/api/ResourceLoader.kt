package pointlessgroup.pontomais.api


fun fromResouces(path: String): String
        = ClassLoader.getSystemClassLoader().getResource(path).readText()