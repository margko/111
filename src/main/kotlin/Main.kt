fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

job("Test and build") {
    container(displayName = "Install deps", image = "node:16.13.2-slim") {
        shellScript {
            interpreter = "/bin/sh"
            content = """ 
                    npm ci 
                    cp -R node_modules $mountDir/share 
                    """
        }
    }

    container(displayName = "Lint", image = "node:16.13.2-slim") {
        shellScript {
            interpreter = "/bin/sh"
            content = """ 
                    ls -la $mountDir/share 
                    """
        }
    }
}